package com.shooterman.game.Observer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Observable {
    private final List<Observer> observers = new ArrayList<>();
    private boolean FLAG_CHANGED = false;

    protected final void setChanged() {
        FLAG_CHANGED = true;
    }

    @SafeVarargs
    protected final <T> void notifySuscribers(T... Data) {
        if (FLAG_CHANGED) {
            for (Observer observer : observers)
                observer.tellEvent(this, Data);
            FLAG_CHANGED = false;
        }
    }

    public final void subscribe(Observer observer) {
        observers.add(observer);
    }

    public final void unSubscribe(Observer... observer) {
        for (Iterator i = observers.iterator(); i.hasNext(); )
            for (Iterator z = Arrays.asList(observer).iterator(); z.hasNext(); )
                if (z.hasNext() == i.hasNext())
                    i.remove();
    }
}
