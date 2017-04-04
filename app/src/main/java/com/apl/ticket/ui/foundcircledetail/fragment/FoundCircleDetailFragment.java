package com.apl.ticket.ui.foundcircledetail.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.apl.ticket.been.FoundCircleBoardBeen;
import com.apl.ticket.been.FoundCircleDetailPostBeen;
import com.apl.ticket.ui.foundcircledetail.FoundCircleDetailActivity;
import com.apl.ticket.ui.foundcircledetail.adapter.FoundCircleDetailAdapter;
import com.apl.ticket.ui.foundcircledetail.contract.CircleDetailContract;
import com.apl.ticket.ui.foundcircledetail.model.CircleDetailModel;
import com.apl.ticket.ui.foundcircledetail.presenter.CircleDetailPresenter;
import com.apl.ticket.ui.home.EventBus.EventWhat;
import com.apl.ticket.ui.home.EventBus.FoundEvent;
import com.orhanobut.logger.Logger;
import com.vittaw.mvplibrary.base.BaseFragment;
import com.vittaw.mvplibrary.utils.LoadingDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;


public class FoundCircleDetailFragment extends BaseFragment<CircleDetailModel, CircleDetailPresenter> implements CircleDetailContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R2.id.fragment_found_circle_detail_recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R2.id.fragment_found_circle_detail_swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    private FoundCircleBoardBeen.Board board;

    private FoundCircleDetailAdapter mAdapter;
    private static String mSort;
    private static final String DEFAULT_SORT = "hot";

    private boolean isFirstRequest = true;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_found_circle_detail;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {//onCreateView
        mSwipeRefresh.setOnRefreshListener(this);

        //设置适配器
        mAdapter = new FoundCircleDetailAdapter(getActivity(), null);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                if (!mAdapter.isLoadMore() && !mSwipeRefresh.isRefreshing() && mAdapter.getItemCount() == layoutManager.findLastVisibleItemPosition() + 1) {
                    mAdapter.setLoadMore(true);
                    mPresenter.getCircleDetailBeen(mSort, board.getBoardId(), "52484");//没啥规律,写死的下拉加载,应该是++page
                }
            }
        });
//        if (getActivity() instanceof FoundCircleDetailActivity){
//            ((FoundCircleDetailActivity) getActivity()).setOnSortChangeListener(new FoundCircleDetailActivity.OnSortChangeListener() {
//
//                @Override
//                public void onSortChanged(String sort) {
//                    Logger.e("popWindow选择后sort" + mSort);
//                    mSort = sort;
//                    mAdapter.setLoadMore(true);
//                    mPresenter.getCircleDetailBeen(mSort,board.getBoardId(),"");
//                }
//            });
//        }
        mPresenter.getCircleDetailBeen(DEFAULT_SORT, board.getBoardId(), "");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(sticky = true)
    public void onEvent(FoundEvent foundEvent) {
        switch (foundEvent.WHAT) {
            case EventWhat.FOUND_CIRCLE_BOARD:
                board = foundEvent.getBoard();
                break;
            case EventWhat.CIRCLE_SORT:
                mSort = foundEvent.getSort();
                Logger.e("popWindow选择后sort" + mSort);
                mPresenter.getCircleDetailBeen(mSort, board.getBoardId(), "");
                break;
        }

    }

    @Override
    public void returnCircleDetailBeen(FoundCircleDetailPostBeen circleDetailPostBeen) {
        Logger.e("圈子详情评论页面:" + circleDetailPostBeen.getPosts().size());
        if (mAdapter.isLoadMore()) {
            mAdapter.addResAll(circleDetailPostBeen.getPosts());
        } else {
            mAdapter.updateResAll(circleDetailPostBeen.getPosts());
        }

    }

    @Override
    public void onStartLoad() {
        mSwipeRefresh.setRefreshing(true);
        Logger.e("onStartLoad");
    }

    @Override
    public void onStopLoad() {
        mSwipeRefresh.setRefreshing(false);
        mAdapter.setLoadMore(false);
        Logger.e("onStopLoad");
    }

    @Override
    public void onError(String errorInfo) {
        Toast.makeText(getActivity(), errorInfo, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onRefresh() {
        Logger.e("onRefresh");
        if (TextUtils.isEmpty(mSort)) {
            mSort = DEFAULT_SORT;
        }
        mPresenter.getCircleDetailBeen(mSort, board.getBoardId(), "");
    }
}
