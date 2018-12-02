package com.ignited.find;

import com.ignited.AbstractAgent;

import java.util.Random;

public class MazeAgent extends AbstractAgent<Action, Result>{

    private double[][] q;
    private Random random;

    private double decay;
    private double explore;
    private int sizeY;

    public MazeAgent(MazeEnvironment env) {
        this(env, 0.9, 0.1);
    }

    public MazeAgent(MazeEnvironment env, double decay, double explore) {
        super(env);
        q = new double[env.area()][4];
        random = new Random();
        this.decay = decay;
        this.explore = explore;
        sizeY = env.sizeY();
    }

    @Override
    public boolean step(){
        int x = result.getX();
        int y = result.getY();
        Action act = getAction(x, y);
        result = env.action(act);

        q[x + y * sizeY][action(act)] = result.getReward() + decay * getMax(result.getX(), result.getY());

        return result.isDone();
    }

    private double getMax(int x, int y){
        double[] qActions = q[y * sizeY + x];
        double max = Double.NEGATIVE_INFINITY;
        for (int i = 0;i<qActions.length;++i){
            if(max < qActions[i]){
                max = qActions[i];
            }
        }
        return max;
    }

    private Action getAction(int x, int y){
        double[] qActions = q[y * sizeY+ x];
        Action[] actions = Action.values();
        Action ret;
        int m = 0;
        if(random.nextDouble() <= explore) {
            m = random.nextInt(4);
        }else {
            for (int i = 0; i < qActions.length; ++i) {
                if (qActions[m] < qActions[i]) {
                    m = i;
                } else if (qActions[m] == qActions[i]) {
                    if (random.nextInt(2) == 0) {
                        m = i;
                    }
                }
            }
        }
        ret = actions[m];

        return ret;
    }


    private int action(Action a){
        Action[] actions = Action.values();
        int ret = -1;
        for (int i = 0; i<actions.length;++i){
            if(actions[i] == a){
                ret = i;
                break;
            }
        }
        return ret;
    }

    public void changeExplore(double dec) {
        explore = explore * dec ;
    }
}
