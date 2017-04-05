package com.apl.ticket.ui.home.fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.apl.ticket.been.HomePageBeen;
import com.apl.ticket.ui.home.EventBus.EventWhat;
import com.apl.ticket.ui.home.EventBus.MovieHotLargeEvent;
import com.apl.ticket.ui.home.adapter.MovieHotAdapter;
import com.apl.ticket.ui.home.adapter.MoviePreviewAdapter;
import com.apl.ticket.ui.home.contract.MovieHotContract;
import com.apl.ticket.ui.home.model.HomePageModel;
import com.apl.ticket.ui.home.presenter.HomePagePresenter;
import com.vittaw.mvplibrary.base.BaseFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * FQC,cry,cry
 */

public class MoviePreviewFragment extends BaseFragment<HomePageModel, HomePagePresenter> implements MovieHotContract.View, AbsListView.OnScrollListener {


    public static final String TAG = MoviePreviewFragment.class.getName();

    @BindView(R2.id.movie_preview_lv)
    ListView mListView;
    private String city = "110000";
    private String type = "1";
    private MoviePreviewAdapter mAdapter;
    HomePageBeen homePageBeen;
    @BindView(R.id.pre_head_text)
    TextView mHeadText;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_movie_preview;
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    protected void initView() {
        //设置适配器
        mAdapter = new MoviePreviewAdapter(getActivity(), null, R.layout.fragment_movie_preview_item);
        mListView.setAdapter(mAdapter);

        //网络获取数据
        mPresenter.getHomePageBeen(String.valueOf(type), String.valueOf(city));
    }

    @Override
    public void returnHomePageBeen(HomePageBeen homePageBeen) {
        Log.e(TAG, "returnHomePageBeen: " + homePageBeen.getList().size());
        mAdapter.updateRes(homePageBeen.getList());

        MovieHotLargeEvent movieHotLargeEvent = new MovieHotLargeEvent(EventWhat.GET_HP_DATA);
        movieHotLargeEvent.setHomePageBeen(homePageBeen);
        EventBus.getDefault().postSticky(movieHotLargeEvent);

        mListView.setOnScrollListener(this);
        this.homePageBeen = homePageBeen;
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
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
        if (i != 0) {
            if (i == 3 || !TextUtils.equals(mAdapter.getItem(i).getReleaseDate().substring(5, 7), mAdapter.getItem(i - 1).getReleaseDate().substring(5, 7))
                    || !TextUtils.equals(mAdapter.getItem(i).getReleaseDate().substring(5, 7), mAdapter.getItem(i + 1).getReleaseDate().substring(5, 7))) {
                int sum = 0;
                for (int j = 0; j < homePageBeen.getList().size(); j++) {
                    if (mAdapter.getItem(i).getReleaseDate().substring(5, 7).equals(homePageBeen.getList().get(j).getReleaseDate().substring(5, 7))) {
                        sum++;
                    } else {
                    }
                }
                mHeadText.setText((mAdapter.getItem(i).getReleaseDate().substring(5, 7) + "月上映 （" + sum + "）部"));
            }
        } else {
            mHeadText.setText("最受关注电影");
        }
    }
}
