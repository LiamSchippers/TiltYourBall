package com.example.gridtest.playground;

import com.example.gridtest.playground.model.GameBoard;

public class BallGameBoard extends GameBoard {

    public BallGameBoard() {
        super(25, 12);
    }

    @Override
    public void onEmptyTileClicked(int x, int y) {

    }

    public String getBackgroundImg(int mx, int my) {
        return "empty";
    }
}
