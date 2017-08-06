package com.shooterman.game.Component.IComponent;

/**
 * Created by jhard on 04.08.2017.
 */

public interface IMessage {
    <T>void sendMessage(T data);
}
