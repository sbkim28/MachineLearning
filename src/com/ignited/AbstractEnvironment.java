package com.ignited;

public abstract class AbstractEnvironment<A, R> {

    protected boolean ready;

    public void reset(){
        ready = false;
    }

    public R getInitialState(){
        if(ready) throw new IllegalStateException("Already started");

        R r = init();
        ready = true;
        return r;
    }

    protected abstract R init();

    public R action(A action){
        if(!ready) throw new IllegalStateException("Not ready");
        return changeState(action);
    }

    protected abstract R changeState(A action);

    public boolean isReady() {
        return ready;
    }
}
