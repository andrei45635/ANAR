package com.example.anar.utils.observer;

import com.example.anar.utils.event.Event;

public interface Observer<E extends Event >{
    void update(E t);
}
