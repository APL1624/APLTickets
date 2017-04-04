package com.apl.ticket.ui.foundcircledetail;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.apl.ticket.ui.foundcircledetail.fragment.FoundCircleDetailFragment;
import com.apl.ticket.ui.home.EventBus.EventWhat;
import com.apl.ticket.ui.home.EventBus.FoundEvent;
import com.orhanobut.logger.Logger;
import com.vittaw.mvplibrary.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class FoundCircleDetailActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    @BindView(R2.id.found_circle_detail_container)
    FrameLayout mContainer;

    @BindView(R2.id.found_circle_detail_title_text)
    TextView mSortText;

    @BindView(R2.id.found_circle_detail_title_image)
    ImageView mSortImage;

    @BindView(R2.id.found_circle_detail_title)
    LinearLayout mSortLayout;
    private LayoutInflater layoutInflater;
    private PopupWindow popupWindow;


    @Override
    public int getLayoutId() {
        return R.layout.activity_found_circle_detail;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        layoutInflater = LayoutInflater.from(this);
        View contentView = layoutInflater.inflate(R.layout.found_circle_detail_pop, null, false);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        RadioButton hot = (RadioButton) contentView.findViewById(R.id.found_circle_detail_replay);
        hot.setOnCheckedChangeListener(this);
        hot.setChecked(true);
        ((RadioButton) contentView.findViewById(R.id.found_circle_detail_publish)).setOnCheckedChangeListener(this);
        ((RadioButton) contentView.findViewById(R.id.found_circle_detail_interest)).setOnCheckedChangeListener(this);


        mSortText.setText("最后回复");
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.found_circle_detail_container,new FoundCircleDetailFragment());
        transaction.commit();
    }

    private boolean isPopOpen;
    @OnClick(value = {R2.id.found_circle_detail_title,R2.id.found_circle_detail_back})
    public void onClick (View view){
        switch (view.getId()) {
            case R.id.found_circle_detail_title:
                if (isPopOpen){
                    popClose();
                }else{
                    popUp();
                }
                break;
            case R.id.found_circle_detail_back:
                finish();
                break;
        }
    }

    private void popClose() {
        isPopOpen = false;
        popupWindow.dismiss();
    }

    private void popUp() {
        isPopOpen = true;
        popupWindow.showAsDropDown(mSortLayout);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked){
            FoundEvent event = new FoundEvent(EventWhat.CIRCLE_SORT);
            switch (buttonView.getId()) {
                case R.id.found_circle_detail_replay:
                    //TODO 刷新fragment的recyclerView
                    event.setSort("hot");
                    break;
                case R.id.found_circle_detail_publish:
                    event.setSort("time");
                    break;
                case R.id.found_circle_detail_interest:
                    event.setSort("follow");
                    break;
            }
            EventBus.getDefault().post(event);
            popClose();
        }
    }

}
