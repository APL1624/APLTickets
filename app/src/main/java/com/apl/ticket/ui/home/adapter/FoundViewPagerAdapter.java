package com.apl.ticket.ui.home.adapter;


import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;



import java.util.List;

/**
 * Created by Administrator on 2017/3/30.
 */

public class FoundViewPagerAdapter extends PagerAdapter {

    private List<ImageView> data;

    public FoundViewPagerAdapter(List<ImageView> data) {
        this.data = data;
    }

    public void upData(List<ImageView> data) {
        if (this.data != null) {
            this.data.clear();
        }
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(data.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = data.get(position);
        container.addView(imageView);
        return imageView;
    }

}
