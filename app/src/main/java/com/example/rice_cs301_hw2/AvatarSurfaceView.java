package com.example.rice_cs301_hw2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.SeekBar;

public class AvatarSurfaceView extends SurfaceView implements SeekBar.OnSeekBarChangeListener, View.OnClickListener{

    int red, green, blue;

    SeekBar redSeekBar;
    SeekBar greenSeekBar;
    SeekBar blueSeekBar;

    int rBtnHairID;
    int rBtnEyesID;
    int rBtnSkinID;

    RadioButton rBtnHair;
    RadioButton rBtnEyes;
    RadioButton rBtnSkin;

    //integer to keep track of hair, skin, and eyes
    int feature = 0;

    Face mainFace = new Face();

    public AvatarSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setWillNotDraw(false);
    }

    @Override
    public void onDraw(Canvas canvas){
        mainFace.draw(canvas);
    }

    public void setSeekBar(SeekBar rSeek, SeekBar gSeek, SeekBar bSeek){
        redSeekBar = rSeek;
        greenSeekBar = gSeek;
        blueSeekBar = bSeek;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        //depending on which seekbar is used, change r, g, and b values then set color
//        if(seekBar.getId() == redSeekBar.getId()){
//            red = progress;
//        }else if(seekBar.getId() == greenSeekBar.getId()){
//            green = progress;
//        }else {
//            blue = progress;
//        }

        switch(seekBar.getId()){
            case R.id.redSeekBar:
                red = progress;
                break;
            case R.id.greenSeekBar:
                green = progress;
                break;
            case R.id.blueSeekBar:
                blue = progress;
                break;
            default:
                break;
        }

        if(feature == 0){
            mainFace.setHairColor(Color.rgb(red, green, blue));
        }else if(feature == 1){
            mainFace.setEyeColor(Color.rgb(red, green, blue));
        }else{
            mainFace.setSkinColor(Color.rgb(red, green, blue));
        }

        invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}

//    @Override
//    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        if(buttonView.getId() == rBtnHair.getId()){
//            feature = 0;
//        }else if(buttonView.getId() == rBtnEyes.getId()){
//            feature = 1;
//        }else{
//            feature = 2;
//        }
//    }

    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()){
            case R.id.hairRButton:
                if(checked){
                    feature = 0;
                }
                break;
            case R.id.eyesRButton:
                if(checked){
                    feature = 1;
                }
                break;
            case R.id.skinRButton:
                if(checked){
                    feature = 2;
                }
                break;
            default:
                break;
        }

    }

    @Override
    public void onClick(View v) {
        onRadioButtonClicked(v);
    }
}
