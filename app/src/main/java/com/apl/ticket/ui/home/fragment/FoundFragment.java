package com.apl.ticket.ui.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.apl.ticket.been.FoundImageBeen;
import com.apl.ticket.ui.home.FoundClickActivity;
import com.apl.ticket.ui.home.FoundPagerActivity;
import com.apl.ticket.ui.home.HomeActivity;
import com.apl.ticket.ui.home.adapter.FoundViewPagerAdapter;
import com.apl.ticket.ui.home.contract.FoundContract;
import com.apl.ticket.ui.home.model.FoundModel;
import com.apl.ticket.ui.home.presenter.FoundPresenter;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;
import com.vittaw.mvplibrary.base.BaseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class FoundFragment extends BaseFragment<FoundModel, FoundPresenter> implements FoundContract.View, ViewPager.OnPageChangeListener, Handler.Callback, View.OnTouchListener, View.OnClickListener {

    public static final String TAG = FoundFragment.class.getName();
    private static final int RUN = 100;
    private FoundViewPagerAdapter adapter;
    private FoundImageBeen foundImageBeen;

    private Handler mHandler = new Handler(this);
    private int ScrollNum = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_found;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
    }


    @BindView(R2.id.home_found_viewPager)
    ViewPager mViewPager;

    @BindView(R2.id.home_found_read)
    RelativeLayout readLayout;

    @BindView(R2.id.home_found_circle)
    RelativeLayout circleLayout;

    @BindView(R2.id.home_found_lift)
    RelativeLayout liftLayout;

    @Override
    protected void initView() {
        adapter = new FoundViewPagerAdapter(null);
        mViewPager.setAdapter(adapter);
        mViewPager.setOnTouchListener(this);
        mViewPager.addOnPageChangeListener(this);
        readLayout.setOnClickListener(this);
        circleLayout.setOnClickListener(this);
        liftLayout.setOnClickListener(this);

        mPresenter.getFoundImageBeen(createMap());
    }

    private Map<String, String> createMap() {
        /**
         * http://piao.163.com
         * /m/movie/getBannerList.html
         * ?data=PgvHPcLxglYagkKzYQ9YZgDJpkFiI4p5JR1A64d5cbodKepoGzmb3w%3D%3D
         * &stamp=1490785958671
         * &apiVer=21
         * &apilevel=17
         * &mobileType=android
         * &deviceId=133524057653049
         * &channel=netease
         * &ver=4.16.2
         * &city=110000
         */
        Map<String, String> map = new HashMap<>();
        map.put("?data", "PgvHPcLxglYagkKzYQ9YZgDJpkFiI4p5JR1A64d5cbodKepoGzmb3w%3D%3D");
        map.put("stamp", "1490785958671");
        map.put("apiVer", "21");
        map.put("apilevel", "17");
        map.put("mobileType", "android");
        map.put("deviceId", "133524057653049");
        map.put("deviceId", "netease");
        map.put("ver", "4.16.2");
        map.put("city", "110000");
        return map;
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
    public void returnFoundImageBeen(FoundImageBeen foundImageBeen) {
        Logger.e("数据" + foundImageBeen, foundImageBeen);
        getImageList(foundImageBeen);
    }

    private void getImageList(FoundImageBeen foundImageBeen) {
        List<ImageView> data = new ArrayList<>();
        int size = foundImageBeen.getBannerList().size();
        for (int i = 0; i < size + 2; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            if (i == 0) {
                Picasso.with(getActivity()).load(foundImageBeen.getBannerList().get(size - 1).getPicLargePath()).into(imageView);
            } else if (i == size + 1) {
                Picasso.with(getActivity()).load(foundImageBeen.getBannerList().get(0).getPicLargePath()).into(imageView);
            } else {
                Picasso.with(getActivity()).load(foundImageBeen.getBannerList().get(i - 1).getPicLargePath()).into(imageView);
            }
            data.add(imageView);
        }
        adapter.upData(data);
        this.foundImageBeen=foundImageBeen;
        mHandler.sendEmptyMessageDelayed(RUN, 0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ScrollNum = position;
        if (position == 7) {
            int id = mRadioGroup.getChildAt(0).getId();
            ((RadioButton) mRadioGroup.findViewById(id)).setChecked(true);
            mViewPager.setCurrentItem(1, false);
        } else if (position == 0) {
            int id = mRadioGroup.getChildAt(5).getId();
            ((RadioButton) mRadioGroup.findViewById(id)).setChecked(true);
            mViewPager.setCurrentItem(6, false);
        } else {
            int id = mRadioGroup.getChildAt(position - 1).getId();
            ((RadioButton) mRadioGroup.findViewById(id)).setChecked(true);
        }
    }

    @BindView(R2.id.home_found_radioGroup)
    RadioGroup mRadioGroup;

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case RUN:
                if (ScrollNum == 0) {
                    ScrollNum = 6;
                } else if (ScrollNum == 7) {
                    ScrollNum = 1;
                }
                mViewPager.setCurrentItem(ScrollNum++, false);
        }
        mHandler.sendEmptyMessageDelayed(RUN, 5 * 1000);
        return true;
    }

    int flate = 0;
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                flate = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                flate = 1;
                break;
            case MotionEvent.ACTION_UP:
                if(flate==0){
                    int position = mViewPager.getCurrentItem();
                    Intent intent = new Intent(getActivity(), FoundPagerActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("key", foundImageBeen.getBannerList().get(position - 1));
                    intent.putExtras(bundle);
                    ((HomeActivity) view.getContext()).startActivityForResult(intent, 1);
                }
                break;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), FoundClickActivity.class);
        switch (view.getId()) {
            case R.id.home_found_read:
                intent.putExtra("key",1);
                break;
            case R.id.home_found_circle:
                intent.putExtra("key",2);
                break;
            case R.id.home_found_lift:
                intent.putExtra("key",3);
                break;
        }
        startActivityForResult(intent,1);
    }
}
