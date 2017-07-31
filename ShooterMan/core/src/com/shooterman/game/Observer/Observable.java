package com.shooterman.game.Observer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Observable {
    private boolean FLAG_CHANGED = false;
    private List<Observer> observers = new ArrayList<>();


    protected void suscribe(Observer observer) {
        observers.add(observer);
    }

    protected void setChanged() {
        FLAG_CHANGED = true;
    }

    @SafeVarargs
    protected final <T> void notifySuscribers(T... Event) {
        if (FLAG_CHANGED) {
            for (Observer observer : observers)
                observer.tellEvent(this, Event);
            FLAG_CHANGED = false;
        }
    }

    protected void unSuscribe(Observer... observer) {
        for (Iterator i = observers.iterator(); i.hasNext(); )
            for (Iterator z = Arrays.asList(observer).iterator(); z.hasNext(); )
                if(z.hasNext() == i.hasNext())
                    i.remove();
    }
}
