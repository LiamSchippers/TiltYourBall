package com.example.gridtest.playground;


import com.example.gridtest.R;
import com.example.gridtest.playground.Objects.FinishObject;
import com.example.gridtest.playground.Objects.HoleObject;
import com.example.gridtest.playground.Objects.TeleportFromObject;
import com.example.gridtest.playground.Objects.TeleportToObject;
import com.example.gridtest.playground.Objects.WallObject;
import com.example.gridtest.playground.model.Game;
import com.example.gridtest.playground.model.GameBoard;

import java.io.IOException;
import java.io.InputStream;

public class BallGame extends Game{

    private int currentLevel = 1;
    private GameActivity activity;

    public BallGame(GameActivity mainActivity) {
        super(new BallGameBoard());

        this.activity = mainActivity;
    }

    /**
     * StartSelectedLevel will load in the corresponding csv file
     * When the csv file has been loaded and read it will load in the GameObjects
     * It will check which of the GameObjects it is and will draw that object onto the corresponding tiles
     * The GameObjects will be loaded for each Y co-coordinate
     * @param level
     */
    public void startSelectedLevel(int level) {
        //Createing a GameBoard nad deleteing all objects
        GameBoard board = getGameBoard();
        board.removeAllObjects();

        String fileContent = "";

        //if the level is 1 than the csv file for level 1 will be loaded and read
        if (level == 1) {
            this.currentLevel = 1;
            InputStream is = this.activity.getBaseContext().getResources().openRawResource(R.raw.level_1);
            try {
                byte[] b = new byte[is.available()];
                is.read(b);
                fileContent = new String(b);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //if the level is 2 than the csv file for level 2 will be loaded and read
        else if (level == 2) {
            this.currentLevel = 2;
            InputStream is = this.activity.getBaseContext().getResources().openRawResource(R.raw.level_2);
            try {
                byte[] b = new byte[is.available()];
                is.read(b);
                fileContent = new String(b);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //if the level is 3 than the csv file for level 3 will be loaded and read
        else if (level == 3) {
            currentLevel = 3;
            InputStream is = this.activity.getBaseContext().getResources().openRawResource(R.raw.level_3);
            try {
                byte[] b = new byte[is.available()];
                is.read(b);
                fileContent = new String(b);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //if the level is 4 than the csv file for level 4 will be loaded and read
        else if (level == 4) {
            currentLevel = 4;
            InputStream is = this.activity.getBaseContext().getResources().openRawResource(R.raw.level_4);
            try {
                byte[] b = new byte[is.available()];
                is.read(b);
                fileContent = new String(b);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //if the level is 5 than the csv file for level 5 will be loaded and read
        else if (level == 5) {
            currentLevel = 5;
            InputStream is = this.activity.getBaseContext().getResources().openRawResource(R.raw.level_5);
            try {
                byte[] b = new byte[is.available()];
                is.read(b);
                fileContent = new String(b);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Splitting on a new line in the csv file
        String[] row = fileContent.split("\n");
        for (int yCO = 0; yCO < row.length; yCO++) {
            String newRow = row[yCO];

            // Splitting on a comma in the csv file
            String[] objectIndex = newRow.split(",");
            for (int xCO = 0; xCO < objectIndex.length; xCO++) {
                // Check to see if the object is a wall
                if (objectIndex[xCO].equals("1")) {
                    board.addGameObject(new WallObject(),xCO,yCO);
                }

                // Check to see if the object is a hole
                else if (objectIndex[xCO].equals("2")) {
                    board.addGameObject(new HoleObject(),xCO,yCO);
                }

                // Check to see if the object is a finish
                else if (objectIndex[xCO].equals("3")) {
                    board.addGameObject(new FinishObject(),xCO,yCO);
                }

                //Check to see if the object is the teleportObject that the player can to enter
                else if (objectIndex[xCO].equals("4")) {
                    board.addGameObject(new TeleportFromObject(),xCO,yCO);
                }

                //Check to see if the object is the teleportObject that the player will be teleported to
                else if (objectIndex[xCO].equals("5")) {
                    board.addGameObject(new TeleportToObject(),xCO,yCO);
                }
            }
        }
    }

    public int getCurrentLevel() {
        return currentLevel;
    }
}
