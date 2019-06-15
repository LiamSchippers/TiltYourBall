package com.example.gridtest.playground.Objects;

import com.example.gridtest.playground.model.GameBoard;
import com.example.gridtest.playground.model.GameObject;

public class WallObject extends GameObject {
    public final static String IMAGE_ID_WALL = "wall";
    @Override
    public String getImageId() {
        return IMAGE_ID_WALL;
    }

    @Override
    public void onTouched(GameBoard gameBoard) {

    }
}
