package com.example.rice_cs301_hw2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * @author Gareth Rice
 * @version 3/21
 *
 * Notes:
 *  Houses all of the actions for seekbar, buttons, and spinner.
 *
 * Known Bugs:
 *
 */

public class AvatarSurfaceView extends SurfaceView implements SeekBar.OnSeekBarChangeListener,
        View.OnClickListener, AdapterView.OnItemSelectedListener{

    int red, green, blue;

    //integer to keep track of hair, skin, and eyes
    int feature = 0;

    Face mainFace = new Face();

    //need seekbars in surface view so their progress can be set when different component
    //is selected
    SeekBar redBar;
    SeekBar greenBar;
    SeekBar blueBar;

    int currentColor;

    public AvatarSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setWillNotDraw(false);
    }

    /**
     * setBars: Get the bars that were linked in the MainActivity
     *
     * @param red the seekbar assigned to seekbar in this class
     * @param green
     * @param blue
     */
    public void setBars(SeekBar red, SeekBar green, SeekBar blue){
        redBar = red;
        greenBar = green;
        blueBar = blue;
    }

    /**
     * onDraw: calls draw method in the  main face and updates the seekbars with correct
     * color values
     *
     * @param canvas
     */
    @Override
    public void onDraw(Canvas canvas){
        mainFace.draw(canvas, this.getWidth() / 2, this.getHeight() / 2);
        updateSeekBar(feature);
    }

    /**
     * onProgressChange: When seekbar is changed, color is changed and updated in mainFace
     *
     * @param seekBar
     * @param progress
     * @param fromUser
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        //Depending on seekBar change red, green, or blue int that will make up paint color
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

        //depending on radioButton feature selection, set the color to appropriate feature
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

    /**
     * onRadioButtonClicked: select the feature whose color/style should be updated
     *
     * @param view
     */
    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        //set feature to 0, 1, or 2 based on which radio button is selected
        switch(view.getId()){
            case R.id.hairRButton:
                feature = 0;
                break;
            case R.id.eyesRButton:
                feature = 1;
                break;
            case R.id.skinRButton:
                feature = 2;
                break;
            default:
                break;
        }

        updateSeekBar(feature);

    }

    /**
     * updateSeekBar: change color seekBars to reflect color of feature selected by radio button
     *
     * @param theFeature
     */
    public void updateSeekBar(int theFeature){

        //get the color that will be displayed on seekBars depending on highlighted features.
        if(theFeature == 0){
            currentColor = mainFace.getHairColor();
        }else if(theFeature == 1){
            currentColor = mainFace.getEyeColor();
        }else{
            currentColor = mainFace.getSkinColor();
        }

        //set red, green, and blue bars based on currentColor
        redBar.setProgress(Color.red(currentColor));
        greenBar.setProgress(Color.green(currentColor));
        blueBar.setProgress(Color.blue(currentColor));
    }

    /**
     * onClick: randomize face if random face button is clicked. Otherwise, it's the radio
     * button and it should update
     * @param v
     */
    @Override
    public void onClick(View v) {
        //if face button is hit, make random face with random colors and hair styles
        //else call function for radioButton Clicked
        if(v.getId() == R.id.randomButton){
            mainFace.randomize();
        }else{
            onRadioButtonClicked(v);
        }

        invalidate();
    }

    /**
     * onItemSelected: Update hairstyle based on spinner selection
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mainFace.setHairStyle(position);
        invalidate();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}
