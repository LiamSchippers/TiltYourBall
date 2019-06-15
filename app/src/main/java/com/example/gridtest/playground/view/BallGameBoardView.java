package com.example.gridtest.playground.view;

import android.content.Context;
import android.util.AttributeSet;

import com.example.gridtest.playground.Objects.BallObject;
import com.example.gridtest.playground.Objects.FinishObject;
import com.example.gridtest.playground.Objects.HoleObject;
import com.example.gridtest.R;
import com.example.gridtest.playground.Objects.TeleportFromObject;
import com.example.gridtest.playground.Objects.TeleportToObject;
import com.example.gridtest.playground.Objects.WallObject;

public class BallGameBoardView extends GameBoardView {

    public BallGameBoardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public BallGameBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {

        spriteCache.setContext(this.getContext());

        spriteCache.loadTile("empty", R.drawable.tile);
        spriteCache.loadTile(BallObject.IMAGE_ID_BALL, R.drawable.ball_red);
        spriteCache.loadTile(FinishObject.IMAGE_ID_FINISH, R.drawable.finish_block);
        spriteCache.loadTile(HoleObject.IMAGE_ID_HOLE, R.drawable.hole_black);
        spriteCache.loadTile(WallObject.IMAGE_ID_WALL, R.drawable.wall_block);
        spriteCache.loadTile(TeleportFromObject.IMAGE_ID_TELEPORT_FROM, R.drawable.teleport_block);
        spriteCache.loadTile(TeleportToObject.IMAGE_ID_TELEPORT_TO, R.drawable.teleport_block);
    }
}
