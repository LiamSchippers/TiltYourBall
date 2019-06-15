package com.example.gridtest.playground;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.gridtest.R;
import com.example.gridtest.playground.model.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class LevelselectActivity extends Activity {
    private MediaPlayer clickSound2;
    public static int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //adding a sound that is played when the player presses a button
        clickSound2 = MediaPlayer.create(this,R.raw.switch31);

        //hide title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set app to full screen and keep screen on
        getWindow().setFlags(0xFFFFFFF, WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levelselect);

        //setting the index to highest level the player has unlocked
        index = MainActivity.levelDatabase.loadLevels();

        //setting the activity buttons
        Button btnLevel2 = findViewById(R.id.buttonLevel2);
        Button btnLevel3 = findViewById(R.id.buttonLevel3);
        Button btnLevel4 = findViewById(R.id.buttonLevel4);
        Button btnLevel5 = findViewById(R.id.buttonLevel5);

        //if the level isn't unlocked yet the alpha is set ot 25% so the player can see which levels are locked
        if (!btnLevel2.isEnabled()){
            btnLevel2.setAlpha((float) 0.25);
        }
        if (!btnLevel3.isEnabled()){
            btnLevel3.setAlpha((float) 0.25);
        }
        if (!btnLevel4.isEnabled()){
            btnLevel4.setAlpha((float) 0.25);
        }
        if (!btnLevel5.isEnabled()){
            btnLevel5.setAlpha((float) 0.25);
        }

        //Checking to see which levels are unlocked and setting the alpha to 100% so the player can see which levels are unlocked
        if (index == 2 || index >= 2) {
            btnLevel2.setEnabled(true);
            btnLevel2.setAlpha(1);
        }
        if (index == 3 || index >= 3) {
            btnLevel3.setEnabled(true);
            btnLevel3.setAlpha(1);
        }
        if (index == 4 || index >= 4) {
            btnLevel4.setEnabled(true);
            btnLevel4.setAlpha(1);
        }
        if (index == 5 || index >= 5) {
            btnLevel5.setEnabled(true);
            btnLevel5.setAlpha(1);
        }
    }

    //if a button is  clicked the corresponding level will be loaded into the GameActivity
    public void clickLevel1(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("index", 1);
        clickSound2.start();
        startActivity(intent);
    }

    public void clickLevel2(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("index", 2);
        clickSound2.start();
        startActivity(intent);
    }

    public void clickLevel3(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("index", 3);
        clickSound2.start();
        startActivity(intent);
    }
    public void clickLevel4(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("index", 4);
        clickSound2.start();
        startActivity(intent);
    }
    public void clickLevel5(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("index", 5);
        clickSound2.start();
        startActivity(intent);
    }
}
