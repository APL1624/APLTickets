package com.apl.ticket.ui.home.adapter;


import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import com.apl.ticket.R;
import com.apl.ticket.been.cinema.CinemaBeen;
import com.orhanobut.logger.Logger;
import com.vittaw.mvplibrary.adapter.ListViewBaseAdapter;
import com.vittaw.mvplibrary.utils.DistanceUtil;

import java.util.ArrayList;
import java.util.List;

public class CinemaAdapter extends ListViewBaseAdapter<CinemaBeen> implements Filterable{

    private List<CinemaBeen> backData;//备份数据源

    private MyFilter myFilter;

    public CinemaAdapter(Context context, List<CinemaBeen> data, int layoutResId) {
        super(context, data, layoutResId);
        if (data != null){
            backData = data;    //第一次传入的data是null
        }
    }

    @Override
    public void updateRes(List<CinemaBeen> data) {
        super.updateRes(data);
        if (backData == null){
            backData = data;//我们的数据时update来的
        }
    }

    @Override
    protected void bindData(ViewHolder holder, List<CinemaBeen> data, int position) {
        CinemaBeen cinemaBeen = data.get(position);
        holder.setText(R.id.cinema_name,cinemaBeen.getName());
        if ("0".equals(cinemaBeen.getIsImaxSupport()) ){
            holder.findView(R.id.cinema_imax).setVisibility(View.GONE);
        }else if ("1".equals(cinemaBeen.getIsImaxSupport())){
            holder.findView(R.id.cinema_imax).setVisibility(View.VISIBLE);
        }
        holder.setText(R.id.cinema_address,cinemaBeen.getAddress());
        holder.setText(R.id.cinema_distance, DistanceUtil.getDistance(cinemaBeen.getCoord()));
        holder.setText(R.id.cinema_play_count,cinemaBeen.getScreenings());
        holder.setText(R.id.cinema_low_price,"￥" + cinemaBeen.getLowPrice() + "起");
        if ("0".equals(cinemaBeen.getIsSeatSupport())){
            holder.findView(R.id.cinema_seat).setVisibility(View.GONE);
        }else if ("1".equals(cinemaBeen.getIsSeatSupport())){
            holder.findView(R.id.cinema_seat).setVisibility(View.VISIBLE);
        }
        if ("0".equals(cinemaBeen.getIsCouponSupport())){
            holder.findView(R.id.cinema_quan).setVisibility(View.GONE);
        }else if ("1".equals(cinemaBeen.getIsCouponSupport())){
            holder.findView(R.id.cinema_quan).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public Filter getFilter() {
        if (myFilter == null){
            myFilter = new MyFilter();
        }
        return myFilter;
    }

    private class MyFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            Logger.e("backData的大小:" + backData.size());
            FilterResults results = new FilterResults();
            List<CinemaBeen> list;
            if (TextUtils.isEmpty(constraint)){
                list = backData;
            }else{
                list = new ArrayList<>();
                for (CinemaBeen been : backData){
                    if (constraint.equals(been.getDistrictId())){
//                        Logger.e("满足条件的CinemaBeen:" + been.getName());
                        list.add(been);
                    }
                }
            }
            results.values = list;
            results.count = list.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count > 0){
//                filterUpdate((List<CinemaBeen>) results.values);
                updateRes((List<CinemaBeen>) results.values);
            }else{
                //没有数据了,就显示全部数据,哈哈
                filterUpdate(backData);
            }
        }
    }

}
