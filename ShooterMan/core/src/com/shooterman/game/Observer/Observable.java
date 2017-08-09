package com.shooterman.game.Observer;

import com.annimon.stream.Stream;
import com.badlogic.gdx.physics.box2d.Fixture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Observable<T> {
    private final List<Observer> observers = new ArrayList<>();
    private boolean FLAG_CHANGED = false;

    protected final void setChanged() {
        FLAG_CHANGED = true;
    }

    @SafeVarargs
    protected final void notifySuscribers(T... UserData) {
        if (FLAG_CHANGED) {
            Stream.of(observers).forEach(observer ->observer.tellEvent(this,UserData));
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
