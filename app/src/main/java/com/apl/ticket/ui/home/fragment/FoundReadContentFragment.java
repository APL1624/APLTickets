package com.apl.ticket.ui.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.apl.ticket.been.FoundReadBeen;
import com.apl.ticket.ui.home.adapter.FoundReadAdapter;
import com.apl.ticket.ui.home.contract.FoundReadContentContract;
import com.apl.ticket.ui.home.model.FoundReadContentModel;
import com.apl.ticket.ui.home.presenter.FoundReadContentPresenter;
import com.apl.ticket.ui.read.ReadActivity;
import com.orhanobut.logger.Logger;
import com.vittaw.mvplibrary.base.BaseFragment;
import com.vittaw.mvplibrary.utils.LoadingDialog;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;


public class FoundReadContentFragment extends BaseFragment<FoundReadContentModel,FoundReadContentPresenter> implements FoundReadContentContract.View, FoundReadAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG_ID = "tagId";

    @BindView(R2.id.found_read_recycler_view)
    RecyclerView mRecyclerView;
    private FoundReadAdapter mAdapter;

    @BindView(R2.id.found_read_swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    private int currentPage = 1;
    private String tagId;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_found_read_content;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected void initView() {
        mSwipeRefresh.setOnRefreshListener(this);
        mAdapter = new FoundReadAdapter(getActivity(),null);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                if (!mAdapter.isLoadMore() && !mSwipeRefresh.isRefreshing() && mAdapter.getItemCount() - 1 == layoutManager.findLastVisibleItemPosition()){
                    mAdapter.setLoadMore(true);
                    mPresenter.getFoundReadBeen(createMap(++currentPage,tagId));
                }
            }
        });

        Bundle bundle = getArguments();
        tagId = (String) bundle.get(TAG_ID);
        Logger.e("tagId" + tagId);
        currentPage = 1;
        mPresenter.getFoundReadBeen(createMap(currentPage,tagId));
    }

    private Map<String, String> createMap(int currentPage,String tagId) {
        /**
         * http://piao.163.com
         * /m/movie/articleList.html
         * ?recordPerPage=20&tagId=&apiVer=21&apilevel=17&currentPage=1&city=110000
         */
        Map<String,String> map = new HashMap<>();
        map.put("recordPerPage","20");
        map.put("tagId",tagId);
        map.put("currentPage",String.valueOf(currentPage));
        map.put("city","110000");
        return map;
    }

    @Override
    public void returnFoundReadBeen(FoundReadBeen foundReadBeen) {
        if (mAdapter.isLoadMore()){
            mAdapter.addResAll(foundReadBeen.getArticleList());
        }else{
            mAdapter.updateResAll(foundReadBeen.getArticleList());
        }

    }

    @Override
    public void onStartLoad() {
        mSwipeRefresh.setRefreshing(true);
    }

    @Override
    public void onStopLoad() {
        mSwipeRefresh.setRefreshing(false);
        mAdapter.setLoadMore(false);
    }

    @Override
    public void onError(String errorInfo) {
        Toast.makeText(getActivity(), errorInfo, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(RecyclerView parent, View view, int position, long id) {
        //TODO 跳转activty webView
        Intent intent = new Intent(getActivity(), ReadActivity.class);
        String clickUrl = mAdapter.getItem(position).getClickUrl();
        intent.putExtra(ReadActivity.URL_TAG,clickUrl);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        currentPage = 1;
        mPresenter.getFoundReadBeen(createMap(currentPage,tagId));
    }
}
