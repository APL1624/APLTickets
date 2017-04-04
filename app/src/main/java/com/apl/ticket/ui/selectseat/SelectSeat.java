package com.apl.ticket.ui.selectseat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.apl.ticket.ui.selectseat.contract.SelectSeatContract;
import com.apl.ticket.ui.selectseat.model.SelectSeatModel;
import com.apl.ticket.ui.selectseat.presenter.SelectSeatPresenter;
import com.qfdqc.views.seattable.SeatTable;
import com.vittaw.mvplibrary.base.BaseActivity;

import butterknife.BindView;

public class SelectSeat extends BaseActivity<SelectSeatPresenter,SelectSeatModel>implements SelectSeatContract.View, View.OnClickListener {
    @BindView(R2.id.select_seat_view)
    SeatTable mSelectSeatView;
    @BindView(R.id.seat_head_back)
    ImageView mIamgeBack;

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_seat;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    public void initView() {
        mSelectSeatView.setScreenName("20");
        mSelectSeatView.setMaxSelected(5);
        mSelectSeatView.setSeatChecker(new SeatTable.SeatChecker() {
            @Override
            public boolean isValidSeat(int row, int column) {
                if(column==2||column==6)
                    return false;
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {
                if ((row==8&&column==9)||(row==5&&column==1)) {
                    return true;
                }

                return false;
            }

            @Override
            public void checked(int row, int column) {

            }

            @Override
            public void unCheck(int row, int column) {

            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return null;
            }
        });
        mSelectSeatView.setData(10,10);
        mIamgeBack.setOnClickListener(this);

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
    public void onClick(View view) {
        finish();
    }
}
