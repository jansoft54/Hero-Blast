package com.shooterman.game.Observer;


public interface Observer {

      <T> void tellEvent(Observable ob,T...d);
}
