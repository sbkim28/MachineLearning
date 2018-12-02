package com.ignited.find;

public class EnvironmentFactory {

    private Converter converter;

    public EnvironmentFactory() {
        this(c -> {
            c = Character.toUpperCase(c);
            Cell ret;
            switch (c){
                case 'S' :
                    ret = Cell.START;
                    break;
                case 'D' :
                    ret = Cell.DEFAULT;
                    break;
                case 'O':
                    ret = Cell.OBSTACLE;
                    break;
                case 'T':
                    ret = Cell.TRAP;
                    break;
                case 'G':
                    ret = Cell.GOAL;
                    break;
                default:
                    throw new IllegalArgumentException("String cannot be converted");
            }
            return ret;
        });
    }

    public EnvironmentFactory(Converter converter) {
        this.converter = converter;
    }

    public MazeEnvironment generateMaze(Cell[][] cells){
        if(cells == null) throw new NullPointerException("Cells cannot be null");
        int y = cells[0].length;

        MazeEnvironment env = new MazeEnvironment(cells.length, y);

        for (int i = 0;i<cells.length * y;++i){
            int col = i % y;
            int row = i / y;
            env.setCell(row, col, cells[row][col]);
        }
        return env;
    }

    public MazeEnvironment generateMaze(String maze, int x, int y){
        return generateMaze(maze, x, y, true);
    }

    public MazeEnvironment generateMaze(String name, int x, int y, boolean horizontal){

        if(name.length() != x*y) throw new IllegalArgumentException("String length does not match the area of the environment");

        MazeEnvironment env = new MazeEnvironment(x, y);

        for (int i = 0;i<x*y;++i){
            int cx = horizontal ? i % x : i / y;
            int cy = horizontal ? i / x : i % y;
            env.setCell(cx, cy, converter.convert(name.charAt(i)));
        }
        return env;
    }

    public MazeEnvironment generateMaze(String maze, String split){
        return generateMaze(maze, split, true);
    }

    public MazeEnvironment generateMaze(String maze, String split, boolean horizontal){

        String[] s = maze.split(split);
        int x;
        int y;
        if(horizontal){
            x = s[0].length();
            y = s.length;
        }else {
            x = s.length;
            y = s[0].length();
        }

        return generateMaze(String.join("", s), x, y, horizontal);
    }

    public interface Converter{
        Cell convert(char c);
    }

}
