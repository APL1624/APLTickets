package com.apl.ticket.ui.home.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.apl.ticket.been.mine.MineInfoBean;
import com.apl.ticket.ui.home.adapter.MineAdapter;
import com.apl.ticket.ui.home.contract.MineContract;
import com.apl.ticket.ui.home.model.MineModel;
import com.apl.ticket.ui.home.presenter.MinePresenter;
import com.squareup.picasso.Picasso;
import com.vittaw.mvplibrary.base.BaseFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static android.content.Context.MODE_PRIVATE;

/**
 * TODO 加泛型网络加载数据
 */

public class MineFragment extends BaseFragment<MineModel, MinePresenter> implements MineContract.View {

    public static final String TAG = MineFragment.class.getName();
    @BindView(R2.id.fragment_home_mine_lv)
    ListView mMineLv;
    private MineAdapter mAdapter;
    private List<MineInfoBean> mMineData;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_mine;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        mMineData = new ArrayList<>();
        mAdapter = new MineAdapter(getActivity(), mMineData, R.layout.fragment_home_mine_item_login, R.layout.fragment_home_mine_item_info,
                R.layout.fragment_home_mine_item_hobby, R.layout.fragment_home_mine_item_setting_or_share);
        mMineLv.setAdapter(mAdapter);
        mPresenter.getMineInfoBean();

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
    public void setMineInfoBean(List<MineInfoBean> mMineData) {
        this.mMineData = mMineData;
        mAdapter.updateRes(mMineData);
        SharedPreferences data = getActivity().getSharedPreferences("data", MODE_PRIVATE);
        if (data != null) {
            String imageUrl = data.getString("imageUrl", null);
            if (imageUrl != null && mMineData.size() > 2 && mAdapter != null) {
                mMineData.get(0).setLogInImag(imageUrl);
                mAdapter.updateRes(mMineData);
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences data = getActivity().getSharedPreferences("data", MODE_PRIVATE);
        if (data != null) {
            String imageUrl = data.getString("imageUrl", null);
            if (imageUrl != null && mMineData.size() > 2 && mAdapter != null) {
                mMineData.get(0).setLogInImag(imageUrl);
                mAdapter.updateRes(mMineData);
            }
        }


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
