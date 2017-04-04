package com.apl.ticket.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.apl.ticket.been.CinemaClassifyBeen;
import com.apl.ticket.ui.home.EventBus.CinemaEvent;
import com.apl.ticket.ui.home.EventBus.EventWhat;
import com.apl.ticket.ui.location.view.LetterView;
import com.apl.ticket.widget.coveradapter.LeftListAdapter;
import com.apl.ticket.widget.coveradapter.RightListAdapter;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnItemClick;

/**
 * Created by Administrator on 2017/3/28 0028.
 */

public class CinemaCover extends FrameLayout  {

    private LayoutInflater inflater;

    private Context context;

    public static final String TAG = CinemaCover.class.getSimpleName();

    @BindView(R2.id.cinema_cover_business)
    RadioButton mBusiness;

    @BindView(R2.id.cinema_cover_underground)
    RadioButton mUnderGround;

    @BindView(R2.id.cinema_cover_list_left)
    ListView mListViewLeft;

    @BindView(R2.id.cinema_cover_list_right)
    ListView mListViewRight;
    private LeftListAdapter mLeftAdapter;
    private RightListAdapter mRightAdapter;

    @BindView(R2.id.cinema_cover_letter_view)
    LetterView mLetterView;

    @BindView(R2.id.cinema_cover_center_letter)
    TextView mLetterText;

    public CinemaCover(Context context) {
        this(context,null);
    }

    public CinemaCover(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CinemaCover(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.cinema_layout_cover,this,true);
        //自定义View中的View绑定
        ButterKnife.bind(this);
//        View view = inflater.inflate(R.layout.cinema_layout_cover, null, false);
//        addView(view);//两种方式都可以
        mBusiness.setChecked(true);
    }


    @OnCheckedChanged(value = {R2.id.cinema_cover_business,R2.id.cinema_cover_underground})
    public void onCheckChanged(CompoundButton buttonView, boolean isChecked){
        if (isChecked){
            switch (buttonView.getId()) {
                case R.id.cinema_cover_business:
                    mBusiness.setTextColor(getResources().getColor(R.color.colorOrange));
                    mUnderGround.setTextColor(getResources().getColor(R.color.color_black));
                    break;
                case R.id.cinema_cover_underground:
                    mBusiness.setTextColor(getResources().getColor(R.color.color_black));
                    mUnderGround.setTextColor(getResources().getColor(R.color.colorOrange));
                    break;
            }
        }
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        switch (changedView.getId()) {
            case R.id.custom_cinema_cover_view:
                if (visibility == VISIBLE){//可见状态

                    if (!EventBus.getDefault().isRegistered(this)){
                        EventBus.getDefault().register(this);
                    }


                }else{

                    if (EventBus.getDefault().isRegistered(this)){
                        EventBus.getDefault().unregister(this);
                    }

                }
                break;
        }
    }

    @Subscribe(sticky = true)
    public void onCinemaEvent(CinemaEvent cinemaEvent){
        switch (cinemaEvent.WHAT) {
            case EventWhat.CINEMA_CLASSIFY:
                CinemaClassifyBeen cinemaClassifyBeen = cinemaEvent.getCinemaClassifyBeen();
                if (cinemaClassifyBeen != null){
//                    Logger.e("地区的数量:" + cinemaClassifyBeen.getDistrictList().size()+"");
                    initLeftListView(cinemaClassifyBeen);
                    initRightListView(cinemaClassifyBeen);
                }
                break;
        }
    }

    private void initRightListView(CinemaClassifyBeen cinemaClassifyBeen) {
        mRightAdapter = new RightListAdapter(context,cinemaClassifyBeen.getCircleList(),R.layout.cinema_cover_list_right_item);
        mListViewRight.setAdapter(mRightAdapter);
        mListViewRight.setTextFilterEnabled(true);//开启文字过滤器
        mLetterView.setLetter(mLetterText);
        mLetterView.setTypeListener(new LetterView.TypeListener() {
            @Override
            public void mathType(String Type) {//A,B,C...
                Map<String, Integer> letterPositionMap = mRightAdapter.getLetterPositionMap();
                Integer position = letterPositionMap.get(Type);
                if(position != null){
                    mListViewRight.setSelection(position);
                }
            }
        });
    }

    private void initLeftListView(CinemaClassifyBeen cinemaClassifyBeen) {
        List<CinemaClassifyBeen.District> districtList = cinemaClassifyBeen.getDistrictList();
        mLeftAdapter = new LeftListAdapter(context, transformData(districtList), R.layout.cinema_cover_list_left_item);
        mListViewLeft.setAdapter(mLeftAdapter);
    }

    private List<CinemaClassifyBeen.District> transformData(List<CinemaClassifyBeen.District> data) {
        //添加一条数据
        List<CinemaClassifyBeen.District> transformData = new ArrayList<>();
        CinemaClassifyBeen.District district = new CinemaClassifyBeen.District();
        district.setId("circle");
        district.setName("全部商圈");
        transformData.add(district);
        for (int i = 0; i < data.size(); i++) {
            transformData.add(data.get(i));
        }
        return transformData;
    }

    @OnItemClick(value = {R2.id.cinema_cover_list_left,R2.id.cinema_cover_list_right})
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        switch (parent.getId()) {
            case R.id.cinema_cover_list_left:
                //TODO 这个地方可以用RecyclerView代替ListView进行优化,因为每一次点击,都notifyAll...刚开始没想到
                mLeftAdapter.setSelectItem(position);
                mLeftAdapter.notifyDataSetChanged();
//                Logger.e("position:" + position);
                if (position == 0){
                    mRightAdapter.getFilter().filter("");
                    mLetterView.setVisibility(VISIBLE);
                }else{
                    //TODO 过滤右边的listView
                    String districtId = mLeftAdapter.getItem(position).getId();
//                    mListViewRight.setFilterText(districtId);//根据districtId去过滤
                    Filter filter = mRightAdapter.getFilter();
                    filter.filter(districtId);
                    mLetterView.setVisibility(GONE);
                }

                break;
            case R.id.cinema_cover_list_right:
                //TODO 过滤cover下面的ListView - districtId
                CinemaClassifyBeen.Circle circle = mRightAdapter.getItem(position);//商圈
                String districtId = circle.getDistrictId();
                //发送到fragment
                CinemaEvent cinemaEvent = new CinemaEvent(EventWhat.CINEMA_DISTRICT_ID);
                cinemaEvent.setDistrictId(districtId);
                cinemaEvent.setDistrictName(circle.getName());
                EventBus.getDefault().postSticky(cinemaEvent);
                break;
        }

    }
}
