package com.example.fishgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
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


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.rgb(103, 200, 231));
        Bitmap fish = BitmapFactory.decodeResource(getResources(),R.drawable.fish);
        fish = Bitmap.createScaledBitmap(fish,200,200,true);
        canvas.drawBitmap(fish,fishX,fishY,new Paint());
        fishX++;
        fishY++;
    }
}
