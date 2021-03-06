package com.example.rice_cs301_hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Button;

/**
 * @author Gareth Rice
 * @version 3/21
 *
 * Notes:
 *
 * Known Bugs:
 *  Hair in spinner doesn't update when face is randomized. Not sure if that was a requirement...
 */

public class MainActivity extends AppCompatActivity {

    /**
     * setup the program by finding components and linking them to the surfaceView where
     * there action controllers are
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //obtain references to seekBars
        SeekBar redSeekBar = (SeekBar) findViewById(R.id.redSeekBar);
        SeekBar greenSeekBar = (SeekBar) findViewById(R.id.greenSeekBar);
        SeekBar blueSeekBar = (SeekBar) findViewById(R.id.blueSeekBar);

        AvatarSurfaceView avatarSurfaceView = (AvatarSurfaceView) findViewById(R.id.avatarView);

        //attach all the seek bars to the avatarSurfaceView which has appropriate methods
        redSeekBar.setOnSeekBarChangeListener(avatarSurfaceView);
        greenSeekBar.setOnSeekBarChangeListener(avatarSurfaceView);
        blueSeekBar.setOnSeekBarChangeListener(avatarSurfaceView);

        RadioButton hairRButton = (RadioButton) findViewById(R.id.hairRButton);
        RadioButton eyesRButton = (RadioButton) findViewById(R.id.eyesRButton);
        RadioButton skinRButton = (RadioButton) findViewById(R.id.skinRButton);

        hairRButton.setOnClickListener(avatarSurfaceView);
        eyesRButton.setOnClickListener(avatarSurfaceView);
        skinRButton.setOnClickListener(avatarSurfaceView);

        //This is all magic that sets up the spinner for me, from the documentation...
        Spinner hairSpinner = (Spinner) findViewById(R.id.hairSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.hairstyle_select_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hairSpinner.setAdapter(adapter);

        //Attach all components to the avatarSurfaceView which contains most of the actions
        hairSpinner.setOnItemSelectedListener(avatarSurfaceView);

        //Make the random face button that randomizes color and hairstyle
        Button randomButton = (Button) findViewById(R.id.randomButton);
        randomButton.setOnClickListener(avatarSurfaceView);

        avatarSurfaceView.setBars(redSeekBar, greenSeekBar, blueSeekBar);
    }
}