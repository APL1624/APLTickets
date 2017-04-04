package com.apl.ticket.ui.home.transformer;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.animation.Transformation;

/**
 * Created by Rock on 2017/3/16.
 */

public class ScaleTransformer implements ViewPager.PageTransformer {
    private static final String TAG = ScaleTransformer.class.getSimpleName();
    private static final float min_scale = 0.85f;
    // position -1,1
    @Override
    public void transformPage(View page, float position) {
//        Log.e(TAG, "transformPage: page:" + page + "==>" + position);
        float scaleFactor = Math.max(min_scale, 1 - Math.abs(position));
        float rotate = 20 * Math.abs(position);
        if (position < -1) {

        } else if (position < 0) {
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setRotationY(rotate);
        } else if (position >= 0 && position < 1) {
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setRotationY(-rotate);
        } else if (position >= 1) {
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setRotationY(-rotate);
        }
    }

}
