package com.example.gridtest.playground.Objects;

import com.example.gridtest.playground.model.GameBoard;
import com.example.gridtest.playground.model.GameObject;

public class HoleObject extends GameObject {

    public final static String IMAGE_ID_HOLE = "hole";
    @Override
    public String getImageId() {
        return IMAGE_ID_HOLE;
    }

    @Override
    public void onTouched(GameBoard gameBoard) {

    }
}
