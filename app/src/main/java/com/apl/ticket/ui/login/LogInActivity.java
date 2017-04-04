package com.apl.ticket.ui.login;


import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.apl.ticket.ui.login.contract.LogInContract;
import com.apl.ticket.ui.login.model.LogInModel;
import com.apl.ticket.ui.login.presenter.LogInPresenter;
import com.squareup.picasso.Picasso;
import com.vittaw.mvplibrary.base.BaseActivity;

import java.util.HashMap;
import java.util.Set;

import butterknife.BindView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

public class LogInActivity extends BaseActivity<LogInPresenter, LogInModel> implements LogInContract.View, View.OnClickListener, PlatformActionListener {
    private static final String TAG = LogInActivity.class.getSimpleName();
    @BindView(R2.id.log_in_finish)
    TextView mFinishText;
    @BindView(R2.id.login_qq)
    ImageView mLogInQQ;
    private String mImageUrl;


    @Override
    public int getLayoutId() {
        return R.layout.activity_log_in;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);

    }

    @Override
    public void initView() {
        ShareSDK.initSDK(this);
        mFinishText.setOnClickListener(this);
        mLogInQQ.setOnClickListener(this);
    }

    @Override
    public void onStartLoad() {

    }

    @Override
    public void onStopLoad() {

    }

    @Override
    public void onError(String errorInfo) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.log_in_finish:
                finish();
                break;
            case R.id.login_qq:

                logInQQ();
                break;
        }
    }

    private void logInQQ() {
        Platform qq = ShareSDK.getPlatform(QQ.NAME);

        authorize(qq);
    }

    private void authorize(Platform plat) {
        if (plat == null) {
            return;
        }

        plat.setPlatformActionListener(this);
        //关闭SSO授权
        plat.SSOSetting(true);
        plat.showUser(null);
    }

    Handler handler = new Handler();

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

        if (i == Platform.ACTION_USER_INFOR) {
            Set<String> keySet = hashMap.keySet();
            for (String key : keySet) {
                if (key.equals("figureurl_qq_1")) {
                    mImageUrl = hashMap.get(key).toString();
                    Log.e(TAG, mImageUrl);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Picasso.with(LogInActivity.this).load(mImageUrl).into(mLogInQQ);
                            SharedPreferences sharedPreferences =
                                    getSharedPreferences("data", MODE_PRIVATE);
                            //2 获取Editor
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            //3 放数据
                            editor.putString("imageUrl", mImageUrl);
                            //4.提交
                            editor.commit();
                            finish();
                        }
                    });
                }
                Log.e(TAG, hashMap.get(key).toString() + "  " + key);

            }


        }
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(Platform platform, int i) {

    }


}
