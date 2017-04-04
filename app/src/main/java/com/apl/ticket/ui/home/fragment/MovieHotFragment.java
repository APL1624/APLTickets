package com.apl.ticket.ui.home.fragment;

import android.app.ProgressDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.apl.ticket.been.HomePageBeen;
import com.apl.ticket.ui.home.EventBus.EventWhat;
import com.apl.ticket.ui.home.EventBus.MovieHotLargeEvent;
import com.apl.ticket.ui.home.adapter.MovieHotAdapter;
import com.apl.ticket.ui.home.contract.MovieHotContract;
import com.apl.ticket.ui.home.model.HomePageModel;
import com.apl.ticket.ui.home.presenter.HomePagePresenter;
import com.vittaw.mvplibrary.base.BaseFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

public class MovieHotFragment extends BaseFragment<HomePageModel,HomePagePresenter> implements MovieHotContract.View {

    public static final String TAG = MovieHotFragment.class.getName();
    private ProgressDialog progressDialog;

    @BindView(R2.id.home_movie_hot_recycler_view)
    RecyclerView mRecyclerView;

    private String city = "110000";
    private String type = "0";
    private MovieHotAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_movie_hot;
    }

    @Override
    protected void initPresenter() {
        //将泛型上的Mode,和实现的接口View 传到presenter中,presenter需要持有model和view
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected void initView() {
        //设置适配器
        mAdapter = new MovieHotAdapter(getActivity(),null);
        mRecyclerView.setAdapter(mAdapter);

        //网络获取数据
        mPresenter.getHomePageBeen(String.valueOf(type),String.valueOf(city));
    }

    @Override
    public void returnHomePageBeen(HomePageBeen homePageBeen) {
        Log.e(TAG, "returnHomePageBeen: " + homePageBeen.getList().size() );

        //发送sticky的event
        MovieHotLargeEvent movieHotLargeEvent = new MovieHotLargeEvent(EventWhat.GET_HOME_PAGE_BEEN);
        movieHotLargeEvent.setHomePageBeen(homePageBeen);
        EventBus.getDefault().postSticky(movieHotLargeEvent);

        mAdapter.updateResAll(homePageBeen.getList());
    }

    @Override
    public void onStartLoad() {
        //TODO 后面还有一个theme字段,可以设置样式吧,有时间探究一下
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setIndeterminate(true);//不确定的,无限循环的
        progressDialog.setMessage("正在加载中...");
        progressDialog.setCancelable(true);//设置为可以取消的
//        progressDialog.setFeatureDrawable();//自定义的方法...
        progressDialog.show();
    }

    @Override
    public void onStopLoad() {
        progressDialog.cancel();
    }

    @Override
    public void onError(String errorInfo) {
        Toast.makeText(getActivity(), errorInfo, Toast.LENGTH_SHORT).show();
    }



}
