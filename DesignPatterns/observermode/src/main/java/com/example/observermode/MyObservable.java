package com.example.observermode;

public interface MyObservable {
    void addObserver(MyObserver o);
    void deleteObserver(MyObserver o);
    void notifyObservers(String arg);
}
