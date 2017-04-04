package com.apl.ticket.ui.home.fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.apl.ticket.been.FoundCircleBoardBeen;
import com.apl.ticket.ui.foundcircledetail.FoundCircleDetailActivity;
import com.apl.ticket.ui.home.EventBus.EventWhat;
import com.apl.ticket.ui.home.EventBus.FoundEvent;
import com.apl.ticket.ui.home.adapter.FoundCircleAdapter;
import com.apl.ticket.ui.home.adapter.MovieHotAdapter;
import com.apl.ticket.ui.home.contract.FoundCircleContract;
import com.apl.ticket.ui.home.decoration.FoundCircleDecoration;
import com.apl.ticket.ui.home.model.FoundCircleModel;
import com.apl.ticket.ui.home.presenter.FoundCirclePresenter;
import com.orhanobut.logger.Logger;
import com.vittaw.mvplibrary.base.BaseFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/31.
 */

public class FoundCircleFragment extends BaseFragment<FoundCircleModel,FoundCirclePresenter> implements FoundCircleContract.View, FoundCircleAdapter.OnItemClickListener {

    public static final String TAG = FoundCircleFragment.class.getName();

    @BindView(R2.id.found_circle_recycler_view)
    RecyclerView mRecyclerView;

    private FoundCircleAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.found_cricle_fragment;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected void initView() {
        mAdapter = new FoundCircleAdapter(getActivity(),null);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new FoundCircleDecoration());
        mAdapter.setOnItemClickListener(this);
        mPresenter.getFoundCircleBoardBeen();
    }

    @Override
    public void returnFoundCircleBoardBeen(FoundCircleBoardBeen foundCircleBoardBeen) {
        mAdapter.updateResAll(foundCircleBoardBeen.getBoards());
    }

    @Override
    public void onStartLoad() {

    }

    @Override
    public void onStopLoad() {

    }

    @Override
    public void onError(String errorInfo) {
        Toast.makeText(getActivity(), errorInfo, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(RecyclerView parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), FoundCircleDetailActivity.class);
        startActivity(intent);
        FoundEvent event = new FoundEvent(EventWhat.FOUND_CIRCLE_BOARD);
        event.setBoard(((FoundCircleAdapter) mRecyclerView.getAdapter()).getItem(position));
        EventBus.getDefault().postSticky(event);
    }



}
