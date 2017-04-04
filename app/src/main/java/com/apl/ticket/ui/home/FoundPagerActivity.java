package com.apl.ticket.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.apl.ticket.been.FoundImageBeen;
import com.vittaw.mvplibrary.base.BaseActivity;

import butterknife.BindView;

public class FoundPagerActivity extends BaseActivity implements View.OnClickListener {

    @Override
    public int getLayoutId() {
        return R.layout.activity_found_pager;
    }

    @Override
    public void initPresenter() {

    }

    @BindView(R2.id.activity_found_pager_web)
    WebView mWebView;

    @BindView(R2.id.activity_found_pager_btn)
    Button mButton;

    @BindView(R2.id.activity_found_textview)
    TextView mTextView;

    @Override
    public void initView() {
        Intent intent = getIntent();
        FoundImageBeen.Banner extra = intent.getParcelableExtra("key");

        WebSettings settings = mWebView.getSettings();
        //设置能够执行Java脚本
        settings.setJavaScriptEnabled(true);
        //设置可以访问文件
        settings.setAllowFileAccess(true);
        settings.setBuiltInZoomControls(true);
        //加载需要显示的网页
        mWebView.loadUrl(extra.getUrl());
        //设置Web视图
        mWebView.setWebViewClient(new WebViewClient());

        mTextView.setText(extra.getName());
        mButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_found_pager_btn:
                finish();
                break;
        }
    }
}
