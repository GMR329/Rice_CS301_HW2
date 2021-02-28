package com.example.rice_cs301_hw2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Face {

    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairStyle;

    private Paint skinPaint;
    private Paint eyePaint;
    private Paint hairPaint;

    public Face(){
        skinPaint = new Paint();
        eyePaint = new Paint();
        hairPaint = new Paint();

        randomize();
    }

    private void randomize(){
        skinColor = Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        eyeColor = Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        hairColor = Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        hairStyle = (int) (Math.random() * 3);
    }

    public void setSkinColor(int col){
        skinColor = col;
    }

    public void setEyeColor(int col){
        eyeColor = col;
    }

    public void setHairColor(int col){
        hairColor = col;
    }

    public void setHairStyle(int style){
        hairStyle = style;
    }

    public void draw(Canvas canvas, int centerx, int centery){
        skinPaint.setColor(skinColor);
        canvas.drawOval(centerx - 400, centery - 600, centerx + 400, centery + 600, skinPaint);

        eyePaint.setColor(eyeColor);
        canvas.drawCircle(centerx + 100, centery - 100, 50, eyePaint);
        canvas.drawCircle(centerx - 100, centery - 100, 50, eyePaint);

        hairPaint.setColor(hairColor);
        if(hairStyle == 0){
            canvas.drawRect(200, 200, 300, 300, hairPaint);
        }else if(hairStyle == 1){
            canvas.drawCircle(200, 200, 50, hairPaint);
        }else{
            canvas.drawOval(200, 200, 400, 400, hairPaint);
        }
    }
}
