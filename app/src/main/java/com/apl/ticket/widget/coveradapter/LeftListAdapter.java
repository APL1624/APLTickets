package com.apl.ticket.widget.coveradapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apl.ticket.R;
import com.apl.ticket.been.CinemaClassifyBeen;
import com.vittaw.mvplibrary.adapter.ListViewBaseAdapter;

import java.util.ArrayList;
import java.util.List;


public class LeftListAdapter extends ListViewBaseAdapter<CinemaClassifyBeen.District> {

    private int selectItem = 0;

    public int getSelectItem() {
        return selectItem;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    private Context context;

    public LeftListAdapter(Context context, List<CinemaClassifyBeen.District> data, int layoutResId) {
        super(context, data, layoutResId);
        this.context = context;
    }

    @Override
    protected void bindData(ViewHolder holder, List<CinemaClassifyBeen.District> data, int position) {
        holder.setText(R.id.cinema_cover_list_item_text,data.get(position).getName());

        TextView textView = (TextView) holder.findView(R.id.cinema_cover_list_item_text);
        if(position == selectItem){
            textView.setBackgroundColor(Color.WHITE);
        }else {
            textView.setBackgroundColor(context.getResources().getColor(R.color.color_gray_e));
        }

    }


}
