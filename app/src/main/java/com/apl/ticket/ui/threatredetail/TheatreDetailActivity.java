package com.apl.ticket.ui.threatredetail;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.apl.ticket.DensityUtils;
import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.apl.ticket.been.theatre.ThreatreBean;
import com.apl.ticket.ui.home.transformer.ScaleTransformer;
import com.apl.ticket.ui.selectseat.SelectSeat;
import com.apl.ticket.ui.threatredetail.adapter.ViewPagerAdapter;
import com.apl.ticket.ui.threatredetail.contract.TheatreDetailContract;
import com.apl.ticket.ui.threatredetail.model.TheatreDetailModel;
import com.apl.ticket.ui.threatredetail.presenter.TheatreDetailPresenter;
import com.orhanobut.logger.Logger;
import com.vittaw.mvplibrary.base.BaseActivity;
import com.vittaw.mvplibrary.base.BasePresenter;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class TheatreDetailActivity extends BaseActivity<TheatreDetailPresenter,TheatreDetailModel>implements TheatreDetailContract.View, ViewPager.OnPageChangeListener, View.OnClickListener {
    private static final String TAG =TheatreDetailActivity.class.getSimpleName() ;
    @BindView(R2.id.theatre_detail_item_one_gallery)
    ViewPager mViewPager;
    private ViewPagerAdapter adapter;
    @BindView(R2.id.container)
    RelativeLayout mRelativeLayout;
    @BindView(R2.id.theatre_detail_item_movie_name)
    TextView mTextView;
    private ThreatreBean threatreBean;
    @BindView(R2.id.theatre_detail_item_movie_show_time)
    TabLayout mTabLayout;
    @BindView(R2.id.theatre_detail_item_movie_item)
    LinearLayout mLinearLayout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_theatre_detail;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    public void initView() {
        Map<String, String>map= new HashMap<>();
        map.put("cinema_id","4989");
        map.put("city","370300");
        mPresenter.getThreatreBean(map);


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
    public void showViewpager(ThreatreBean threatreBean) {
        mViewPager.setOffscreenPageLimit(10);
        Log.e(TAG,threatreBean.getMovieList().size()+"  dd  "+threatreBean.getMovieList().get(0).getLogo());
        adapter = new ViewPagerAdapter(threatreBean.getMovieList(),this);
        mRelativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mViewPager.dispatchTouchEvent(event);
            }
        });
        mViewPager.setPageMargin(15);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(this);
        this.threatreBean=threatreBean;


        for (int i = 0; i < 3; i++) {
            TabLayout.Tab tab = mTabLayout.newTab();
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, i);
            CharSequence format = DateFormat.format("MM月dd日", c);
            tab.setText(format);
            mTabLayout.addTab(tab);
        }
        LinearLayout linearLayout = (LinearLayout) mTabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,
                R.drawable.layout_divider_vertical));
        linearLayout.setDividerPadding(DensityUtils.dp2px(this,15));
        for (int i = 0; i < 9; i++) {
            View view = LayoutInflater.from(this).inflate(R.layout.theatre_detail_linear_item, null);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,DensityUtils.dp2px(this,70));
            view.setLayoutParams(params);
            Button btn = (Button) view.findViewById(R.id.theatre_detail_linear_item_btn);
            btn.setOnClickListener(this);
            mLinearLayout.addView(view);
        }


    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        mTextView.setText(threatreBean.getMovieList().get(position).getName());

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        Log.e(TAG,"btnnnnnnn");
        switch (view.getId()) {

            case R.id.theatre_detail_linear_item_btn:
                startActivity(new Intent(this, SelectSeat.class));
                Log.e(TAG,"btn");
                break;
        }
    }
}
