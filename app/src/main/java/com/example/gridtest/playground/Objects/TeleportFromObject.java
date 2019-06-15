package com.example.gridtest.playground.Objects;

import com.example.gridtest.playground.model.GameBoard;
import com.example.gridtest.playground.model.GameObject;

public class TeleportFromObject extends GameObject {

    public final static String IMAGE_ID_TELEPORT_FROM = "teleport_from";
    @Override
    public String getImageId() {
        return IMAGE_ID_TELEPORT_FROM;
    }

    @Override
    public void onTouched(GameBoard gameBoard) {

    }
}
