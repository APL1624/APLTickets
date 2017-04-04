package com.apl.ticket.ui.moviedetail;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.apl.ticket.been.HomeDetailBeen;
import com.apl.ticket.been.detailcomment.HomeDetailCommentBeen;
import com.apl.ticket.ui.moviedetail.adapter.MovieDetailAdapter;
import com.apl.ticket.ui.moviedetail.adapter.StagePhotoAdapter;
import com.apl.ticket.ui.moviedetail.contract.MovieDetailContract;
import com.apl.ticket.ui.moviedetail.model.MovieDetailModel;
import com.apl.ticket.ui.moviedetail.presenter.MovieDetailPresenter;
import com.apl.ticket.widget.CustomVideoView;
import com.squareup.picasso.Picasso;
import com.vittaw.mvplibrary.base.BaseActivity;
import com.vittaw.mvplibrary.utils.ScreenUtil;
import com.vittaw.mvplibrary.utils.TimeUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class MovieDetailActivity extends BaseActivity<MovieDetailPresenter,MovieDetailModel> implements MovieDetailContract.View, View.OnClickListener {

    private static final String TAG = MovieDetailActivity.class.getSimpleName();

    public static final String ACCOUNT_ID = "account_id";
    public static final String MOVIE_ID = "movie_id";
    public static final String CITY = "city";

    @BindView(R2.id.movie_detail_list_view)
    ListView mListView;

    @BindView(R2.id.movie_detail_video_container)
    FrameLayout mVideoContainer;


    private LayoutInflater inflater;
    private MovieDetailAdapter mAdapter;
    private StagePhotoAdapter mStagePhotoAdapter;
    private TextView mActors;
    private TextView mDescrioption;
    private ImageView mOpenDescription;
    private View mDescriptionImage;

    @BindView(R2.id.movie_detail_video_share)
    ImageView mVideoBack;

    @BindView(R2.id.movie_detail_video_play)
    ImageView mVideoPlay;

    @BindView(R2.id.movie_detail_video_view)
    CustomVideoView mVideoView;

    @BindView(R2.id.movie_detail_video_title)
    TextView mVideoTitle;

    @BindView(R2.id.movie_detail_video_movie_time)
    TextView mMovieTime;

    @BindView(R2.id.movie_detail_video_country)
    TextView mVideoCountry;

//    @BindView(R2.id.movie_detail_video_share)
//    ImageView mVideoShare;

//    @BindView(R2.id.movie_detail_video_summary)
//    LinearLayout mVideoSummary;

    @BindView(R2.id.movie_detail_float)
    LinearLayout mBottomFloat;

    @BindView(R2.id.movie_detail_video_image)
    ImageView mVideoImageSmall;


    private View mVideoHeader;
    private int mVideoHeight;
    private boolean isLandscape;

    @Override
    public int getLayoutId() {
        return R.layout.activity_movie_detail;
    }

    @Override
    public void initPresenter() {
        inflater = LayoutInflater.from(this);
        mPresenter.setVM(this,mModel);
    }

    @Override
    public void initView() {
        //①video header
        initVideoHeader();
        //②description header
        initDescriptionHeader();
        //③图片展示header
        initStagePhotoHeader();

        //④评论的listView
        mAdapter = new MovieDetailAdapter(this,null,R.layout.activity_movie_detail_item);
        mListView.setAdapter(mAdapter);


        //TODO accountId city
        Intent intent = getIntent();
        String movieId = intent.getStringExtra(MOVIE_ID);
        mPresenter.getHomeDetail("",movieId,"110000");
        Map<String, String> map = createQuaryMap(movieId);
        mPresenter.getHomeDetailComment(map);
        Log.e(TAG, "initView: " );
    }

    @NonNull
    private Map<String, String> createQuaryMap(String movieId) {
        Map<String,String> map = new HashMap<>();
        map.put("login_id","93A1FEFE25A4F773F9EE7598460DF9B88B5C4DC1D033E37CBD34A2748A129E68C571DD9B5A63E9C62405FE76B7CFD03A");
        map.put("sinceId","0");
        map.put("sort","time");
        map.put("count","20");
        map.put("req_type","movie");
        map.put("req_id",movieId);
        map.put("login_token","");
        map.put("topAttach","true");
        map.put("maxId","0");
        map.put("city","110000");
        return map;
    }

    private void initVideoHeader() {
        mVideoHeader = inflater.inflate(R.layout.activity_movie_detail_header_video,null,false);
        mListView.addHeaderView(mVideoHeader);
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE){
                    Log.e(TAG, "onScrollStateChanged: " + mBottomFloat );
                    mBottomFloat.setVisibility(View.VISIBLE);
                }else{
                    mBottomFloat.setVisibility(View.GONE);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //TODO 跟随滚动
            }
        });
    }

    private void initStagePhotoHeader() {
        View photoHeader = inflater.inflate(R.layout.activity_movie_detail_header_stagephoto, null, false);
        mListView.addHeaderView(photoHeader);
        RecyclerView recyclerView = (RecyclerView) photoHeader.findViewById(R.id.movie_detail_header_stagephoto_recycler);
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mStagePhotoAdapter = new StagePhotoAdapter(this,null);
        recyclerView.setAdapter(mStagePhotoAdapter);
    }

    private void initDescriptionHeader() {
        View descriptionHeader = inflater.inflate(R.layout.activity_movie_detail_header_description, null, false);
        mListView.addHeaderView(descriptionHeader);
        mActors = (TextView) descriptionHeader.findViewById(R.id.movie_detail_actors);
        mDescrioption = (TextView) descriptionHeader.findViewById(R.id.movie_detail_description);
        mOpenDescription = (ImageView) descriptionHeader.findViewById(R.id.movie_detail_open_description);
        mOpenDescription.setOnClickListener(this);
        mDescriptionImage = descriptionHeader.findViewById(R.id.movie_detail_image);
    }

    @Override
    public void returnHomeDetailBeen(HomeDetailBeen homeDetailBeen) {
        Log.e(TAG, "returnHomeDetailBeen: " );
        //更新UI显示
        List<HomeDetailBeen.HDPData.HDPDLogo> photos = homeDetailBeen.getObject().getStillsList();
        mStagePhotoAdapter.updateRes(photos);
        updateMovieDescription(homeDetailBeen);
        updateMovieVideoHeader(homeDetailBeen);
    }

    private void updateMovieVideoHeader(HomeDetailBeen homeDetailBeen) {
        Log.e(TAG, "updateMovieVideoHeader: " );
        if (homeDetailBeen.getObject().getMobilePreview() != null){
            mVideoView.setVideoURI(Uri.parse(homeDetailBeen.getObject().getMobilePreview()));
        }
        mVideoTitle.setText(homeDetailBeen.getObject().getName());
        Picasso.with(this).load(homeDetailBeen.getObject().getLogo556640()).into(mVideoImageSmall);
        String releaseDate = homeDetailBeen.getObject().getReleaseDate();
        mMovieTime.setText(TimeUtil.converseReleaseTime(releaseDate) +"上映");
        mVideoCountry.setText("国家:  " + homeDetailBeen.getObject().getArea());
    }

    private void updateMovieDescription(HomeDetailBeen homeDetailBeen) {
        mActors.setText(homeDetailBeen.getObject().getActors());
        mDescrioption.setText(homeDetailBeen.getObject().getDescription());
    }

    @Override
    public void returnHomeDetailComment(HomeDetailCommentBeen homeDetailCommentBeen) {
        mListView.addHeaderView(inflater.inflate(R.layout.activity_movie_detail_header_comment,null,false));
        mAdapter.updateRes(homeDetailCommentBeen.getPosts());
    }

    @Override
    public void onStartLoad() {

    }

    @Override
    public void onStopLoad() {

    }

    @Override
    public void onError(String errorInfo) {
        Log.e(TAG, "onError: " + errorInfo );
    }


    private boolean isArrowDown;//默认是false(模仿checkBox 设置个标记)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.movie_detail_open_description:
                if (isArrowDown){
                    //展开电影描述
                    mDescrioption.setMaxLines(100);
                    isArrowDown = false;
                }else{
                    mDescrioption.setMaxLines(2);
                    isArrowDown = true;
                }
                break;
        }
    }

    @OnClick(value = {R2.id.movie_detail_video_play,R2.id.movie_detail_video_back,R2.id.movie_detail_video_share})
    void onVideoClick(View v){
        switch (v.getId()) {
            case R.id.movie_detail_video_play:
                //TODO 全屏,播放视频
                videoStart();
                break;
            case R.id.movie_detail_video_share:
                //TODO 分享 ,待实现
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.movie_detail_video_back:
                if (isLandscape){
                    //返回竖屏
                    videoStop();
                }else{
                    finish();
                }
                break;
        }

    }

    private void videoStop() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mVideoView.pause();
        mVideoContainer.getLayoutParams().height = mVideoHeight;
        isLandscape = false;
//        mVideoShare.setVisibility(View.VISIBLE);
        mVideoPlay.setVisibility(View.VISIBLE);
        mVideoImageSmall.setVisibility(View.VISIBLE);
        mBottomFloat.setVisibility(View.VISIBLE);
//        mVideoSummary.setVisibility(View.VISIBLE);
        mVideoView.setMediaController(null);
    }

    private void videoStart() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mVideoView.start();
        //记录原videoView的高度
        mVideoHeight = mVideoContainer.getLayoutParams().height;
        mVideoContainer.getLayoutParams().height = FrameLayout.LayoutParams.MATCH_PARENT;
        isLandscape = true;

//        mVideoShare.setVisibility(View.GONE);
        mVideoPlay.setVisibility(View.GONE);
        mVideoImageSmall.setVisibility(View.GONE);
        mBottomFloat.setVisibility(View.GONE);
//        mVideoSummary.setVisibility(View.GONE);
        mVideoView.setMediaController(new MediaController(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoView == null){
            mVideoView = null;
        }
        Log.e(TAG, "onDestroy: " );
    }

    @Override
    public void onBackPressed() {
        if (isLandscape){
            videoStop();
        }else{
            finish();
        }
    }


}
