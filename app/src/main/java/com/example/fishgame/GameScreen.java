package com.example.fishgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;

public class GameScreen extends View {

    Timer timer;
    public GameScreen(Context context) {
        super(context);
        timer = new Timer();
        timer.start();
    }

    class Timer extends CountDownTimer {
        public Timer() {
            super(Integer.MAX_VALUE,1000/60);
        }

        @Override
        public void onTick(long l) {
            GameScreen.this.invalidate();
        }

        @Override
        public void onFinish() {

        }
    }

    private float fishX=0;
    private float fishY=0;
    private float goalX=0;
    private float goalY=0;


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.rgb(103, 200, 231));
        Bitmap fish = BitmapFactory.decodeResource(getResources(),R.drawable.fish);
        fish = Bitmap.createScaledBitmap(fish,200,200,true);
        canvas.drawBitmap(fish,fishX,fishY,new Paint());
        if(fishX<goalX){
            fishX+=10;
        }else if(fishX>goalX){
            fishX-=10;
        }
        if(fishY<goalY){
            fishY+=10;
        }else if(fishY>goalY){
            fishY-=10;
        }

        if(Math.abs(fishX-goalX)<10){
            fishX=goalX;
        }
        if(Math.abs(fishY-goalY)<10){
            fishY=goalY;
        }


        if(fishX>1080){
            fishX=1080;
        }else if(fishX<0){
            fishX=0;
        }

        if(fishY>1550){
            fishY=1550;
        }else if(fishY<0){
            fishY=0;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            goalX = event.getX();
            goalY = event.getY();
        }

        return super.onTouchEvent(event);
    }
}
