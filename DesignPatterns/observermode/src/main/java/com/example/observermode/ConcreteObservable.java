package com.example.observermode;

import java.util.Observer;
import java.util.Vector;

public class ConcreteObservable implements MyObservable {

    private Vector<MyObserver> obs;

    public ConcreteObservable() {
        obs = new Vector<>();
    }

    @Override
    public void addObserver(MyObserver o) {
        obs.add(o);
    }

    @Override
    public void deleteObserver(MyObserver o) {
        obs.remove(o);
    }

    @Override
    public void notifyObservers(String arg) {
        for (MyObserver observer : obs) {
            observer.update(arg);
        }
    }
}
