package com.apl.ticket.ui.cinema;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.apl.ticket.ui.home.fragment.CinemaFragment;
import com.vittaw.mvplibrary.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class CinemaActivity extends BaseActivity {

    public static final String MOVIE_ID = "movie_id";



    @Override
    public int getLayoutId() {
        return R.layout.activity_cinema;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        CinemaFragment fragment = new CinemaFragment();
        Bundle bundle = new Bundle();
        bundle.putString(MOVIE_ID,"48221");//TODO 这就是写死的,可以用eventBus
        fragment.setArguments(bundle);
        transaction.add(R.id.cinema_container, fragment);
        transaction.commit();
    }

    @OnClick(R2.id.cinema_back)
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.cinema_back:
                finish();
                break;
        }
    }
}
