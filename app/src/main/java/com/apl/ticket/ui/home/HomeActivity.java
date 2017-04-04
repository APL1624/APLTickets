package com.apl.ticket.ui.home;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.apl.ticket.ui.home.fragment.CinemaFragment;
import com.apl.ticket.ui.home.fragment.FoundFragment;
import com.apl.ticket.ui.home.fragment.MineFragment;
import com.apl.ticket.ui.home.fragment.MovieHotFragment;
import com.apl.ticket.ui.home.fragment.MovieHotLargeFragment;
import com.apl.ticket.ui.home.fragment.MoviePreviewFragment;
import com.apl.ticket.ui.home.fragment.MoviePreviewLargeFragment;
import com.apl.ticket.ui.location.LocationActivity;
import com.apl.ticket.ui.map.MapActivity;
import com.apl.ticket.ui.search.SearchActivity;
import com.apl.ticket.ui.userservice.UserServiceActivity;
import com.vittaw.mvplibrary.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

//TODO 继承自BaseActivity 指定泛型,加载数据的
public class HomeActivity extends BaseActivity {

    private static final String TAG = HomeActivity.class.getSimpleName();

    //------------------bottom--------------------------------
    @BindView(R2.id.home_bottom_guide_container)
    RadioGroup mBottomContainer;

    @BindView(R2.id.home_bottom_movie)
    RadioButton mBottomMovie;

    @BindView(R2.id.home_bottom_cinema)
    RadioButton mBottomCinema;

    @BindView(R2.id.home_bottom_found)
    RadioButton mBottomFound;

    @BindView(R2.id.home_bottom_mine)
    RadioButton mBottomMine;

    //------------main---------------------------
    @BindView(R2.id.home_main_container)
    FrameLayout mMainContainer;//主页面的container

    private Fragment mShowFragment;

    //---------------top----------------------
    @BindView(R2.id.home_top_movie)
    FrameLayout mTopMovie;

    @BindView(R2.id.home_top_cinema)
    FrameLayout mTopCinema;

    @BindView(R2.id.home_top_found)
    TextView mTopFound;

    @BindView(R2.id.home_top_mine)
    FrameLayout mTopMine;

    //---------------------movie页-----------------------------------
    @BindView(R2.id.home_top_movie_list_mode)
    CheckBox mMovieListMode;

    @BindView(R2.id.home_top_movie_hot)
    CheckBox mMovieHot;

    @BindView(R2.id.home_top_movie_preview)
    CheckBox mMoviePreview;

    @BindView(R2.id.home_top_movie_indicator)
    View mIndicator;

    private int mIndicatorWidth;


    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        mBottomMovie.setChecked(true);
        mMovieListMode.setChecked(true);
        mMovieHot.setChecked(true);

    }

    @OnCheckedChanged(value = {R2.id.home_bottom_movie,R2.id.home_bottom_cinema,R2.id.home_bottom_found,R2.id.home_bottom_mine})
    public void onCheckChanged(CompoundButton buttonView, boolean isChecked){
        if (isChecked){//选中一个,判断是哪一个,切换fragment
            switch (buttonView.getId()) {
                case R.id.home_bottom_movie:
                    if (isLargeMode){
                        if (isPreviewChecked){
                            switchTo(MoviePreviewLargeFragment.TAG);
                        }else{
                            switchTo(MovieHotLargeFragment.TAG);
                        }
                    }else{
                        if (isPreviewChecked){
                            switchTo(MoviePreviewFragment.TAG);
                        }else{
                            switchTo(MovieHotFragment.TAG);
                        }
                    }
                    mTopMovie.setVisibility(View.VISIBLE);
                    mTopCinema.setVisibility(View.GONE);
                    mTopFound.setVisibility(View.GONE);
                    mTopMine.setVisibility(View.GONE);
                    break;
                case R.id.home_bottom_cinema:
                    switchTo(CinemaFragment.TAG);
                    mTopMovie.setVisibility(View.GONE);
                    mTopCinema.setVisibility(View.VISIBLE);
                    mTopFound.setVisibility(View.GONE);
                    mTopMine.setVisibility(View.GONE);
                    break;
                case R.id.home_bottom_found:
                    switchTo(FoundFragment.TAG);
                    mTopMovie.setVisibility(View.GONE);
                    mTopCinema.setVisibility(View.GONE);
                    mTopFound.setVisibility(View.VISIBLE);
                    mTopMine.setVisibility(View.GONE);
                    break;
                case R.id.home_bottom_mine:
                    switchTo(MineFragment.TAG);
                    mTopMovie.setVisibility(View.GONE);
                    mTopCinema.setVisibility(View.GONE);
                    mTopFound.setVisibility(View.GONE);
                    mTopMine.setVisibility(View.VISIBLE);
                    break;
            }
        }

    }

    private void switchTo(String tag) {
        //TODO 动态切换fragment
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        //隐藏当前显示的
        if (mShowFragment != null){
            transaction.hide(mShowFragment);
        }
        //显示将要显示的页面 -- 缓存的逻辑(fragment 在fm加载过就有缓存的)
        if (fm.findFragmentByTag(tag) != null){
            mShowFragment = fm.findFragmentByTag(tag);
            transaction.show(mShowFragment);
        }else{
            try {
                mShowFragment = (Fragment) Class.forName(tag).newInstance();
                transaction.add(R.id.home_main_container,mShowFragment,tag);//********frameLayout 上显示fragment通过transaction
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        transaction.commit();
    }

    //点击事件---------------------------
    @OnClick(value = {R2.id.home_top_movie_location,R2.id.home_top_movie_search,R2.id.home_top_cinema_location_text,R2.id.home_top_cinema_location_image,R2.id.home_top_cinema_search,R2.id.home_top_mine_service})
    void onClickOfPageMovie(View v){
        switch (v.getId()) {
            case R.id.home_top_movie_location:
                startActivity(new Intent(this, LocationActivity.class));
                break;
            case R.id.home_top_movie_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.home_top_cinema_location_text:
                startActivity(new Intent(this, LocationActivity.class));
                break;
            case R.id.home_top_cinema_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.home_top_cinema_location_image:
                startActivity(new Intent(this, MapActivity.class));
                break;
            case R.id.home_top_mine_service:
                startActivity(new Intent(this, UserServiceActivity.class));
                break;
        }
    }

    //以下主要控制movie 的 topbar上 fragment的切换---------------------------------
    private boolean isPreviewChecked;//默认是false
    private boolean isLargeMode;//默认是false
    private boolean isFirstIn = true;//第一次选中hot normal 不要动画 ,默认是false
    @OnCheckedChanged(value = {R2.id.home_top_movie_hot,R2.id.home_top_movie_preview,R2.id.home_top_movie_list_mode})
    void onMovieTitleCheckChanged(CompoundButton buttonView, boolean isChecked){
        if (mIndicatorWidth == 0){
            mIndicatorWidth = mIndicator.getWidth();
        }
        if (isChecked){
            switch (buttonView.getId()) {
                case R.id.home_top_movie_hot:
                    switchToHot();
                    break;
                case R.id.home_top_movie_preview:
                    switchToPreview();
                    break;
            }
        }
        if (buttonView.getId() == R.id.home_top_movie_list_mode && !isFirstIn){//isFistIn 第一次进入程序,是此判断无效
            //TODO 切换显示模式
            if (isChecked){
                //TODO 切换到Normal
                if (isPreviewChecked){
                    switchAnimator(360,90,0,MoviePreviewFragment.TAG);
                }else{
                    switchAnimator(360,90,0,MovieHotFragment.TAG);
                }
                isLargeMode = false;
            }else{
                //TODO 切换到Large
                if (isPreviewChecked){// Preview → PreviewLarge
                    switchAnimator(0,270,360,MoviePreviewLargeFragment.TAG);
                }else{
                    switchAnimator(0,270,360,MovieHotLargeFragment.TAG);
                }
                isLargeMode = true;
            }
        }
        isFirstIn = false;
    }

    private void switchAnimator(final float startDegree, final float anotherMiddleDegree, final float stopDegree, final String tag) {// ↓ 0 → 90
        int middleDegree = 0;
        if (startDegree == 360){
            middleDegree = 270;
        }else{
            middleDegree = 90;
        }
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(mMainContainer, "RotationY", startDegree, middleDegree);
        rotationY.setDuration(500).start();
        rotationY.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                switchTo(tag);
                mMainContainer.setRotationY(anotherMiddleDegree);
                ObjectAnimator.ofFloat(mMainContainer, "RotationY", anotherMiddleDegree ,stopDegree).setDuration(500).start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void switchToPreview() {
        ObjectAnimator.ofFloat(mIndicator,"TranslationX",mIndicatorWidth).setDuration(300).start();
        mMovieHot.setChecked(false);
        if (isLargeMode){
         switchTo(MoviePreviewLargeFragment.TAG);
        }else{
            switchTo(MoviePreviewFragment.TAG);
        }
        isPreviewChecked = true;
    }

    private void switchToHot() {
        ObjectAnimator.ofFloat(mIndicator,"TranslationX",0).setDuration(300).start();
        mMoviePreview.setChecked(false);
        if (isLargeMode){
            switchTo(MovieHotLargeFragment.TAG);
        }else{
            switchTo(MovieHotFragment.TAG);
        }
        isPreviewChecked = false;
    }
    //----------------------------------------------------------------------


}
