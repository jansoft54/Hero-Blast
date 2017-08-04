package com.shooterman.game.Component.IComponent;

/**
 * Created by jhard on 04.08.2017.
 */

public interface IMessage {
    void sendMessage();
    <T>void receiveMessage(T data);

}
