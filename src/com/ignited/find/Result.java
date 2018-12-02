package com.ignited.find;

import com.ignited.IReward;

public class Result implements IReward {

    private int x;
    private int y;
    private double reward;
    private boolean done;

    public Result(int x, int y) {
        this.x = x;
        this.y = y;
        reward = 0;
        done = false;
    }

    public Result(int x, int y, double reward, boolean done) {
        this.x = x;
        this.y = y;
        this.reward = reward;
        this.done = done;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public double getReward() {
        return reward;
    }

    @Override
    public boolean isDone() {
        return done;
    }

    @Override
    public String toString() {
        return "Result{" +
                "x=" + x +
                ", y=" + y +
                ", reward=" + reward +
                ", done=" + done +
                '}';
    }
}
