package com.apl.ticket;

import android.app.Application;
import android.graphics.Bitmap;
import android.net.Uri;

import com.squareup.picasso.Picasso;

/**
 * Created by Administrator on 2017/3/25 0025.
 */

public class AplTicketApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initPicasso();
    }

    private void initPicasso() {
        Picasso picasso = new Picasso.Builder(this)
                .defaultBitmapConfig(Bitmap.Config.RGB_565)//改变图片质量
                .build();
        Picasso.setSingletonInstance(picasso);
    }
}
