package com.example.rice_cs301_hw2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class AvatarSurfaceView extends SurfaceView implements SeekBar.OnSeekBarChangeListener, View.OnClickListener, AdapterView.OnItemSelectedListener{

    int red, green, blue;

    //integer to keep track of hair, skin, and eyes
    int feature = 0;

    Face mainFace = new Face();

    public AvatarSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setWillNotDraw(false);
    }

    @Override
    public void onDraw(Canvas canvas){
        mainFace.draw(canvas, this.getWidth() / 2, this.getHeight() / 2);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mainFace.setHairStyle(position);
        invalidate();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}
