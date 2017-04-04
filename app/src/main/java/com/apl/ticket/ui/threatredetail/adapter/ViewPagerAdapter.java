package com.apl.ticket.ui.threatredetail.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.apl.ticket.been.theatre.MovieListBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/4/2.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private List<MovieListBean> listViews;
    private Context context;

    public ViewPagerAdapter(List<MovieListBean> listViews) {
        this.listViews = listViews;
    }

    public ViewPagerAdapter(List<MovieListBean> listViews, Context context) {
        this.listViews = listViews;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView=new ImageView(context);
        Picasso.with(context).load(listViews.get(position).getLogo()).into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
