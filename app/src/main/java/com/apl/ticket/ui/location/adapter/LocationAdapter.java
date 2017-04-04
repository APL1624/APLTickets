package com.apl.ticket.ui.location.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.apl.ticket.api.LocationApi;
import com.apl.ticket.been.location.LocationCityBean;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/25.
 */

public class LocationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private static final String TAG = LocationAdapter.class.getSimpleName();
    private List<LocationCityBean> cityInfo;
    private Context context;
    private RecyclerView mRecyclerView;

    public LocationAdapter(List<LocationCityBean> cityInfo, Context context) {
        if (cityInfo != null) {
            this.cityInfo = cityInfo;
        } else {
            this.cityInfo = new ArrayList<>();
        }

        this.context = context;
    }

    public void updateRes(List<LocationCityBean> cityInfo) {
        if (cityInfo != null) {
            this.cityInfo.clear();
            this.cityInfo.addAll(cityInfo);
            notifyDataSetChanged();
        }
    }

    public void addRes(List<LocationCityBean> cityInfo) {
        this.cityInfo.addAll(cityInfo);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return cityInfo.get(position).getType();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        switch (viewType) {
            case 0:
                itemView = LayoutInflater.from(context).inflate(R.layout.activity_location_item_head, parent, false);
                return new ViewHolderHead(itemView);

            case 1:
                itemView = LayoutInflater.from(context).inflate(R.layout.activity_location_item_content, parent, false);
                itemView.setOnClickListener(this);
                return new ViewHolderContent(itemView);

        }
        return null;


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 0:
                ((ViewHolderHead)holder).cityTpe.setText(cityInfo.get(position).getFullName());
                break;
            case 1:
                ((ViewHolderContent)holder).cityName.setText(cityInfo.get(position).getFullName());
                break;
        }
    }


    @Override
    public int getItemCount() {
        return cityInfo != null ? cityInfo.size() : 0;
    }

    @Override
    public void onClick(View view) {
        int childAdapterPosition = mRecyclerView.getChildAdapterPosition(view);
        Log.e(TAG,cityInfo.get(childAdapterPosition).getFirstName());


    }


    public static class ViewHolderHead extends RecyclerView.ViewHolder {


        @BindView(R2.id.location_item_head_text)
        TextView cityTpe;

        public ViewHolderHead(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static class ViewHolderContent extends RecyclerView.ViewHolder {

        @BindView(R2.id.location_item_content_text)
        TextView cityName;


        public ViewHolderContent(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }
    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mRecyclerView = null;
    }

}
