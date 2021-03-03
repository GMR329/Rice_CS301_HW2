package com.example.rice_cs301_hw2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.SeekBar;

/**
 * @author Gareth Rice
 * @version 3/21
 *
 * Notes:
 *  Keeps track of the colors used to make the face (skin, eyes, hair)
 *  Keeps track of the paint that is used for the components and contains draw method
 *  for drawing the features
 *
 * Known Bugs:
 */

public class Face {

    private int skinColor;
    private int eyeColor;
    private int hairColor;

    //if hair is 0, hair is wavy
    //hair is 1 is an afro
    //hair is 2 is bowl cut
    private int hairStyle;

    private Paint skinPaint;
    private Paint eyePaint;
    private Paint hairPaint;

    /**
     * ctor Face: Initializes the paint objects and creates a random face
     */
    public Face(){
        skinPaint = new Paint();
        eyePaint = new Paint();
        hairPaint = new Paint();

        randomize();
    }

    /**
     * randomize: Sets random colors for skin, eyes, and hair as well as a random hairstyle
     */
    public void randomize(){
        //choose random values for all 3 rgb values and give them to skin, eye, and hair
        skinColor = Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255),
                (int) (Math.random() * 255));
        eyeColor = Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255),
                (int) (Math.random() * 255));
        hairColor = Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255),
                (int) (Math.random() * 255));

        //start with a random style from 1 to 3.
        hairStyle = (int) (Math.random() * 3);

    }

    public void setSkinColor(int col) {
        skinColor = col;
    }

    public int getSkinColor(){
        return skinColor;
    }

    public void setEyeColor(int col){
        eyeColor = col;
    }

    public int getEyeColor(){
        return eyeColor;
    }

    public void setHairColor(int col){
        hairColor = col;
    }

    public int getHairColor(){
        return hairColor;
    }

    public void setHairStyle(int style) {
        hairStyle = style;
    }

    /**
     * drawFace: draw an oval that is the color of the skin
     *
     * @param canvas
     * @param centerx
     * @param centery
     */
    public void drawFace(Canvas canvas, int centerx, int centery){
        //draw the main face as an oval
        skinPaint.setColor(skinColor);
        canvas.drawOval(centerx - 400, centery - 600, centerx + 400,
                centery + 600, skinPaint);
    }

    /**
     * drawEyes: draw whites with colored centers
     *
     * @param canvas
     * @param centerx
     * @param centery
     */
    public void drawEyes(Canvas canvas, int centerx, int centery){
        //Have eye whites. Paint them with centers 100 pixels from center of face on either side
        eyePaint.setColor(Color.WHITE);
        canvas.drawCircle(centerx + 100, centery - 100, 75, eyePaint);
        canvas.drawCircle(centerx - 100, centery - 100, 75, eyePaint);
        //draw the eyes
        eyePaint.setColor(eyeColor);
        canvas.drawCircle(centerx + 100, centery - 100, 50, eyePaint);
        canvas.drawCircle(centerx - 100, centery - 100, 50, eyePaint);
    }

    /**
     * draw: called from the avatarSurfaceView and draws the hairstyle, face, and eyes
     *
     * @param canvas the thing that does the drawing
     * @param centerx gives the center of the surface view for drawing all components
     * @param centery gives the center y component for drawing
     */
    public void draw(Canvas canvas, int centerx, int centery){
        hairPaint.setColor(hairColor);
        if(hairStyle == 0){
            //draw 3 circles behind the face that look like a hair style when face drawn over
            canvas.drawCircle(centerx - 100, centery - 300, 350, hairPaint);
            canvas.drawCircle(centerx + 100, centery - 300, 350, hairPaint);
            canvas.drawCircle(centerx, centery, 550, hairPaint);
        }
        if(hairStyle == 1){
            //first half of hairstyle 1.
            canvas.drawCircle(centerx, centery - 600, 350, hairPaint);
        }

        drawFace(canvas, centerx, centery);

        drawEyes(canvas, centerx, centery);

        if(hairStyle == 1){
            //finish drawing hairstyle 1
            canvas.drawCircle(centerx, centery - 700, 300, hairPaint);
        }
        if(hairStyle == 2){
            //need variable for determining how far from the right side of the face it needs to be
            int rightCorrection = 0;
            //draw a bunch of arcs to make a bowl cut
            for(int i = centerx - 400; i < centerx; i++){
                canvas.drawArc(i, centery - 600, centerx + 400 - rightCorrection++,
                        centery, 180, 180, false, hairPaint);
            }
        }

    }
}
