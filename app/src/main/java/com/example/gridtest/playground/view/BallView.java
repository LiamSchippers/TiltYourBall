package com.example.gridtest.playground.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.gridtest.R;
import com.example.gridtest.playground.model.BallModel;

public class BallView extends View {

    private Bitmap bitMap;
    private Paint shadow = new Paint();
    private BallModel ballModel;
    private Paint colorRect = new Paint();
    private int counter = 0;


    public BallView(Context context) {
        super(context);
        init();
    }

    public BallView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BallView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BallView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init(){
        Bitmap ballSrc = BitmapFactory.decodeResource(getResources(), R.drawable.ball_red);
        final int dstWidth = 75;
        final int dstHeight = 75;
        bitMap = Bitmap.createScaledBitmap(ballSrc, dstWidth, dstHeight, true);
//        shadow.setARGB(100,0,0,0);


    }
    public void setModel(BallModel model){
        this.ballModel = model;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        /** Draws the ball at the given position */
        if (ballModel != null) {
            canvas.drawBitmap(bitMap, ballModel.getxPos() - bitMap.getWidth() / 2, ballModel.getyPos() - bitMap.getHeight() /2, null);
            ballModel.setMax(canvas.getWidth(), canvas.getHeight());

        }


    }

}

