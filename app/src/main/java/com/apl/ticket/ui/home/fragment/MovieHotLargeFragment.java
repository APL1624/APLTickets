package com.apl.ticket.ui.home.fragment;

import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.apl.ticket.been.HomePageBeen;
import com.apl.ticket.ui.home.EventBus.EventWhat;
import com.apl.ticket.ui.home.EventBus.MovieHotLargeEvent;
import com.apl.ticket.ui.home.contract.MovieHotContract;
import com.apl.ticket.ui.home.model.HomePageModel;
import com.apl.ticket.ui.home.presenter.HomePagePresenter;
import com.apl.ticket.ui.home.transformer.ScaleTransformer;
import com.orhanobut.logger.Logger;
import com.vittaw.mvplibrary.adapter.CommonFragmentPagerAdapter;
import com.vittaw.mvplibrary.base.BaseFragment;
import com.vittaw.mvplibrary.utils.LoadingDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/3/25 0025.
 */

public class MovieHotLargeFragment extends BaseFragment<HomePageModel, HomePagePresenter> implements MovieHotContract.View, ViewPager.OnPageChangeListener, View.OnTouchListener {

    private String city = "110000";
    private String type = "0";

    public static final String TAG = MovieHotLargeFragment.class.getName();

    private HomePageBeen mData;

    @BindView(R2.id.home_movie_hot_viewPager)
    ViewPager mViewPager;

    private CommonFragmentPagerAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_movie_large_hot;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @BindView(R2.id.home_movie_hot_relativelayout)
    RelativeLayout mRelativeLayout;

    @Override
    protected void initView() {
        mPresenter.getHomePageBeen(String.valueOf(type), String.valueOf(city));

        adapter = new CommonFragmentPagerAdapter(getActivity().getSupportFragmentManager(), null);
        mViewPager.setOffscreenPageLimit(3);
        int widthPixels = (int) getResources().getDisplayMetrics().widthPixels;
        int pagerWidth = ((int) (getResources().getDisplayMetrics().widthPixels * 4.0f / 5.0f));
        ViewGroup.LayoutParams params = mViewPager.getLayoutParams();
        if (params == null) {
            params = new ViewGroup.LayoutParams(pagerWidth, ViewGroup.LayoutParams.MATCH_PARENT);
        } else {
            params.width = pagerWidth;
        }
        mViewPager.setLayoutParams(params);
        mViewPager.setPageMargin(-50);
        mViewPager.setTranslationX((widthPixels - pagerWidth) / 2 - 10);
//        mViewPager.setPageTransformer(true, new ScaleTransformer());
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(this);
        showButton();
    }

    @BindView(R2.id.home_movie_hot_btn)
    Button mBuyButton;

    @BindView(R2.id.home_movie_preview_look)
    Button mButtonLook;

    private void showButton() {
        Logger.e("执行了show");
        mBuyButton.setVisibility(View.VISIBLE);
        mButtonLook.setVisibility(View.GONE);
    }


    private List<Fragment> getData() {
        List<Fragment> data=new ArrayList<>();
        if (mData!=null) {
            for (int i = 0; i < mData.getList().size()+2; i++) {
                String logo3 = "";
                if (i==0){
                    logo3 = mData.getList().get(mData.getList().size() - 1).getLogo3();
                    addFragment(data, logo3);

                }else if (i==mData.getList().size()+1){
                    logo3 = mData.getList().get(0).getLogo3();
                    addFragment(data,logo3);
                }else{
                    logo3 =  mData.getList().get(i-1).getLogo3();
                    addFragment(data,logo3);
                }


            }
        }
        return data;
    }

    private void addFragment(List<Fragment> data, String logo3) {
        MovieHotLargeViewPagerFragment fragment = new MovieHotLargeViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putString(MovieHotLargeViewPagerFragment.IMAGE_URL,logo3);
        fragment.setArguments(bundle);
        data.add(fragment) ;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            int position = mViewPager.getCurrentItem();
            setTextView(position);
            if (position == mViewPager.getAdapter().getCount() - 1) {
                // 将位置滚动到第二个元素
                mViewPager.setCurrentItem(1, false);
            } else if (position == 0) {
                // 将位置滚动到倒数第二个元素
                mViewPager.setCurrentItem(mViewPager.getAdapter().getCount() - 2, false);
            }
        }
    }

    @BindView(R2.id.home_movie_hot_title)
    TextView title;
    @BindView(R2.id.home_movie_hot_resurt)
    TextView resurt;
    @BindView(R2.id.home_movie_hot_content)
    TextView content;

    private void setTextView(int position) {
        int size = mData.getList().size();
        if (position == 0) {
            title.setText(mData.getList().get(size - 1).getName());
            resurt.setText(mData.getList().get(size - 1).getGrade());
            content.setText(mData.getList().get(size - 1).getHighlight());
        } else if (position == size + 1) {
            title.setText(mData.getList().get(0).getName());
            resurt.setText(mData.getList().get(0).getGrade());
            content.setText(mData.getList().get(0).getHighlight());
        } else {
            title.setText(mData.getList().get(position - 1).getName());
            resurt.setText(mData.getList().get(position - 1).getGrade());
            content.setText(mData.getList().get(position - 1).getHighlight());
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return mViewPager.dispatchTouchEvent(motionEvent);
    }

    @Override
    public void returnHomePageBeen(HomePageBeen homePageBeen) {
        mData = homePageBeen;
        Logger.e("HotLarge的数据" + mData.getList().size());
        adapter.updateRes(getData());
        mViewPager.setCurrentItem(1, false);
        setTextView(1);
    }

    @Override
    public void onStartLoad() {
        LoadingDialog.showDialogForLoading(getActivity());
    }

    @Override
    public void onStopLoad() {
        LoadingDialog.cancelDialogForLoading();
    }

    @Override
    public void onError(String errorInfo) {
        Toast.makeText(getActivity(), "网络出错了!", Toast.LENGTH_SHORT).show();
    }
}
