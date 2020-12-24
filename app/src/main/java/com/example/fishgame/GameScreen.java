package com.example.fishgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import static java.security.AccessController.getContext;

public class GameScreen extends SurfaceView implements SurfaceHolder.Callback {

    public GameScreen(Context context) {
        super(context);
        getHolder().addCallback(this);
    }
    private void setupThread(Context context) {
        gameThread = new GameThread();
        gameThread.setSurfaceHolder(getHolder());
        gameThread.setContext(context);
        gameThread.start();
    }
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        setupThread(getContext());
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    GameThread gameThread;



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            gameThread.setGoal(event.getX(), event.getY());
        }
        return super.onTouchEvent(event);
    }
}

class GameThread extends Thread {

    private SurfaceHolder surfaceHolder;
    private Context context;
    private float fishX = 0;
    private float fishY = 0;
    private float goalX = 0;
    private float goalY = 0;
    private float enemyX = 500;
    private float enemyY = 500;
    private boolean running = true;

    public void setSurfaceHolder(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setGoal(float goalX, float goalY) {
        this.goalX = goalX;
        this.goalY = goalY;
    }

    public Rect getRectForMario(){
        Rect rect = new Rect((int)fishX+30,(int)fishY+30,(int)fishX+120,(int)fishY+120);
        return rect;
    }
    public Rect getRectForEnemy(){
        Rect rect = new Rect((int)enemyX+10,(int)enemyY+10,(int)enemyX+80,(int)enemyY+80);
        return rect;
    }

    public void stopDraw() {
        running = false;
    }

    @Override
    public void run() {
        Bitmap fish = BitmapFactory.decodeResource(context.getResources(), R.drawable.fish);
        fish = Bitmap.createScaledBitmap(fish, 200, 200, true);
        Paint paint = new Paint();
        Mario mario = new Mario();
        Enemy enemy = new Enemy();
        paint.setColor(Color.RED);
        //Typeface font = Typeface.createFromAsset(getContext().getAssets(), "font/PressStart2P-Regular.ttf");
        //paint.setTypeface(font);
        paint.setTextSize(150);
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                canvas.drawColor(Color.rgb(103, 200, 231));
                canvas.drawBitmap(mario.getNextMario(context), fishX, fishY, paint);
                canvas.drawBitmap(enemy.getNextEnemy(context), enemyX, enemyY, paint);

                if (fishX < goalX) {
                    fishX += 10;
                } else if (fishX > goalX) {
                    fishX -= 10;
                }
                if (fishY < goalY) {
                    fishY += 10;
                } else if (fishY > goalY) {
                    fishY -= 10;
                }

                if (Math.abs(fishX - goalX) < 10) {
                    fishX = goalX;
                }
                if (Math.abs(fishY - goalY) < 10) {
                    fishY = goalY;
                }

                if(enemyX<fishX){
                    enemyX+=2.5;
                }else if(enemyX>fishX){
                    enemyX-=2.5;
                }
                if(enemyY<fishY){
                    enemyY+=2.5;
                }else if(enemyY>fishY){
                    enemyY-=2.5;
                }

                if(getRectForMario().intersect(getRectForEnemy())){
                    running=false;
                    canvas.drawText("GAME OVER",180,800,paint);
                }

                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
