package com.example.fishgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Enemy {
    private int currentEnemyNumber =0;
    public Bitmap getNextEnemy(Context context) {
        Bitmap enemy = BitmapFactory.decodeResource(context.getResources(),R.drawable.enemy1);
        switch (currentEnemyNumber){
            case 0:
                enemy = BitmapFactory.decodeResource(context.getResources(),R.drawable.enemy1);
                break;
            case 1:
                enemy = BitmapFactory.decodeResource(context.getResources(),R.drawable.enemy2);
                break;
            case 2:
                enemy = BitmapFactory.decodeResource(context.getResources(),R.drawable.enemy3);
                break;
            case 3:
                enemy = BitmapFactory.decodeResource(context.getResources(),R.drawable.enemy4);
                break;
            case 4:
                enemy = BitmapFactory.decodeResource(context.getResources(),R.drawable.enemy5);
                break;
        }
        enemy = Bitmap.createScaledBitmap(enemy,200,200,false);
        currentEnemyNumber++;
        if(currentEnemyNumber>8){
            currentEnemyNumber=0;
        }
        return enemy;
    }
}
