package com.apl.ticket.ui.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.apl.ticket.been.FoundCircleBoardBeen;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public class FoundCircleAdapter extends RecyclerView.Adapter<FoundCircleAdapter.ViewHolder> implements View.OnClickListener {

    private List<FoundCircleBoardBeen.Board> data;

    private LayoutInflater inflater;

    private Context context;

    private RecyclerView mRecyclerView;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public FoundCircleAdapter(Context context , List<FoundCircleBoardBeen.Board> data) {
        inflater = LayoutInflater.from(context);
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
        this.context = context;
    }

    public void updateResAll(List<FoundCircleBoardBeen.Board> data){
        if (data != null){
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void addResAll(List<FoundCircleBoardBeen.Board> data){
        if (data != null){
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.fragment_foud_circle_item, parent, false);
        itemView.setOnClickListener(this);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Picasso.with(context).load(data.get(position).getBoardIconUrl()).transform(new CropCircleTransformation()).into(holder.mImage);
        holder.mTitle.setText(data.get(position).getBoardName());
        holder.mPerson.setText("人数:" + data.get(position).getPeopleNum());
        holder.mPost.setText("帖子:" + data.get(position).getPostsNum());
    }

    public FoundCircleBoardBeen.Board getItem(int position){
        return data.get(position);
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null){
            int position = mRecyclerView.getChildAdapterPosition(v);
            onItemClickListener.onItemClick(mRecyclerView,v,position,position);
        }

    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (mRecyclerView == null){
            this.mRecyclerView = recyclerView;
        }
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (mRecyclerView != null){
            mRecyclerView = null;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R2.id.found_circle_img1)
        ImageView mImage;

        @BindView(R2.id.found_circle_title1)
        TextView mTitle;

        @BindView(R2.id.found_circle_textview1_person)
        TextView mPerson;

        @BindView(R2.id.found_circle_textview1_posts)
        TextView mPost;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(RecyclerView parent, View view, int position, long id);
    }

}
