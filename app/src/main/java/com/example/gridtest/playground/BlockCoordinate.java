package com.example.gridtest.playground;

public class BlockCoordinate {

    private int xStart;
    private int xEnd;
    private int yStart;
    private int yEnd;
    private int objectType;

    public BlockCoordinate(int xStart, int xEnd, int yStart, int yEnd, int objectType) {
        this.xStart = xStart;
        this.xEnd = xEnd;
        this.yStart = yStart;
        this.objectType = objectType;
        this.yEnd = yEnd;
    }

    public int getxStart() {
        return xStart;
    }

    public int getxEnd() {
        return xEnd;
    }

    public int getyStart() {
        return yStart;
    }

    public int getyEnd() {
        return yEnd;
    }

    public int getObjectType() {
        return objectType;
    }
}
