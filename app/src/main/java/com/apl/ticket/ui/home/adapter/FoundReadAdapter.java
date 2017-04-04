package com.apl.ticket.ui.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.apl.ticket.been.FoundReadBeen;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FoundReadAdapter extends RecyclerView.Adapter<FoundReadAdapter.ViewHolder> implements View.OnClickListener{

    private List<FoundReadBeen.Article> data;

    private LayoutInflater inflater;

    private Context context;

    private RecyclerView mRecyclerView;

    private boolean isLoadMore;

    public boolean isLoadMore() {
        return isLoadMore;
    }

    public void setLoadMore(boolean loadMore) {
        isLoadMore = loadMore;
    }

    private OnItemClickListener onItemClickListener;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public FoundReadAdapter(Context context, List<FoundReadBeen.Article> data){
        inflater = LayoutInflater.from(context);
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
        this.context = context;
    }

    public void updateResAll(List<FoundReadBeen.Article> data) {
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void addResAll(List<FoundReadBeen.Article> data) {
        if (data != null) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.fragment_found_read_item, parent, false);
        itemView.setOnClickListener(this);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //TODO 绑定数据源
        FoundReadBeen.Article article = getItem(position);
        Picasso.with(context).load(article.getPicUrl()).into(holder.mImage);
        holder.mTitle.setText(article.getTitle());
        holder.mHighLight.setText(article.getHighlight());
    }

    public FoundReadBeen.Article getItem(int position) {
        return data.get(position);
    }

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            int position = mRecyclerView.getChildAdapterPosition(v);
            onItemClickListener.onItemClick(mRecyclerView, v, position, position);
        }

    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (mRecyclerView == null) {
            this.mRecyclerView = recyclerView;
        }
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (mRecyclerView != null) {
            mRecyclerView = null;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.read_item_image)
        ImageView mImage;

        @BindView(R2.id.read_item_title)
        TextView mTitle;

        @BindView(R2.id.read_item_high_light)
        TextView mHighLight;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(RecyclerView parent, View view, int position, long id);
    }


}
