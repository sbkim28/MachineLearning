package com.ignited.find;

import com.ignited.AbstractEnvironment;

public class MazeEnvironment extends AbstractEnvironment<Action, Result> {

    private Cell[][] cells;

    private int playerX;
    private int playerY;

    private double fail;
    private double success;

    private int startX;
    private int startY;

    public MazeEnvironment(int x, int y) {
        this(x, y, -1, 1);
    }

    public MazeEnvironment(int x, int y, double fail, double success){
        if(x <= 0 || y <= 0) throw new IllegalArgumentException("param x and y cannot be 0 or negative number.");
        cells = new Cell[x][y];
        this.fail = fail;
        this.success = success;
        for (int i = 0;i<x*y;++i) {
            int col = i % y;
            int row = i / y;
            cells[row][col] = Cell.DEFAULT;
        }
        startX = -1;
        startY = -1;
        playerX = -1;
        playerY = -1;
    }

    @Override
    public void reset(){
        playerX = -1;
        playerY = -1;
        super.reset();
    }

    @Override
    public Result init(){
        if(startY == -1 || startX == -1)
            throw new IllegalStateException("Start cell has not been assigned.");

        playerX = startX;
        playerY = startY;
        ready = true;
        return new Result(playerX, playerY);
    }

    public void setCell(int x, int y, Cell cell){
        if(cells.length <= x || cells[0].length <= y) throw new ArrayIndexOutOfBoundsException();

        if(cell == Cell.START){
            if(startX != -1 && startY != -1) {
                cells[startX][startY] = Cell.DEFAULT;
            }
            startX = x;
            startY = y;
        }else if(cells[x][y] == Cell.START){
            startX = -1;
            startY = -1;
        }
        cells[x][y] = cell;
    }

    public int sizeX(){
        return cells.length;
    }

    public int sizeY(){
        return cells[0].length;
    }

    public int area(){
        return cells.length * cells[0].length;
    }

    @Override
    public Result changeState(Action a){
        playerX += a.x();
        playerY += a.y();
        if(playerX < 0){
            playerX = 0;
        }else if(playerX >= cells.length){
            playerX = cells.length - 1;
        }
        if(playerY < 0){
            playerY = 0;
        }else if(playerY >= cells[0].length){
            playerY = cells[0].length - 1;
        }

        Cell c = cells[playerX][playerY];
        boolean done = false;
        double reward = 0;
        if(c == Cell.OBSTACLE){
            playerX -= a.x();
            playerY -= a.y();
        }else if(c==Cell.TRAP){
            reward = fail;
            done = true;
        }else if(c== Cell.GOAL){
            reward = success;
            done = true;
        }

        return new Result(playerX, playerY, reward, done);
    }

}
