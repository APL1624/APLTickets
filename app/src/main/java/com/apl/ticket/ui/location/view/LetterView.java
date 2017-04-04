package com.apl.ticket.ui.location.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Click 是如何产生的
 * 实际在系统的底层并没有点击事件
 * Android
 * 底层中包含的都是触摸事件，将触摸事件封装成点击事件
 * Android
 * 触摸事件 一个完成的触摸事件 从触摸点落下到抬起
 * ACTION_DOWN - > ACTION_UP
 * 触摸事件的一个包装类 MotionEvent
 * 对象中包含动作，包含你按下的位置（x,y），
 */

public class LetterView extends View {

    private static final String TAG = LetterView.class.getSimpleName();
    //  A,B,C...X,Y,Z,#
    public List<String> letters;
    private Paint mPaint;

    private int selectPosition = -1;
    // 屏幕中字母
    private TextView mLetter;
    private int position;
    private String mType;
    private TypeListener listener;
    public interface TypeListener{
        void mathType(String Type);
    }
    public void setTypeListener(TypeListener listener){
        this.listener=listener;
    }
    public void setLetter(TextView mLetter) {

        this.mLetter = mLetter;
    }

    public LetterView(Context context) {
        this(context, null);
    }

    public LetterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        letters = new ArrayList<>();
        letters.add("热");
        // 生产字母
        for (int i = 65; i <= 90; i++) {
            letters.add(String.format(Locale.CHINA, "%c", i));
        }


        mPaint = new Paint();
        mPaint.setTextSize(18);
        mPaint.setAntiAlias(true);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 获取View的宽度
         * 获取View的高度
         *
         */
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        // 测量字的宽度
        int size = letters.size();
        // 计算一个字母的可占高度
        int singleHeight = measuredHeight / size;
        for (int i = 0; i < size; i++) {
            if (i == selectPosition) {
                mPaint.setColor(Color.RED);
            } else {
                mPaint.setColor(Color.BLACK);
            }
            float textWidth = mPaint.measureText(letters.get(i));
            canvas.drawText(letters.get(i), (measuredWidth - textWidth) / 2, singleHeight * (i + 1), mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 包装了动作，位置信息
//        event.getY();     相对于View本身的一个坐标
//        event.getRawY();   返回的是相对于屏幕的一个坐标值
        int y = (int) event.getY();
        int measuredHeight = getMeasuredHeight();
        int singleHeight = measuredHeight / letters.size();
        position = y / singleHeight;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
//                Log.e(TAG, "onTouchEvent: DOWN");
            case MotionEvent.ACTION_MOVE:
//                Log.e(TAG, "onTouchEvent: MOVE");
                selectPosition = position;
                if (mLetter != null) {
                    mLetter.setVisibility(VISIBLE);
                    // 极限临界状况，可能会产生越界，添加判断
                    if (position >=0 && position <letters.size()) {
                        mType = letters.get(position);
                        listener.mathType(mType);
                        mLetter.setText(mType);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onTouchEvent: UP");
                selectPosition = -1;
                if (mLetter != null){
                    mLetter.setVisibility(GONE);
                }
                break;
        }
        // 申请重绘
        invalidate();
//        返回true代表事件被处理
        return true;
    }

}
