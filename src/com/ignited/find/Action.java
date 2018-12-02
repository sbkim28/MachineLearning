package com.ignited.find;

public enum Action {
    LEFT(-1,0), DOWN(0,1), RIGHT(1,0), UP(0,-1);

    private int x;
    private int y;

    Action(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

}
