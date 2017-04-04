package com.apl.ticket.ui.home.fragment;

import android.support.v4.view.ViewPager;

import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.vittaw.mvplibrary.base.BaseFragment;

import butterknife.BindView;

/**
 * TODO 加泛型网络加载数据
 */

public class MovieFragment extends BaseFragment {

    public static final String TAG = MovieFragment.class.getName();

    @BindView(R2.id.fragment_movie_view_pager)
    ViewPager mViewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_movie;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {

    }
}
