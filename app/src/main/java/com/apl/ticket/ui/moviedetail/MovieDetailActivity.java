package com.apl.ticket.ui.moviedetail;

import android.animation.ObjectAnimator;
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
import android.view.MotionEvent;
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
import com.apl.ticket.ui.cinema.CinemaActivity;
import com.apl.ticket.ui.moviedetail.adapter.MovieDetailAdapter;
import com.apl.ticket.ui.moviedetail.adapter.StagePhotoAdapter;
import com.apl.ticket.ui.moviedetail.contract.MovieDetailContract;
import com.apl.ticket.ui.moviedetail.model.MovieDetailModel;
import com.apl.ticket.ui.moviedetail.presenter.MovieDetailPresenter;
import com.apl.ticket.widget.CustomVideoView;
import com.apl.ticket.widget.RatingBar;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;
import com.vittaw.mvplibrary.base.BaseActivity;
import com.vittaw.mvplibrary.utils.LoadingDialog;
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
    private ImageView mDescriptionImage;

    @BindView(R2.id.movie_detail_video_share)
    ImageView mVideoBack;

    @BindView(R2.id.movie_detail_video_view)
    CustomVideoView mVideoView;

    @BindView(R2.id.movie_detail_float)
    LinearLayout mBottomFloat;


    private View mVideoHeader;
    private int mVideoHeight;
    private boolean isLandscape;
    private RatingBar mRatingBar;
    private TextView mVideoTitle;
    private TextView mMovieTime;
    private TextView mVideoCountry;
    private ImageView mVideoImage;
    private TextView mDescriptionType;
    private TextView mDescriptionDuration;
    private TextView mDescriptionEditor;
    private String movieId;

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
        movieId = intent.getStringExtra(MOVIE_ID);
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
        mVideoTitle = ((TextView) mVideoHeader.findViewById(R.id.movie_detail_video_title));
        mMovieTime = ((TextView) mVideoHeader.findViewById(R.id.movie_detail_video_movie_time));
        mVideoCountry = ((TextView) mVideoHeader.findViewById(R.id.movie_detail_video_country));
        mVideoHeader.findViewById(R.id.movie_detail_play).setOnClickListener(this);
        mVideoHeader.findViewById(R.id.movie_detail_back).setOnClickListener(this);
        mVideoImage = ((ImageView) mVideoHeader.findViewById(R.id.movie_detail_video_image));
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE){
                    ObjectAnimator.ofFloat(mBottomFloat, "TranslationY", 1000,0).setDuration(2000).start();
                }else{
                    ObjectAnimator.ofFloat(mBottomFloat,"TranslationY",1000).setDuration(1000).start();
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
        mDescriptionImage = (ImageView) descriptionHeader.findViewById(R.id.movie_detail_image);
        mRatingBar = ((RatingBar) descriptionHeader.findViewById(R.id.rb));
        mDescriptionType = ((TextView) descriptionHeader.findViewById(R.id.movie_detail_description_type));
        mDescriptionDuration = ((TextView) descriptionHeader.findViewById(R.id.movie_detail_description_duration));
        mDescriptionEditor = ((TextView) descriptionHeader.findViewById(R.id.movie_detail_description_editor));

//        mRatingBar.setClickable(true);//设置可否点击
//        mRatingBar.setStar(2.5f);//设置显示的星星个数
//        mRatingBar.setStepSize(RatingBar.StepSize.Half);//设置每次点击增加一颗星还是半颗星
//        mRatingBar.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
//            @Override
//            public void onRatingChange(float ratingCount) {//点击星星变化后选中的个数
//                Log.d("RatingBar","RatingBar-Count="+ratingCount);
//            }
//        });

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
        Picasso.with(this).load(homeDetailBeen.getObject().getLogo556640()).into(mVideoImage);
        String releaseDate = homeDetailBeen.getObject().getReleaseDate();
        mMovieTime.setText(TimeUtil.converseReleaseTime(releaseDate) +"上映");
        mVideoCountry.setText("国家:  " + homeDetailBeen.getObject().getArea());
    }

    private void updateMovieDescription(HomeDetailBeen homeDetailBeen) {
        mActors.setText(homeDetailBeen.getObject().getActors());
        mDescrioption.setText(homeDetailBeen.getObject().getDescription());
        float rating = Float.parseFloat(homeDetailBeen.getObject().getGrade()) / 10;
        mRatingBar.setStar(rating);//设置显示的星星个数
        mDescriptionType.setText(homeDetailBeen.getObject().getCategory());
        mDescriptionDuration.setText("时长 : " + homeDetailBeen.getObject().getDuration());
        mDescriptionEditor.setText("导演 : " + homeDetailBeen.getObject().getDirector());
        Picasso.with(this).load(homeDetailBeen.getObject().getLogo2()).into(mDescriptionImage);
    }

    @Override
    public void returnHomeDetailComment(HomeDetailCommentBeen homeDetailCommentBeen) {
        Log.e(TAG, "returnHomeDetailComment: " );
        mListView.addHeaderView(inflater.inflate(R.layout.activity_movie_detail_header_comment,null,false));
        mAdapter.updateRes(homeDetailCommentBeen.getPosts());
    }

    @Override
    public void onStartLoad() {
        LoadingDialog.showDialogForLoading(this);
    }

    @Override
    public void onStopLoad() {
        LoadingDialog.cancelDialogForLoading();
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
            case R.id.movie_detail_play:
                //TODO 全屏,播放视频
                videoStart();
                break;
            case R.id.movie_detail_back:
                finish();
                break;
        }
    }

    @OnClick(value = {R2.id.movie_detail_video_back,R2.id.movie_detail_video_share,R2.id.movie_detail_float_buy})
    void onVideoClick(View v){
        switch (v.getId()) {
            case R.id.movie_detail_video_share:
                //TODO 分享 ,待实现
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.movie_detail_video_back:
                //返回竖屏
                videoStop();
                break;
            case R.id.movie_detail_float_buy:
                jumpToActivity();
                break;
        }

    }

    private void jumpToActivity() {
        Intent intent = new Intent(this, CinemaActivity.class);
        intent.putExtra(CinemaActivity.MOVIE_ID,movieId);
        startActivity(intent);
    }

    private void videoStop() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mVideoView.pause();
        mVideoContainer.getLayoutParams().height = mVideoHeight;
        isLandscape = false;

        mBottomFloat.setVisibility(View.VISIBLE);
        mVideoView.setMediaController(null);
        mVideoContainer.setVisibility(View.GONE);
    }

    private void videoStart() {
        mVideoContainer.setVisibility(View.VISIBLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mVideoView.start();
        //记录原videoView的高度
        mVideoHeight = mVideoContainer.getLayoutParams().height;
        mVideoContainer.getLayoutParams().height = FrameLayout.LayoutParams.MATCH_PARENT;
        isLandscape = true;

        mBottomFloat.setVisibility(View.GONE);
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
