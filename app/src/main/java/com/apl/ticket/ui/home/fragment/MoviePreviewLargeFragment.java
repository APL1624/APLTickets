package com.apl.ticket.ui.home.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.apl.ticket.been.HomePageBeen;
import com.apl.ticket.ui.home.EventBus.EventWhat;
import com.apl.ticket.ui.home.EventBus.MovieHotLargeEvent;
import com.apl.ticket.ui.home.adapter.MoviePreviewLargeAdapter;
import com.apl.ticket.ui.home.transformer.ScaleTransformer;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.vittaw.mvplibrary.adapter.CommonFragmentPagerAdapter;
import com.vittaw.mvplibrary.base.BaseFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/3/25 0025.
 */

public class MoviePreviewLargeFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    public static final String TAG = MoviePreviewLargeFragment.class.getName();
    private MoviePreviewLargeAdapter adapter;
    private HomePageBeen mData;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_movie_large_hot;
    }

    @Override
    protected void initPresenter() {

    }

    @BindView(R2.id.home_movie_hot_viewPager)
    ViewPager mViewPager;


    @Override
    protected void initView() {

        adapter = new MoviePreviewLargeAdapter();
        mViewPager.setOffscreenPageLimit(3);
        int widthPixels = (int) getResources().getDisplayMetrics().widthPixels;
        int pagerWidth = (int) (getResources().getDisplayMetrics().widthPixels * 3.0f / 4.0f);
        ViewGroup.LayoutParams mViewpageParams = mViewPager.getLayoutParams();
        if (mViewpageParams == null) {
            mViewpageParams = new ViewGroup.LayoutParams(pagerWidth, ViewGroup.LayoutParams.MATCH_PARENT);
            Logger.e("宽度", pagerWidth);
        } else {
            mViewpageParams.width = pagerWidth;
        }
        mViewPager.setLayoutParams(mViewpageParams);
        mViewPager.setPageMargin(-20);
        mViewPager.setTranslationX((widthPixels - pagerWidth) / 2 );
        mViewPager.setPageTransformer(true, new ScaleTransformer());
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(this);
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
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        showButton();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(sticky = true)
    public void onEvent(MovieHotLargeEvent movieHotLargeEvent) {
        switch (movieHotLargeEvent.WHAT) {
            case EventWhat.GET_HP_DATA:
                mData = movieHotLargeEvent.getHomePageBeen();
                Logger.e("数据", mData);
                getImageArray();
                mViewPager.setCurrentItem(100, false);
                setTextView(100);
                break;
        }
    }

    public void getImageArray() {

        List<ImageView> imgData = new ArrayList<>();
        for (int i = 0; i < mData.getList().size(); i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Picasso.with(getActivity()).load(mData.getList().get(i).getLogo3()).into(imageView);
            imgData.add(imageView);
        }
        adapter.upData(imgData);
    }


    @BindView(R2.id.home_movie_hot_btn)
    Button mBuyButton;

    @BindView(R2.id.home_movie_preview_look)
    Button mButtonLook;

    private void showButton() {
        mBuyButton.setVisibility(View.GONE);
        mButtonLook.setVisibility(View.VISIBLE);
    }

    @BindView(R2.id.home_movie_hot_title)
    TextView title;
    @BindView(R2.id.home_movie_hot_resurt)
    TextView resurt;
    @BindView(R2.id.home_movie_hot_content)
    TextView content;


    private void setTextView(int position) {
        int size = mData.getList().size();

        title.setText(mData.getList().get(position % size).getName());
        resurt.setText(mData.getList().get(position % size).getGrade());
        content.setText(mData.getList().get(position % size).getHighlight());
    }
}
