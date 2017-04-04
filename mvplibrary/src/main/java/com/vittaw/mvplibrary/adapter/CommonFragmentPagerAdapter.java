package com.vittaw.mvplibrary.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class CommonFragmentPagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> data;

    public CommonFragmentPagerAdapter(FragmentManager fm,List<Fragment> data) {
        super(fm);
        if (data != null){
            this.data = data;
        }else{
            this.data = new ArrayList<>();
        }
    }

    public void updateRes(List<Fragment> data){
        if (data != null){
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data != null? data.size() : 0;
    }
}
