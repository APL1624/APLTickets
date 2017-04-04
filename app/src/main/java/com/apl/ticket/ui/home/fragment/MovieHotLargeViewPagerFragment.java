package com.apl.ticket.ui.home.fragment;

import android.widget.ImageView;

import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.apl.ticket.been.HomePageBeen;
import com.apl.ticket.ui.home.EventBus.EventWhat;
import com.apl.ticket.ui.home.EventBus.MovieHotLargeEvent;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;
import com.vittaw.mvplibrary.base.BaseFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/3/28.
 */

public class MovieHotLargeViewPagerFragment extends BaseFragment {

    private String imgURL;

    public MovieHotLargeViewPagerFragment( String imgURL) {
        this.imgURL=imgURL;
    }

    @BindView(R2.id.movie_hot_large_img)
    ImageView imageView;

    @Override
    protected int getLayoutId() {
        return R.layout.movie_hot_large_item;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Picasso.with(getActivity()).load(imgURL).into(imageView);
    }
}
