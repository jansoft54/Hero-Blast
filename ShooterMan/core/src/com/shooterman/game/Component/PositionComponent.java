package com.shooterman.game.Component;

import com.badlogic.gdx.math.Vector2;
import com.shooterman.game.Component.IComponent.IComponent;

/**
 * Created by jhard on 02.08.2017.
 */

public class PositionComponent implements IComponent {
    private Vector2 position;

    public PositionComponent(Vector2 position)
    {
        this.position = position;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void sendMessage() {

    }

    @Override
    public void receiveMessage() {

    }
}
