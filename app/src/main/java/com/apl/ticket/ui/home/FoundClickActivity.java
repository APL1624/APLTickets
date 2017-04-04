package com.apl.ticket.ui.home;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.apl.ticket.ui.home.fragment.FoundCircleFragment;
import com.apl.ticket.ui.home.fragment.FoundLifeFragment;
import com.apl.ticket.ui.home.fragment.FoundReadFragment;
import com.vittaw.mvplibrary.base.BaseActivity;

import butterknife.BindView;

public class FoundClickActivity extends BaseActivity implements View.OnClickListener {


    @Override
    public int getLayoutId() {
        return R.layout.activity_found_click;
    }

    @Override
    public void initPresenter() {

    }

    @BindView(R2.id.found_click_return_btn)
    Button returnBtn;

    @Override
    public void initView() {
        addLayout();
        returnBtn.setOnClickListener(this);
    }

    @BindView(R2.id.found_click_title)
    TextView mTitle;

    @BindView(R2.id.found_click_login_btn)
    Button loginBtn;

    private void addLayout() {
        Intent intent = getIntent();
        int extra = intent.getIntExtra("key", 0);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (extra) {
            case 1:
                mTitle.setText("推荐阅读");
                transaction.add(R.id.found_click_framelayout, new FoundReadFragment());
                break;
            case 2:
                mTitle.setText("圈子");
                loginBtn.setVisibility(View.VISIBLE);
                transaction.add(R.id.found_click_framelayout,new FoundCircleFragment());
                break;
            case 3:
                mTitle.setText("乐得没好生活");
                transaction.add(R.id.found_click_framelayout,new FoundLifeFragment());
                break;
        }
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
