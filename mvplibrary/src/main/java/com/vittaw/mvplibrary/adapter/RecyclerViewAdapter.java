package com.vittaw.mvplibrary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class RecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private List<T> data;

    private LayoutInflater inflater;

    private int layoutRes;

    public RecyclerViewAdapter(Context context,List<T> data,int layoutRes) {
        inflater = LayoutInflater.from(context);
        if (data != null){
            this.data = data;
        }else{
            this.data = new ArrayList<>();
        }
        this.layoutRes = layoutRes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(layoutRes, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        onBindData(holder,position);
    }

    public abstract  void onBindData(ViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        private Map<Integer,View> cacheViews;

        ViewHolder(View itemView) {
            super(itemView);
            cacheViews = new HashMap<>();
        }

        public View findView(int resId){
            //根据id去findViewById,添加入缓存
            View v = null;
            if (cacheViews.containsKey(resId)){
                v = cacheViews.get(resId);
            }else{
                v = itemView.findViewById(resId);
            }
            return v;
        }
    }

}
