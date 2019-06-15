package com.example.gridtest.playground.Objects;

import com.example.gridtest.playground.model.GameBoard;
import com.example.gridtest.playground.model.GameObject;

public class FinishObject extends GameObject {

    public final static String IMAGE_ID_FINISH = "finish";
    @Override
    public String getImageId() {
        return IMAGE_ID_FINISH;
    }

    @Override
    public void onTouched(GameBoard gameBoard) {

    }
}
