package com.apl.ticket.ui.home.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.apl.ticket.been.HomePageBeen;
import com.apl.ticket.ui.cinema.CinemaActivity;
import com.apl.ticket.ui.moviedetail.MovieDetailActivity;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieHotAdapter  extends RecyclerView.Adapter<MovieHotAdapter.ViewHolder> implements View.OnClickListener {

    private List<HomePageBeen.HPData> data;

    private LayoutInflater inflater;

    private Context context;

    private RecyclerView mRecyclerView;

    public static final String TAG = MovieHotAdapter.class.getSimpleName();

    public MovieHotAdapter(Context context, List<HomePageBeen.HPData> data) {
        this.context = context;
        if (data != null){
            this.data = data;
        }else{
            this.data = new ArrayList<>();
        }
        inflater = LayoutInflater.from(context);
    }

    public void updateResAll(List<HomePageBeen.HPData> data){
        if (data != null){
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void addResAll(List<HomePageBeen.HPData> data){
        if (data != null){
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.fragment_movie_hot_item, parent, false);
        itemView.setOnClickListener(this);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HomePageBeen.HPData hpData = data.get(position);
        Picasso.with(context).load(hpData.getLogo()).into(holder.mImage);
        holder.mTitle.setText(hpData.getName());
        holder.mHighLight.setText(hpData.getHighlight());
        holder.mScreenings.setText(hpData.getScreenings());
        if ("1".equals(hpData.getIsNew())){//是新片
            holder.mNewMovie.setVisibility(View.VISIBLE);
        }
        holder.mGrade.setText(hpData.getGrade()+"分");
        holder.mBuyBtn.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }


    @Override
    public void onClick(View v) {
        //选作购票的监听回调
        if (v.getId() == R.id.hot_item_buy_ticket){
            //TODO 跳转传值
            context.startActivity(new Intent(context, CinemaActivity.class));
            return;
        }
        //itemView的点击监听
        if (mRecyclerView != null){
            int position = mRecyclerView.getChildLayoutPosition(v);
            Log.e(TAG, "onClick: " + position );
            Intent intent = new Intent(context, MovieDetailActivity.class);
            //TODO 跳转city不知道咋来的,accountId不知道咋来的 -- 先写死了!
            intent.putExtra(MovieDetailActivity.MOVIE_ID,data.get(position).getId());
            context.startActivity(intent);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.mRecyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.mRecyclerView = null;
    }


    static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R2.id.hot_item_image)
        ImageView mImage;

        @BindView(R2.id.hot_item_title)
        TextView mTitle;

        @BindView(R2.id.hot_item_highlight)
        TextView mHighLight;

        @BindView(R2.id.hot_item_screenings)
        TextView mScreenings;

        @BindView(R2.id.hot_item_new_movie)
        ImageView mNewMovie;

        @BindView(R2.id.hot_item_grade)
        TextView mGrade;

        @BindView(R2.id.hot_item_buy_ticket)
        Button mBuyBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
