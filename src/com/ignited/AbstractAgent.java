package com.ignited;

public abstract class AbstractAgent<A, R> {

    protected AbstractEnvironment<A, R> env;
    protected R result;


    public AbstractAgent(AbstractEnvironment<A, R> env) {
        this.env = env;

    }

    public void reset(){
        env.reset();
        result = env.getInitialState();
    }

    public abstract boolean step();

    public void learn(){
        reset();
        boolean isDone;
        do {
            isDone = step();
        }while (!isDone);
    }


}
