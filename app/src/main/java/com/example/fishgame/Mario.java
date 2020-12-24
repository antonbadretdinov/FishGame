package com.example.fishgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Mario {
    private int currentMarioNumber = 0;
    public Bitmap getNextMario(Context context) {
        Bitmap mario = BitmapFactory.decodeResource(context.getResources(),R.drawable.mario1);
        switch ((int)currentMarioNumber){
            case 0:
            case 1:
                mario = BitmapFactory.decodeResource(context.getResources(),R.drawable.mario1);
                break;
            case 2:
            case 3:
                mario = BitmapFactory.decodeResource(context.getResources(),R.drawable.mario2);
                break;
            case 4:
            case 5:
                mario = BitmapFactory.decodeResource(context.getResources(),R.drawable.mario3);
                break;
        }
        mario = Bitmap.createScaledBitmap(mario,200,200,false);
        currentMarioNumber ++;
        if(currentMarioNumber>2){
            currentMarioNumber=0;
        }
        return mario;
    }
}
