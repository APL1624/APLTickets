package com.apl.ticket.ui.splash;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.apl.ticket.been.SplashBeen;
import com.apl.ticket.ui.home.HomeActivity;
import com.apl.ticket.ui.location.LocationActivity;
import com.apl.ticket.ui.splash.contract.SplashContract;
import com.apl.ticket.ui.splash.model.SplashModel;
import com.apl.ticket.ui.splash.presenter.SplashPresenter;
import com.squareup.picasso.Picasso;
import com.vittaw.mvplibrary.base.BaseActivity;

import java.util.HashMap;

import butterknife.BindView;

public class SplashActivity extends BaseActivity<SplashPresenter,SplashModel> implements SplashContract.View, Animator.AnimatorListener {

    @BindView(R2.id.splash_image)
    ImageView mImage;


    private static final String TAG = SplashActivity.class.getSimpleName();

    private boolean isFirstIn;//默认是false

    public static final int DELAY_TIME = 2 * 1000;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    /**
     * timestamp=1490071559576
     * &apiVer=21
     * &apilevel=17
     * &mobileType=android
     * &deviceId=133524057653049
     * &channel=netease
     * &ver=4.16.2
     * &city=370300
     */
    @Override
    public void initView() {
        //去请求数据
        HashMap<String, String> map = new HashMap<>();
        map.put("timestamp","1490071559576");
        map.put("apiVer","21");
        map.put("apilevel","17");
        map.put("mobileType","android");
        map.put("deviceId","133524057653049");
        map.put("channel","netease");
        map.put("ver","4.16.2");
        map.put("city","370300");
        mPresenter.getSplashBeen(map);

    }

    @Override
    public void returnSplashBeen(SplashBeen splashBeen) {
        //显示到UI界面上
        String picLargePath = splashBeen.getStartupList().get(0).getPicLargePath();
        Log.e(TAG, "returnSplashBeen: 图片路径:" + picLargePath);
        Log.e(TAG, "returnSplashBeen: " + mImage );//? mImage 为null
        Picasso.with(this).load(picLargePath).into(mImage);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(mImage, "scaleX", 1, 1.2f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(mImage, "scaleY", 1, 1.2f);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(scaleX,scaleY);
        set.setDuration(DELAY_TIME);
        set.start();
        set.addListener(this);
    }

    @Override
    public void onStartLoad() {
        Log.e(TAG, "onStartLoad: " );
    }

    @Override
    public void onStopLoad() {
        Log.e(TAG, "onStopLoad: " );

    }

    @Override
    public void onError(String errorInfo) {
        //放一张默认显示的图片,谈一个吐司提示用户没网络
        Log.e(TAG, "onError: " + errorInfo );
        Toast.makeText(this, errorInfo, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
