package com.example.observermode.javaobserver;

import java.util.Observable;

public class MessagePublisher extends Observable {
    private static MessagePublisher instance = new MessagePublisher();
    private MessagePublisher(){}
    public static MessagePublisher getInstance(){
        return instance;
    }

    public void notifyAllObservers() {
        setChanged();
        notifyObservers();
    }
}
