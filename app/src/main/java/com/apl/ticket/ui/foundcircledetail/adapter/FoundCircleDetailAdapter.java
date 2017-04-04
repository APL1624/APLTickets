package com.apl.ticket.ui.foundcircledetail.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.apl.ticket.been.FoundCircleBoardBeen;
import com.apl.ticket.been.FoundCircleDetailPostBeen;
import com.apl.ticket.constants.TypeConstans;
import com.apl.ticket.ui.home.EventBus.EventWhat;
import com.apl.ticket.ui.home.EventBus.FoundEvent;
import com.apl.ticket.widget.circlecustomimage.CustomImageView;
import com.apl.ticket.widget.circlecustomimage.NineGridlayout;
import com.apl.ticket.widget.circlecustomimage.ScreenTools;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;
import com.vittaw.mvplibrary.utils.TimeUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by Administrator on 2017/4/3 0003.
 */

public class FoundCircleDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private List<FoundCircleDetailPostBeen.Post> data;

    private LayoutInflater inflater;

    private Context context;

    private RecyclerView mRecyclerView;

    private OnItemClickListener onItemClickListener;
    private FoundCircleBoardBeen.Board board;

    private boolean isLoadMore;

    public boolean isLoadMore() {
        return isLoadMore;
    }

    public void setLoadMore(boolean loadMore) {
        isLoadMore = loadMore;
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public FoundCircleDetailAdapter(Context context, List<FoundCircleDetailPostBeen.Post> data) {
        inflater = LayoutInflater.from(context);
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
        this.context = context;
    }

    public void updateResAll(List<FoundCircleDetailPostBeen.Post> data) {
        if (data != null) {
            this.data.clear();
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void addResAll(List<FoundCircleDetailPostBeen.Post> data) {
        if (data != null) {
            this.data.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TypeConstans.CIRCLE_ITEM_TYPE_HEADER:
                View itemView = inflater.inflate(R.layout.found_circle_detail_item_zero, parent, false);
                return new HeaderViewHolder(itemView);
            default:
                View itemViewTwo = inflater.inflate(R.layout.found_circle_detail_item_one, parent, false);
                itemViewTwo.setOnClickListener(this);
                return new ViewHolder(itemViewTwo);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.circle_item_share:
                Toast.makeText(context, "转发", Toast.LENGTH_SHORT).show();
                break;
            case R.id.circle_item_like:
                Toast.makeText(context, "点赞", Toast.LENGTH_SHORT).show();
                break;
            case R.id.circle_item_comment:
                Toast.makeText(context, "评论", Toast.LENGTH_SHORT).show();
                break;
            default:
                int position = mRecyclerView.getChildAdapterPosition(v);
                Logger.e(position + "");
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(mRecyclerView, v, position, position);
                }
                break;
        }



    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TypeConstans.CIRCLE_ITEM_TYPE_HEADER;
        } else {
            return Integer.parseInt(getItem(position).getType());
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //TODO 绑定数据源
        switch (getItemViewType(position)) {
            case TypeConstans.CIRCLE_ITEM_TYPE_HEADER:
                if (holder instanceof HeaderViewHolder){
                    Picasso.with(context).load(board.getBoardIconUrl()).transform(new CropCircleTransformation()).into(((HeaderViewHolder) holder).mBoardIcon);
                    ((HeaderViewHolder) holder).mBoardTitle.setText(board.getBoardName());
                    ((HeaderViewHolder) holder).mBoardPeople.setText("人数:" +  board.getPeopleNum());
                    ((HeaderViewHolder) holder).mBoardPost.setText("帖子:" + board.getPostsNum());
                }
                break;
            case TypeConstans.CIRCLE_ITEM_TYPE_TEXT:
                if (holder instanceof ViewHolder){
                    ((ViewHolder) holder).mNineGridLayout.setVisibility(View.GONE);
                    ((ViewHolder) holder).mCustomImageView.setVisibility(View.GONE);

                    handleText((ViewHolder) holder, position);
                }
                break;
            case TypeConstans.CIRCLE_ITEM_TYPE_IMAGE:
                if (holder instanceof ViewHolder){
                    List<FoundCircleDetailPostBeen.Image> imageList = getItem(position).getImageList();
                    if (imageList.size() == 1){//一张图
                        ((ViewHolder) holder).mCustomImageView.setVisibility(View.VISIBLE);
                        ((ViewHolder) holder).mNineGridLayout.setVisibility(View.GONE);

                        handleOneImage((ViewHolder) holder,position);
                        handleText((ViewHolder) holder,position);
                    }else{//九宫格图
                        ((ViewHolder) holder).mCustomImageView.setVisibility(View.GONE);
                        ((ViewHolder) holder).mNineGridLayout.setVisibility(View.VISIBLE);

                        ((ViewHolder) holder).mNineGridLayout.setImagesData(getItem(position).getImageList());
                        ((ViewHolder) holder).mNineGridLayout.setGap(8);//图片之间的间隔
                        handleText((ViewHolder) holder,position);
                    }

                }
                break;
        }
    }

    private void handleOneImage(ViewHolder holder, int position) {
        FoundCircleDetailPostBeen.Image image = getItem(position).getImageList().get(0);
        int totalWidth;
        int imageWidth;
        int imageHeight;
        ScreenTools screentools = ScreenTools.instance(context);
        totalWidth = screentools.getScreenWidth() - screentools.dip2px(80);
        float imageDipWidth = Float.parseFloat(image.getThumbnailWidth());
        imageWidth = screentools.dip2px(imageDipWidth);
        float imageDipHeight = Float.parseFloat(image.getThumbnailHeight());
        imageHeight = screentools.dip2px(imageDipHeight);
        if (imageDipWidth <= imageDipHeight) {
            if (imageHeight > totalWidth) {
                imageHeight = totalWidth;
                imageWidth = (int) ((imageHeight * imageDipWidth) / imageDipHeight);
            }
        } else {
            if (imageWidth > totalWidth) {
                imageWidth = totalWidth;
                imageHeight = (int) ((imageWidth * imageHeight) / imageDipWidth);
            }
        }
        ViewGroup.LayoutParams layoutparams = holder.mCustomImageView.getLayoutParams();
        layoutparams.height = imageHeight;
        layoutparams.width = imageWidth;
        holder.mCustomImageView.setLayoutParams(layoutparams);
        holder.mCustomImageView.setClickable(true);
        holder.mCustomImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        holder.mCustomImageView.setImageUrl(image.getThumbnailUrl());
    }

    private void handleText(ViewHolder holder, int position) {
        if (!TextUtils.isEmpty(getItem(position).getAvatarUrl())){
            Picasso.with(context).load(getItem(position).getAvatarUrl()).transform(new CropCircleTransformation()).into(holder.mUserIcon);
        }else{//没有头像,显示一张默认的
            Picasso.with(context).load(R.mipmap.user_avatar).transform(new CropCircleTransformation()).into(holder.mUserIcon);
        }
        holder.mUserName.setText(getItem(position).getNickName());
        holder.mContent.setText(getItem(position).getText());
        holder.mTime.setText(TimeUtil.convertCreateTime(getItem(position).getCreateTime()));//createTime: "2015-06-16 15:09:32"
        holder.mLike.setText(getItem(position).getLikeCount());
        holder.mComment.setText(getItem(position).getCommentCount());
        holder.mLike.setOnClickListener(this);
        holder.mComment.setOnClickListener(this);
        holder.mShare.setOnClickListener(this);
    }

    public FoundCircleDetailPostBeen.Post getItem(int position) {
        return data.get(position);
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

        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (mRecyclerView != null) {
            mRecyclerView = null;
        }

        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.circle_item_user_icon)
        ImageView mUserIcon;

        @BindView(R2.id.circle_item_user_name)
        TextView mUserName;

        @BindView(R2.id.circle_item_content)
        TextView mContent;

        @BindView(R2.id.circle_item_nine_image)
        NineGridlayout mNineGridLayout;

        @BindView(R2.id.circle_item_custom_image)
        CustomImageView mCustomImageView;

        @BindView(R2.id.circle_item_time)
        TextView mTime;

        @BindView(R2.id.circle_item_share)
        TextView mShare;

        @BindView(R2.id.circle_item_like)
        TextView mLike;

        @BindView(R2.id.circle_item_comment)
        TextView mComment;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.found_circle_detail_board_icon)
        ImageView mBoardIcon;

        @BindView(R2.id.found_circle_detail_board_title)
        TextView mBoardTitle;

        @BindView(R2.id.found_circle_detail_board_people)
        TextView mBoardPeople;

        @BindView(R2.id.found_circle_detail_board_post)
        TextView mBoardPost;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(RecyclerView parent, View view, int position, long id);
    }


    @Subscribe(sticky = true)
    public void onEvent(FoundEvent foundEvent){
        switch (foundEvent.WHAT) {
            case EventWhat.FOUND_CIRCLE_BOARD:
                board = foundEvent.getBoard();
                Logger.e("适配器里收到board" + board.getBoardName());
                break;
        }
    }



}
