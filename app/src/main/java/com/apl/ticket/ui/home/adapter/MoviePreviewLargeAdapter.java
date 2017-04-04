package com.apl.ticket.ui.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class MoviePreviewLargeAdapter extends PagerAdapter {

    private List<ImageView> mImageView;


    public void upData(List<ImageView> mImageView){
        this.mImageView=mImageView;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return mImageView!=null?300:0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mImageView.get(position%mImageView.size()));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mImageView.get(position%mImageView.size()));
        return mImageView.get(position%mImageView.size());
    }
}
