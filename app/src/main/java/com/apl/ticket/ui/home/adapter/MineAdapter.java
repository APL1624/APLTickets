package com.apl.ticket.ui.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.apl.ticket.R;
import com.apl.ticket.R2;
import com.apl.ticket.been.mine.MineInfoBean;
import com.apl.ticket.ui.login.LogInActivity;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;

import static android.content.Context.MODE_PRIVATE;

/**
 * fqc,cry,cry
 */

public class MineAdapter extends ListViewMultiTypeBaseAdapter<MineInfoBean> implements View.OnClickListener{

    private Context context;
    public MineAdapter(Context context, List<MineInfoBean> data, int... layoutIds) {
        super(context, data, layoutIds);
        this.context=context;
    }
    @Override
    public void bindData(ViewHolder holder, MineInfoBean item, int position) {
        switch (item.getType()) {
            case 0:
                holder.findView(R.id.mine_item_login_btn).setOnClickListener(this);
                ImageView view = (ImageView) holder.findView(R.id.mine_item_login_image);

                String imageUrl= item.getLogInImag();
                if (imageUrl!=null) {
                    Picasso.with(context).load(imageUrl).into(view);
                }
                view.setOnClickListener(this);
                break;
            case 1:
                holder.findView(R.id.mine_item_info_coupon_view).setOnClickListener(this);
                holder.findView(R.id.mine_item_info_order_view).setOnClickListener(this);
                holder.findView(R.id.mine_item_info_points_view).setOnClickListener(this);
                break;
            case 2:
                holder.findView(R.id.mine_item_hobby__want_to_see_view).setOnClickListener(this);
                holder.findView(R.id.mine_item_hobby_collection_view).setOnClickListener(this);
                break;
            case 3:
                holder.setText(R.id.mine_item_ss_text,item.getName().get(0));
                ImageView imageView= (ImageView) holder.findView(R.id.mine_item_ss_image);
                imageView.setImageResource(item.getImageUrl().get(0));
                holder.findView(R.id.mine_item_ss_view).setOnClickListener(this);
                break;

        }


    }
    @Override
    public void onClick(View view) {

        context.startActivity(new Intent(context, LogInActivity.class));

    }

}
