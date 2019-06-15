package com.example.gridtest.playground.model;


import android.media.MediaPlayer;

import com.example.gridtest.playground.BallGameBoard;
import com.example.gridtest.playground.BlockCoordinate;
import com.example.gridtest.playground.GameActivity;
import com.example.gridtest.playground.view.BallGameBoardView;

import java.util.ArrayList;

public class BallModel{

    private float xPos;
    private float yPos;
    private float xAccel, xVel;
    private float yAccel, yVel;
    private float xMax, yMax;
    private BallGameBoardView gameView;
    private BallGameBoard gameBoard;
    private ArrayList<BlockCoordinate> blocks;

    public BallModel() {


    }

    public void checkPos(BlockCoordinate block,MediaPlayer mediaPlayerTeleport) {




        if (block.getObjectType() == 1) {


            // Onderkant van de bal checken
            // Als een tijdelijke coördinaat in een muur/block ligt, laat de bal niet verder rollen
            float tempCoY1 = yPos + 38;
            if (xPos > block.getxStart() && xPos < block.getxEnd() && tempCoY1 > block.getyStart() && tempCoY1 < block.getyEnd()) {
                yVel = 0;
                yPos = block.getyStart() - 38;
                return;
            }

            // Linkerkant van de bal checken
            // Als een tijdelijke coördinaat in een muur/block ligt, laat de bal niet verder rollen
            float tempCoX1 = xPos - 35;
            if (tempCoX1 > block.getxStart() && tempCoX1 < block.getxEnd() && yPos > block.getyStart() && yPos < block.getyEnd()) {
                xVel = 0;
                xPos = block.getxEnd() + 35;
                return;
            }

            // Rechterkant van de bal checken
            // Als een tijdelijke coördinaat in een muur/block ligt, laat de bal niet verder rollen
            float tempCoX2 = xPos + 38;
            if (tempCoX2 > block.getxStart() && tempCoX2 < block.getxEnd() && yPos > block.getyStart() && yPos < block.getyEnd()) {
                xVel = 0;
                xPos = block.getxStart() - 38;
                return;

            }

            // Bovenkant van de bal checken
            // Als een tijdelijke coördinaat in een muur/block ligt, laat de bal niet verder rollen
            float tempCoY2 = yPos - 38;
            if (xPos > block.getxStart() && xPos < block.getxEnd() && tempCoY2 > block.getyStart() && tempCoY2 < block.getyEnd()) {
                yVel = 0;
                yPos = block.getyEnd() + 38;
                return;
            }


        } else if (block.getObjectType() == 2){
            if (xPos > block.getxStart() + 15 && xPos < block.getxEnd() - 15 && yPos > block.getyStart() + 18 && yPos < block.getyEnd() - 18) {
                yVel = 0;
                xVel = 0;

                GameActivity.gameOver = true;
            }
        } else if (block.getObjectType() == 3) {
            if (xPos > block.getxStart() && xPos < block.getxEnd() && yPos > block.getyStart() && yPos < block.getyEnd()) {
                yVel = 0;
                xVel = 0;

                GameActivity.finished = true;
            }
        } else if (block.getObjectType() == 4) {
            if (xPos > block.getxStart() && xPos < block.getxEnd() && yPos > block.getyStart() && yPos < block.getyEnd()) {
                for (BlockCoordinate teleportToBlock : blocks) {
                    if (teleportToBlock.getObjectType() == 5) {
                        mediaPlayerTeleport.start();
                        float xPosNew = ((teleportToBlock.getxEnd() - teleportToBlock.getxStart()) / 2) + teleportToBlock.getxStart();
                        float yPosNew = ((teleportToBlock.getyEnd() - teleportToBlock.getyStart()) / 2) + teleportToBlock.getyStart();
                        setBallPos(xPosNew,yPosNew);
                    }
                }
            }
        }
    }


    public void setGameView(BallGameBoardView view) {
        this.gameView = view;
        this.blocks = gameView.blocks;
    }

    public void setGameBoard(BallGameBoard board) {
        this.gameBoard = board;
    }

    public void setXYAccel(float xAccel, float yAccel) {
        this.xAccel = xAccel;
        this.yAccel = yAccel;
    }

    public void setMax(float xMax, float yMax) {
        this.xMax = xMax;
        this.yMax = yMax;
    }

    public void setVel(float frameTime) {
        this.xVel += (xAccel * frameTime);
        this.yVel += (yAccel * frameTime);
    }

    public void setPos(float frameTime) {
        float xS = (xVel / 2) * frameTime;
        float yS = (yVel / 2) * frameTime;

        this.xPos -= xS;
        this.yPos -= yS;
    }

    public void setBallPos(float x, float y) {
        this.xPos = x;
        this.yPos = y;
    }

    public float getxPos() {
        return xPos;
    }

    public float getyPos() {
        return yPos;
    }


}
