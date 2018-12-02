package com.ignited.test;


import com.ignited.find.*;

public class TEST {

    private static final int number = 2000;

    public static void main(String[] args){

        // sample code

        MazeEnvironment environment = new EnvironmentFactory().generateMaze("SDTDO DODDT DDDTG DTDDD DDDTD", " ");
        MazeAgent agent = new MazeAgent(environment);

        for (int i = 0; i<number;++i) {
            agent.learn();
            agent.changeExplore(0.99);
        }
    }
}
