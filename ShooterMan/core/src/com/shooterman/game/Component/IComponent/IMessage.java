package com.shooterman.game.Component.IComponent;


public interface IMessage {
    <T>void sendMessage(T... data);
}
