package com.example.gridtest.playground.Objects;

import com.example.gridtest.playground.model.GameBoard;
import com.example.gridtest.playground.model.GameObject;

public class BallObject extends GameObject {

    public final static String IMAGE_ID_BALL = "ball";
    @Override
    public String getImageId() {
        return IMAGE_ID_BALL;
    }

    @Override
    public void onTouched(GameBoard gameBoard) {

    }
}
