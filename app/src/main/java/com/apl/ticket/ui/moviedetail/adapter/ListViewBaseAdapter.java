package com.apl.ticket.ui.moviedetail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/27.
 */

public abstract class ListViewBaseAdapter<T> extends BaseAdapter {

    private List<T> data;

    private LayoutInflater inflater;

    private int layoutRes;

    //构造方法咋能有返回值呢
    public  ListViewBaseAdapter(Context context,List<T> data, int layoutRes) {
        inflater = LayoutInflater.from(context);
        if (data != null){
            this.data = data;
        }else{
            this.data = new ArrayList<>();
        }
        this.layoutRes = layoutRes;
    }

    public void updateRes(List<T> data){
        if (data != null){
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void addRes(List<T> data){
        if (data != null){
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null){
            view = inflater.inflate(layoutRes,viewGroup,false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        //绑定数据源
        onBindData(holder,data.get(i),i);
        return view;
    }

    protected abstract void onBindData(ViewHolder holder,T t,int position);

    public static class ViewHolder{

        private View itemView;

        private Map<Integer,View> cacheViews;

        public ViewHolder(View itemView) {
            this.itemView = itemView;
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

        public void setText(int resId,String text){
            ((TextView) findView(resId)).setText(text);
        }
    }
}
