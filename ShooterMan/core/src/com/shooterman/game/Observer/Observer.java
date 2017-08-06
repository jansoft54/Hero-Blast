package com.shooterman.game.Observer;

/**
 * Created by jhard on 31.07.2017.
 */

public interface Observer {

      <T> void tellEvent(Observable ob,T...d);
}
