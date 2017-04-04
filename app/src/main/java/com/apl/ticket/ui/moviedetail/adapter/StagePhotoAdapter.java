package com.apl.ticket.ui.moviedetail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.apl.ticket.been.HomeDetailBeen;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StagePhotoAdapter extends RecyclerView.Adapter<StagePhotoAdapter.ViewHolder> implements View.OnClickListener {

    private List<HomeDetailBeen.HDPData.HDPDLogo> stagePhotos;

    private Context context;

    private LayoutInflater inflater;

    private RecyclerView mRecyclerView;

    public static final String TAG = StagePhotoAdapter.class.getSimpleName();

    public StagePhotoAdapter( Context context ,List<HomeDetailBeen.HDPData.HDPDLogo> stagePhotos) {
        inflater = LayoutInflater.from(context);
        if (stagePhotos != null){
            this.stagePhotos = stagePhotos;
        }else{
            this.stagePhotos = new ArrayList<>();
        }
        this.context = context;
    }

    public void updateRes(List<HomeDetailBeen.HDPData.HDPDLogo > stagePhoto){
        if (stagePhoto != null){
            this.stagePhotos.clear();
            this.stagePhotos.addAll(stagePhoto);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.activity_movie_detail_header_stage_item, parent, false);
        itemView.setOnClickListener(this);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String logo1 = stagePhotos.get(position).getLogo1();
        Picasso.with(context).load(logo1).into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        Log.e(TAG, "getItemCount: " + stagePhotos.size() );
        return stagePhotos != null? stagePhotos.size() : 0;
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

    @Override
    public void onClick(View v) {
        int position = mRecyclerView.getChildAdapterPosition(v);
        Toast.makeText(context, position + "", Toast.LENGTH_SHORT).show();
        //TODO 放大图片 全屏展示图片,加载logo logo是大图,logo1是小图
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R2.id.movie_detail_header_stage_item_image)
        ImageView mImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }

}
