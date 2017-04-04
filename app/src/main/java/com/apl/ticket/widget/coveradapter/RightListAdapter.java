package com.apl.ticket.widget.coveradapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Filter;
import android.widget.Filterable;

import com.apl.ticket.R;
import com.apl.ticket.been.CinemaClassifyBeen;
import com.orhanobut.logger.Logger;
import com.vittaw.mvplibrary.adapter.ListViewBaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class RightListAdapter extends ListViewBaseAdapter<CinemaClassifyBeen.Circle> implements Filterable {

    private List<CinemaClassifyBeen.Circle> backData;//备份原始数据

    private MyFilter myFilter;

    private Map<String,Integer> letterPositionMap;

    public Map<String, Integer> getLetterPositionMap() {
        return letterPositionMap;
    }

    public RightListAdapter(Context context, List<CinemaClassifyBeen.Circle> data, int layoutResId) {
        super(context, data, layoutResId);                      //这个data是会变的,会是过滤后的数据
        initMap(data);
        backData = data;//备份原始数据
    }


    @Override
    protected void bindData(ViewHolder holder, List<CinemaClassifyBeen.Circle> data, int position) {
        String spell = data.get(position).getSpell().toUpperCase();
        if (position != 0 && spell.equals(data.get(position - 1).getSpell().toUpperCase())){
            holder.setText(R.id.cinema_cover_list_right_letter, "");
        }else{
            holder.setText(R.id.cinema_cover_list_right_letter, spell);
        }
        holder.setText(R.id.cinema_cover_list_right_text,data.get(position).getName());
    }


    private void initMap(List<CinemaClassifyBeen.Circle> data) {
        letterPositionMap = new HashMap<>();
        for (int i = 0; i < data.size(); i++) {
            if (!letterPositionMap.containsKey(data.get(i).getSpell().toUpperCase())){
                letterPositionMap.put(data.get(i).getSpell().toUpperCase(),i);
            }
        }
    }

    //实现Filterable接口,当listView调用setTextFilter时,就会调用改方法
    @Override
    public Filter getFilter() {//返回我们自己定义的filter
        if (myFilter == null){
            myFilter = new MyFilter();
        }
        return myFilter;
    }


    //定义过滤器
    private class MyFilter extends Filter{

        //定义过滤规则,执行过滤条件
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {//districtId
            FilterResults filterResults = new FilterResults();
            List<CinemaClassifyBeen.Circle> list;
            if (TextUtils.isEmpty(constraint)){//如果约束为空
                list = backData;
            }else{
                list = new ArrayList<>();
                for (CinemaClassifyBeen.Circle circle : backData){
                    if (constraint.equals(circle.getDistrictId())){
                        Logger.e("满足条件的Circle:" + circle.getName());
                        list.add(circle);
                    }
                }
            }
            //将得到的集合存到filter - values变量中
            filterResults.values = list;
            //将集合的大小存到filter - count变量中
            filterResults.count = list.size();
            return filterResults;//将结果返回
        }

        //告诉适配器更新结果
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count > 0){
                //通知适配器刷新
                Logger.e("数据过滤成功,适配器刷新!");
                filterUpdate((List<CinemaClassifyBeen.Circle>) results.values);//这个方法用在过滤数据时
//                updateRes((List<CinemaClassifyBeen.Circle>) results.values);//这个方法用在刷新时
            }else{
                //通知数据失效
                Logger.e("数据过滤失效,list 数据count小于0,显示 暂无数据");
                //显示一条空数据
                List<CinemaClassifyBeen.Circle> list = new ArrayList<>();
                CinemaClassifyBeen.Circle e = new CinemaClassifyBeen.Circle();
                e.setSpell("");
                e.setId("0");
                e.setDistrictId("0");
                e.setName("什么都没找到~");
                list.add(e);
                filterUpdate(list);
            }
        }
    }

}
