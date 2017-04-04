package com.apl.ticket.ui.home.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.apl.ticket.been.FoundReadBeen;
import com.apl.ticket.been.FoundReadTabBeen;
import com.apl.ticket.ui.home.contract.FoundReadContract;
import com.apl.ticket.ui.home.model.FoundReadModel;
import com.apl.ticket.ui.home.presenter.FoundReadPresenter;
import com.orhanobut.logger.Logger;
import com.vittaw.mvplibrary.adapter.CommonFragmentPagerAdapter;
import com.vittaw.mvplibrary.base.BaseFragment;
import com.vittaw.mvplibrary.utils.LoadingDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import com.apl.ticket.R;
import com.vittaw.mvplibrary.base.BaseFragment;

/**
 * Created by Administrator on 2017/3/31.
 */

public class FoundReadFragment extends BaseFragment<FoundReadModel,FoundReadPresenter>  implements FoundReadContract.View{

    public static final String TAG = FoundReadFragment.class.getName();

    @BindView(R2.id.found_read_tab_layout)
    TabLayout mTabLayout;

    @BindView(R2.id.found_read_view_pager)
    ViewPager mViewPager;
    private CommonFragmentPagerAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.found_read_fragment;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected void initView() {
        mPresenter.getFoundReadTabBeen();
        mAdapter = new CommonFragmentPagerAdapter(getChildFragmentManager(),null);
        mViewPager.setAdapter(mAdapter);
    }

    @Override
    public void returnFoundTabReadBeen(FoundReadTabBeen foundReadTabBeen) {
        Logger.e("推荐阅读tab" + foundReadTabBeen.getTags().size());
        List<Fragment> fragments = new ArrayList<>();
        List<FoundReadTabBeen.Tab> tabs = foundReadTabBeen.getTags();
        FoundReadTabBeen.Tab tabAll = new FoundReadTabBeen.Tab();
        tabAll.setId("");
        tabAll.setName("全部");
        tabs.add(0, tabAll);
        for (int i = 0; i < tabs.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tabs.get(i).getName()));
            Bundle bundle = new Bundle();
            bundle.putString(FoundReadContentFragment.TAG_ID,tabs.get(i).getId());
            FoundReadContentFragment contentFragment = new FoundReadContentFragment();
            contentFragment.setArguments(bundle);
            fragments.add(contentFragment);
        }
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mAdapter.updateRes(fragments);
    }

    @Override
    public void onStartLoad() {
    }

    @Override
    public void onStopLoad() {
    }

    @Override
    public void onError(String errorInfo) {
        Toast.makeText(getActivity(), errorInfo, Toast.LENGTH_SHORT).show();
    }

}
