package com.example.gridtest.playground;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.gridtest.R;
import com.example.gridtest.playground.database.LevelDatabase;
import com.example.gridtest.playground.database.MyDatabase;

public class MainActivity extends Activity {

    public static MyDatabase myDatabase;
    public static LevelDatabase levelDatabase;
    private MediaPlayer clickSound1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_main_menu);
        //adding a sound that is played when the player presses a button
        clickSound1 = MediaPlayer.create(this,R.raw.switch32);

        //Setting the activity buttons
        ImageButton startGame = findViewById(R.id.ibStartButton);
        startGame.setImageResource(R.drawable.button);

        ImageButton highScores = findViewById(R.id.ibHighScores);
        highScores.setImageResource(R.drawable.button);

        ImageButton levelSelect = findViewById(R.id.ibLevelSelect);
        levelSelect.setImageResource(R.drawable.button);

        //If there is no databse a databse will be made where the user can save the scores that where achieved
        //Else the databse that was already made will be used to save and retrieve scores
        if(myDatabase == null){
            myDatabase = new MyDatabase(this, null);
        }

        if (levelDatabase == null) {
            levelDatabase = new LevelDatabase(this, null);
            levelDatabase.firstSave();
        }

        //making the user able to click on the buttons and starting the corresponding activity
        //When the start button is clicked the GameActivity will be started
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameView = new Intent(MainActivity.this, GameActivity.class);
                clickSound1.start();
                startActivity(gameView);
                finish();

            }
        });

        //When the highScores button is clicked the HighscoreActivity will be started
        highScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent highscoreView = new Intent(MainActivity.this, HighscoreActivity.class);
                clickSound1.start();
                startActivity(highscoreView);
            }
        });

        //when the levelSelect button is clicked the LevelSelectActivity will be started
        levelSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent levelSelectView = new Intent(MainActivity.this, LevelselectActivity.class);
                clickSound1.start();
                startActivity(levelSelectView);

            }
        });
    }
}

