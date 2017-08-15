package com.shooterman.game.Observer;

@Deprecated
public interface Observer {

      <T> void tellEvent(Observable ob,T...d);
}
