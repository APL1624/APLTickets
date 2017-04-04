package com.apl.ticket.ui.location;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.apl.ticket.R;
import com.apl.ticket.R2;

import com.apl.ticket.been.location.LocationCityBean;
import com.apl.ticket.ui.location.adapter.LocationAdapter;
import com.apl.ticket.ui.location.contract.LocationContract;
import com.apl.ticket.ui.location.model.LocationModel;
import com.apl.ticket.ui.location.presenter.LocationPresenter;
import com.apl.ticket.ui.location.view.LetterView;
import com.vittaw.mvplibrary.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *fqc cry cry
 */
public class LocationActivity extends BaseActivity<LocationPresenter,LocationModel> implements LocationContract.View, AMapLocationListener,LetterView.TypeListener, View.OnClickListener {
    private static final int WRITE_COARSE_LOCATION_REQUEST_CODE =25 ;
    private static final String TAG =LocationActivity.class.getSimpleName() ;
    @BindView(R2.id.location_fqc_city_list)
    RecyclerView mLocationCityList;
    private LocationAdapter mAdapter;
    private List<LocationCityBean>cityInfo;

    @BindView(R2.id.location_fqc_city_current)
    TextView cityName;

    @BindView(R2.id.location_letter_view)
    LetterView mLetterView;
    @BindView(R2.id.location_letter)
    TextView mletter;
    @BindView(R2.id.location_scroll)
    View locationScroll;
    @BindView(R2.id.location_fqc_ab_back)
    ImageView mLocationImageBack;

    //定位服务类
    private AMapLocationClient locationClient = null;
    //定位参数类
    private AMapLocationClientOption locationClientOption = null;
    private LinearLayoutManager layoutManager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_location;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    public void initView() {

        cityInfo = new ArrayList<>();
        mAdapter=new LocationAdapter(cityInfo,this);
        layoutManager = new LinearLayoutManager(this);
        mLocationCityList.setLayoutManager(layoutManager);
        mLocationCityList.setAdapter(mAdapter);
        mLocationImageBack.setOnClickListener(this);
        getPermission();
        mPresenter.handleModelToView();
        mLetterView.setLetter(mletter);

        mLetterView.setTypeListener(this);
        mLocationCityList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState==RecyclerView.SCROLL_STATE_IDLE) {
                    locationScroll.setVisibility(View.GONE);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                locationScroll.setVisibility(View.VISIBLE);
                int measuredHeight = mLocationCityList.getMeasuredHeight();
                float divisor= (float) (1.0/cityInfo.size());
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
                locationScroll.setTranslationY(measuredHeight*divisor*firstVisibleItemPosition);
            }
        });

    }


    private void getPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.READ_PHONE_STATE},
                    WRITE_COARSE_LOCATION_REQUEST_CODE);//自定义的code
        }else {
            local();
        }

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
    public void showLocationData(List<LocationCityBean> cityInfo) {
        mAdapter.updateRes(cityInfo);
        mLocationCityList.addOnScrollListener(new RecyclerView.OnScrollListener() {
        });
    }

    @Override
    public String setModelKey() {
        return "TKRBZ-A3HA4-TCVUD-X5326-6B2HT-BDFLF";
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //初始化定位

        local();

    }

    private void local() {
        locationClient = new AMapLocationClient(getApplicationContext());
        locationClient.setLocationListener(this);
        //定位参数设置
        locationClientOption = new AMapLocationClientOption();
        locationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        locationClient.setLocationOption(locationClientOption);
        locationClient.startLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {

            if (aMapLocation.getErrorCode() == 0) {

               cityName.setText(aMapLocation.getCity());


            } else {

               Toast.makeText(this,"定位失败, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo(),Toast.LENGTH_SHORT
               ).show();
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationClient != null) {
            locationClient.onDestroy();
            locationClient = null;
            locationClientOption = null;
        }
    }


    @Override
    public void mathType(String Type) {

        for (int i = 0; i <cityInfo.size() ; i++) {
            if(cityInfo.get(i).getType()==0){
                if(cityInfo.get(i).getFullName().substring(0,1).equals(Type)){
                   MoveToPosition(layoutManager,mLocationCityList,i);
                    break;
                }
            }
        }

    }
    public static void MoveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {


        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            manager.scrollToPositionWithOffset(n, 0);
            manager.setStackFromEnd(true);

        }

    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
