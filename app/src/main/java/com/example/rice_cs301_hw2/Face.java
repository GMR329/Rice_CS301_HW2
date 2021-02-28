package com.example.rice_cs301_hw2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Face {

    private int skinColor;
    private int eyeColor;
    private int hairColor;

    //if hair is 0, hair is wavy
    //hair is 1 is short
    //hair is 2 is bowl cut
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
        //choose random values for all 3 rgb values and give them to skin, eye, and hair
        skinColor = Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        eyeColor = Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        hairColor = Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));

        //start with a random style from 1 to 3.
        hairStyle = (int) (Math.random() * 2);
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
        hairPaint.setColor(hairColor);
        if(hairStyle == 0){
            canvas.drawCircle(centerx - 100, centery - 300, 350, hairPaint);
            canvas.drawCircle(centerx + 100, centery - 300, 350, hairPaint);
            canvas.drawCircle(centerx, centery, 550, hairPaint);
        }
        if(hairStyle == 1){
            canvas.drawCircle(centerx, centery - 600, 350, hairPaint);
        }

        //draw the main face as an oval
        skinPaint.setColor(skinColor);
        canvas.drawOval(centerx - 400, centery - 600, centerx + 400, centery + 600, skinPaint);

        //draw the eyes
        eyePaint.setColor(eyeColor);
        canvas.drawCircle(centerx + 100, centery - 100, 50, eyePaint);
        canvas.drawCircle(centerx - 100, centery - 100, 50, eyePaint);

        if(hairStyle == 1){
            canvas.drawCircle(centerx, centery - 700, 300, hairPaint);
        }
        if(hairStyle == 2){
            int rightCorrection = 0;
            for(int i = centerx - 400; i < centerx; i++){
                canvas.drawArc(i, centery - 600, centerx + 400 - rightCorrection++,
                        centery, 180, 180, false, hairPaint);
            }
        }

    }
}
