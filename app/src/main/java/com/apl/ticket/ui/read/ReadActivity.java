package com.apl.ticket.ui.read;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.vittaw.mvplibrary.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ReadActivity extends BaseActivity {

    public static final String TAG = ReadActivity.class.getSimpleName();

    public static final String URL_TAG = "url";

    @BindView(R2.id.read_web_view)
    WebView mWeb;

    @Override
    public int getLayoutId() {
        return R.layout.activity_read;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        String url = getIntent().getStringExtra(URL_TAG);
        WebSettings settings = mWeb.getSettings();
        //设置能够执行Java脚本
        settings.setJavaScriptEnabled(true);
        //设置可以访问文件
        settings.setAllowFileAccess(true);
        settings.setBuiltInZoomControls(true);
        //加载需要显示的网页
        mWeb.loadUrl(url);
        //设置Web视图
        mWeb.setWebViewClient(new WebViewClient());
    }

    @OnClick(R2.id.read_back)
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.read_back:
                finish();
                break;
        }

    }
}
