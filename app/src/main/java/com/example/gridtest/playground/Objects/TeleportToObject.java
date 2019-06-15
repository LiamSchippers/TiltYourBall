package com.example.gridtest.playground.Objects;

import com.example.gridtest.playground.model.GameBoard;
import com.example.gridtest.playground.model.GameObject;

public class TeleportToObject extends GameObject {

    public static final String IMAGE_ID_TELEPORT_TO = "teleport_to";
    @Override
    public String getImageId() {
        return IMAGE_ID_TELEPORT_TO;
    }

    @Override
    public void onTouched(GameBoard gameBoard) {

    }
}
