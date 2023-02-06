package com.example.anar.utils.observer;

import com.example.anar.utils.event.Event;

public interface Observable<E extends Event> {
    void addObserver(Observer<E> e);
    void notifyObserver(E t);
}
