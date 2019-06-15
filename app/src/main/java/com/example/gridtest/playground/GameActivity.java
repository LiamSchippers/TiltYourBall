package com.example.gridtest.playground;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.gridtest.R;
import com.example.gridtest.playground.model.BallModel;
import com.example.gridtest.playground.model.GameBoard;
import com.example.gridtest.playground.model.Player;
import com.example.gridtest.playground.view.BallGameBoardView;
import com.example.gridtest.playground.view.BallView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GameActivity extends Activity implements SensorEventListener2{

    public static boolean gameOver;
    public static boolean finished;

    private BallGame game;
    private BallGameBoardView gameView;
    private BallModel ballModel;
    private BallView ballView;
    private SensorManager sensorManager;
    private Button btnPause;
    final Context context = this;
    private TextView tvTime;
    private MediaPlayer clickSound, clickSound2, teleportSound;

    private long elapsedTime;
    private long startTime;
    private long currentTime;

    private int score;

    @SuppressLint({"SetTextI18n", "SimpleDateFormat"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clickSound = MediaPlayer.create(this,R.raw.switch31);
        clickSound2 = MediaPlayer.create(this,R.raw.switch32);
        teleportSound = MediaPlayer.create(this,R.raw.teleport);

        //gameOver & finished set to false so that the player can always play whenever the game is started
        //makes sure that the player is not immediately game over or finished with the level
        gameOver = false;
        finished = false;

        gameView = findViewById(R.id.game);
        btnPause = findViewById(R.id.btnPause);

        ballModel = new BallModel();
        game = new BallGame(this);

        Intent intent = getIntent();
        int index = intent.getIntExtra("index", 1);
        game.startSelectedLevel(index);
        checkLevelBallPos(index);

        // Tell the game board view which game board to show
        GameBoard board = game.getGameBoard();
        gameView.setGameBoard(board);
        gameView.setFixedGridSize(board.getWidth(), board.getHeight());

        ballView = findViewById(R.id.ballView);
        ballView.setModel(ballModel);
        ballModel.setGameView(gameView);
        tvTime = findViewById(R.id.tvTime);
        TextView tvLevel = findViewById(R.id.tvLevel);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //Whenever the pause button is clicked the game will pause and the pause menu will show up
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSound2.start();
                pauseMenu();
            }
        });

        //The timer is set to 0 and will be shown on screen
        elapsedTime = 0;
        startTime = (System.currentTimeMillis());
        currentTime = elapsedTime + (System.currentTimeMillis() - startTime);
        tvTime.setText("" + new SimpleDateFormat("mm:ss:SS").format(new Date(currentTime)));
        tvLevel.setText("Level " + game.getCurrentLevel());
    }

    @Override
    protected void onStart() {
        super.onStart();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onStop() {
        sensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    public void onFlushCompleted(Sensor sensor) {

    }

    @SuppressLint({"SetTextI18n", "SimpleDateFormat"})
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER && !gameOver && !finished) {
            //whenever the player tilts their phone the ball acceleration will be updated
            float yAccel = -sensorEvent.values[0];
            float xAccel = -sensorEvent.values[1];

            ballModel.setXYAccel(xAccel, yAccel);
            updateBall();

            //the timer gets updated and the new time will be shown on the screen
            currentTime = elapsedTime + (System.currentTimeMillis() - startTime);
            tvTime.setText("" + new SimpleDateFormat("mm:ss:SS").format(new Date(currentTime)));

        }
        //when the player falls in a hole gameOver will be set to true and the gameOver() method will be called
        else if (gameOver){
            gameOver();
        }

        //when the player reached the finish finished will be set to true and the levelFinished() method will be called
        else if (finished){
            levelFinished();
        }
    }

    protected void updateBall() {
        float frameTime = 0.5f;
        for (int i = 0; i < gameView.blocks.size(); i++) {
            ballModel.checkPos(gameView.blocks.get(i), teleportSound);
        }
        ballModel.setPos(frameTime);
        ballModel.setVel(frameTime);
        ballView.invalidate();

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    //when the onPause() is called the timer will be set to a pause
    //the sensor will stop sending updates so that the ball will not move when the game is paused
    @SuppressLint({"SetTextI18n", "SimpleDateFormat"})
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
        elapsedTime = elapsedTime + (System.currentTimeMillis() - startTime);
        currentTime = elapsedTime;
        tvTime.setText("" + new SimpleDateFormat("mm:ss:SS").format(new Date(currentTime)));
    }

    //when the onResume() is called the timer will continue with the last time is registered
    //the accelerometer will be enabled again so that the player can move the ball again
    @SuppressLint({"SetTextI18n", "SimpleDateFormat"})
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
        startTime = (System.currentTimeMillis());
        currentTime = elapsedTime + (System.currentTimeMillis() - startTime);
        tvTime.setText("" + new SimpleDateFormat("mm:ss:SS").format(new Date(currentTime)));
    }

    //When the back button is pressed the game will pause and the pause menu will show
    @Override
    public void onBackPressed() {
        clickSound.start();
        pauseMenu();
    }

    //When this method is called a custom dialog containing the pause menu will show up
    @SuppressLint("SetTextI18n")
    public void pauseMenu() {
        // custom dialog
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.pause_dialog);

        //making sure the dialog can't be closed when the back button is pressed or the player touches the screen outside of the dialog
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        // set the custom dialog components - text, image and button
        Button btnContinue = dialog.findViewById(R.id.btnContinue);
        Button btnRestart = dialog.findViewById(R.id.btnRestart);
        Button btnMainMenu = dialog.findViewById(R.id.btnMainMenu);
        TextView tvLevel = dialog.findViewById(R.id.tvCurrentLevel);

        onPause();

        //if a button is clicked, close the custom dialog
        //When the continue button is pressed the pause menu closes and the level will resume where you left off
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResume();
                dialog.dismiss();

            }
        });

        //When the restart button is pressed the pause menu closes and the activity is restarted
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
                dialog.dismiss();
            }
        });

        //when the main menu button is pressed the main menu activity is started and this activity closes
        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(GameActivity.this, MainActivity.class);
                dialog.dismiss();
                startActivity(newIntent);
                finish();
            }
        });
        dialog.show();
        //the cutom dialog will show thee player which level he/she is in
        tvLevel.setText("Level " + game.getCurrentLevel());
    }

    //Whenever this method is called the game over dialog shows up so that the user knows the game is over
    //From here the user can choose to go back to the main menu or to restart the level
    public void gameOver(){
        //custom dialog
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.game_over_dialog);

        //making sure the dialog can't be closed when the back button is pressed or the player touches the screen outside of the dialog
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        // set the custom dialog components - text, image and button
        Button btnRestart = dialog.findViewById(R.id.restartButton);
        Button btnMainMenu = dialog.findViewById(R.id.mainMenuButton);

        onPause();

        //When the restart button is pressed the pause menu closes and the activity is restarted
        //boolean gameOver is set to false so that you aren't immidiatly game over when you restart
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
                dialog.dismiss();
            }
        });
        //when the main menu button is pressed the main menu activity is started and this activity closes
        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(GameActivity.this, MainActivity.class);
                dialog.dismiss();
                startActivity(newIntent);
                finish();
            }
        });

        dialog.show();
    }

    //This method will be called whenever the player finishes the current level he is in
    //a custom dialg will be shown on screen where the player can choose to restart the level, go to the next level or return to the main menu
    //also the player will be able to see the score that was managed in the level and can choose to fill in their name and save their score for the level
    public void levelFinished(){
        //custom dialog
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.level_finished_dialog);

        // Tell the LevelSelectActivity which buttons can be enabled
        if (LevelselectActivity.index < game.getCurrentLevel() + 1){
            LevelselectActivity.index = game.getCurrentLevel() + 1;
            MainActivity.levelDatabase.saveLevels();
        }

        //making sure the dialog can't be closed when the back button is pressed or the player touches the screen outside of the dialog
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        // set the custom dialog components - text, image and button
        Button btnNextlevel = dialog.findViewById(R.id.lfBtnNextLevel);
        Button btnRestart = dialog.findViewById(R.id.lfBtnRestartLevel);
        Button btnMainMenu = dialog.findViewById(R.id.lfBtnMainMenu);
        TextView tvScore = dialog.findViewById(R.id.textViewScorePlayer);
        final EditText editTextPlayerName = dialog.findViewById(R.id.editTextNamePlayer);
        final Button saveButton = dialog.findViewById(R.id.buttonSavePlayer);

        onPause();

        //the score is calculated based on the time the player took to finish the level
        score = (int) ((elapsedTime/100) * -2);
        score = 1000 + score;
        tvScore.setText(String.valueOf(score));

        // Only specified buttons can be enabled
        if (game.getCurrentLevel() == 5) {
            btnNextlevel.setEnabled(false);
        }

        //whenever this button is clicked the next level will start
        btnNextlevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentLevel = game.getCurrentLevel();
                if (currentLevel >= 5) {
                    return;
                }
                game.startSelectedLevel(currentLevel + 1);
                LevelselectActivity.index = currentLevel + 1;
                Intent intent = new Intent(GameActivity.this, GameActivity.class);
                intent.putExtra("index", currentLevel + 1);
                dialog.dismiss();
                startActivity(intent);

            }
        });

        //When the restart button is pressed the custom dialog closes and the activity is restarted
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
                dialog.dismiss();
            }
        });

        //when the main menu button is pressed the main menu activity is started and this activity closes
        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(GameActivity.this, MainActivity.class);
                dialog.dismiss();
                startActivity(newIntent);
                finish();
            }
        });

        //whenever this butten is clicked the player wil save the score, to the database, that was achieved in the level
        //the score will only be saved when the player has filled in a name
        //the score can only be saved once each time the player finishes a level
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editTextPlayerName.getText().toString().matches("")) {
                    MainActivity.myDatabase.addPlayer(new Player(editTextPlayerName.getText().toString(), score, game.getCurrentLevel()));
                    saveButton.setEnabled(false);
                }
            }
        });

        dialog.show();
    }

    //setting the spawn position for the ball for each level
    //index is equal to the current level the player is in
    public void checkLevelBallPos(int index) {
        switch (index){
            case 1: case 3: case 4: case 5:
                ballModel.setBallPos(200, 250);
                break;
            case 2:
                ballModel.setBallPos(200,1000);
                break;
            default:
                ballModel.setBallPos(200, 250);
                break;
        }
    }
}
