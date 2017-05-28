package com.apl.ticket.ui.home.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.apl.ticket.been.CinemaClassifyBeen;
import com.apl.ticket.been.cinema.CinemaListBeen;
import com.apl.ticket.ui.cinema.CinemaActivity;
import com.apl.ticket.ui.home.EventBus.CinemaEvent;
import com.apl.ticket.ui.home.EventBus.EventWhat;
import com.apl.ticket.ui.home.adapter.CinemaAdapter;
import com.apl.ticket.ui.home.contract.CinemaContract;
import com.apl.ticket.ui.home.model.CinemaModel;
import com.apl.ticket.ui.home.presenter.CinemaPresenter;
import com.apl.ticket.widget.CinemaCover;
import com.apl.ticket.widget.coveradapter.RightListAdapter;
import com.orhanobut.logger.Logger;
import com.vittaw.mvplibrary.base.BaseFragment;
import com.vittaw.mvplibrary.utils.LoadingDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnCheckedChanged;

/**
 * TODO 加泛型网络加载数据
 */

public class CinemaFragment extends BaseFragment<CinemaModel,CinemaPresenter> implements CinemaContract.View{

    public static final String TAG = CinemaFragment.class.getName();

    @BindView(R2.id.cinema_all_place)
    CheckBox mPlace;

    @BindView(R2.id.cinema_all_series)
    CheckBox mSeries;

    @BindView(R2.id.cinema_default_range)
    CheckBox mRange;

    @BindView(R2.id.cinema_display_list_view)
    ListView mListView;

    @BindView(R2.id.custom_cinema_cover_view)
    CinemaCover mCinemaCover;

    @BindView(R2.id.cinema_cover_series)
    FrameLayout mSeriesContainer;

    @BindView(R2.id.cinema_cover_range)
    FrameLayout mRangeContainer;

    @BindView(R2.id.cinema_cover_control_hide)
    FrameLayout mCinemaCoverHide;


    private CinemaAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_cinema;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected void initView() {
        mCinemaCoverHide.setVisibility(View.GONE);
        mAdapter = new CinemaAdapter(getActivity(),null,R.layout.fragment_cinema_item);
        mListView.setAdapter(mAdapter);
        mListView.setTextFilterEnabled(true);

        String movieId = "";
        if (getArguments() != null){
            Bundle bundle = getArguments();
            movieId = (String) bundle.get(CinemaActivity.MOVIE_ID);
        }
        mPresenter.getCinemaListBeen(getQueryMap());
        mPresenter.getCinemaClassifyBeen(getClassifyMap(movieId));
    }

    private Map<String, String> getClassifyMap(String movieId) {
        //http://piao.163.com/m/base_data.html?city=110000
        Map<String,String> map = new HashMap<>();
        map.put("city","110000");
        map.put("movie_id",movieId);
        return map;
    }

    private Map<String, String> getQueryMap() {
        //movie_id=48123&city=110000&longitude=118.074705&page_num=&page_no=&latitude=36.810581&apiVer=21&apilevel=17
        Map<String,String> map = new HashMap<>();
        map.put("movie_id","48440");
        map.put("city","110000");
        map.put("apiVer","21");
        map.put("apilevel","17");
        map.put("longitude","118.074705");
        map.put("latitude","36.810581");
        return map;
    }

    @Override
    public void returnCinemaListBeen(CinemaListBeen cinemaListBeen) {
        //对加载回来的数据需要判断一下
        if (cinemaListBeen != null && cinemaListBeen.getRetcode() == 200){
            Log.e(TAG, "returnCinemaListBeen: " + cinemaListBeen.getCinemaList().size() );
            mAdapter.updateRes(cinemaListBeen.getCinemaList());
        }
    }

    @Override
    public void returnCinemaClassifyBeen(CinemaClassifyBeen cinemaClassifyBeen) {
        if (cinemaClassifyBeen != null && "200".equals(cinemaClassifyBeen.getRetcode())){
            Logger.e(cinemaClassifyBeen.getDistrictList().size() + "");

            //向CinemaCover发送消息
            CinemaEvent cinemaEvent = new CinemaEvent(EventWhat.CINEMA_CLASSIFY);
            cinemaEvent.setCinemaClassifyBeen(cinemaClassifyBeen);
            EventBus.getDefault().postSticky(cinemaEvent);
        }
    }

    @Override
    public void onStartLoad() {
        LoadingDialog.showDialogForLoading(getActivity(),"正在加载中...",true);
    }

    @Override
    public void onStopLoad() {
        LoadingDialog.cancelDialogForLoading();
    }

    @Override
    public void onError(String errorInfo) {
        Toast.makeText(getActivity(), "网络出错啦!", Toast.LENGTH_SHORT).show();
    }

    @OnCheckedChanged(value = {R2.id.cinema_all_place,R2.id.cinema_all_series,R2.id.cinema_default_range})
    public void onCheckChanged(CompoundButton compoundButton,boolean isChecked){
        Log.e(TAG, "onCheckChanged: " );
        if (isChecked){
            registerEvent();
            Log.e(TAG, "onCheckChanged: check" );
            mCinemaCoverHide.setVisibility(View.VISIBLE);
            switch (compoundButton.getId()) {
                case R.id.cinema_all_place:
                    Log.e(TAG, "onCheckChanged: cinema_all_place" );
                    openCoverDistrict();
                    break;
                case R.id.cinema_all_series:
                    Log.e(TAG, "onCheckChanged: cinema_all_series" );
                    openCoverSeries();
                    break;
                case R.id.cinema_default_range:
                    Log.e(TAG, "onCheckChanged: cinema_default_range" );
                    openCoverRange();
                    break;
            }
        }

        if (!mPlace.isChecked() && !mSeries.isChecked() && !mRange.isChecked()){
            unRegisterEvent();
            Log.e(TAG, "onCheckChanged: 没check" );
            mCinemaCoverHide.setVisibility(View.GONE);
        }
    }

    private void openCoverRange() {
        //cover 排序
        mCinemaCover.setVisibility(View.GONE);
        mSeriesContainer.setVisibility(View.GONE);
        mPlace.setChecked(false);
        mSeries.setChecked(false);
    }

    private void openCoverSeries() {
        //cover 类型
        mCinemaCover.setVisibility(View.GONE);
        mSeriesContainer.setVisibility(View.VISIBLE);
        mPlace.setChecked(false);
        mRange.setChecked(false);
    }

    private void openCoverDistrict() {
        //cover 地区
        mCinemaCover.setVisibility(View.VISIBLE);
        mSeries.setChecked(false);
        mRange.setChecked(false);
    }

    private void registerEvent(){
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    private void unRegisterEvent(){
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(sticky = true)
    public void onEvent(CinemaEvent cinemaEvent){
        switch (cinemaEvent.WHAT) {
            case EventWhat.CINEMA_DISTRICT_ID:
//                Logger.e("数据传递成功!" + cinemaEvent.getDistrictId());
//                mListView.setFilterText(cinemaEvent.getDistrictId());
                mAdapter.getFilter().filter(cinemaEvent.getDistrictId());//使用text过滤刷新listView显示
                mCinemaCoverHide.setVisibility(View.GONE);
                mPlace.setText(cinemaEvent.getDistrictName());
                break;
        }

    }

}
