package com.apl.ticket.ui.moviedetail.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.apl.ticket.R;
import com.apl.ticket.been.HomeDetailBeen;
import com.apl.ticket.been.detailcomment.HomeDetailPostData;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2017/3/27.
 */

public class MovieDetailAdapter extends ListViewBaseAdapter<HomeDetailPostData>{

    private Context context;

    public MovieDetailAdapter(Context context, List<HomeDetailPostData> data, int layoutRes) {
        super(context, data, layoutRes);
        this.context = context;
    }

    @Override
    protected void onBindData(ViewHolder holder, HomeDetailPostData homeDetailPostData, int position) {
        ImageView imageView = (ImageView) holder.findView(R.id.movie_detail_avatar_logo);
        Picasso.with(context).load(homeDetailPostData.getAvatarUrl()).transform(new CropCircleTransformation()).into(imageView);
        holder.setText(R.id.movie_detail_avatar_name,homeDetailPostData.getNickName());
        holder.setText(R.id.movie_detail_avatar_comment,homeDetailPostData.getText());
        holder.setText(R.id.movie_detail_avatar_time,homeDetailPostData.getCreateTime());
        holder.setText(R.id.movie_detail_avatar_like,homeDetailPostData.getLikeCount());
    }
}
