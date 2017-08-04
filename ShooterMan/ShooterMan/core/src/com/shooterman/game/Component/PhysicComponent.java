package com.shooterman.game.Component;

import com.badlogic.gdx.math.Vector2;
import com.shooterman.game.Component.IComponent.IComponent;

/**
 * Created by jhard on 02.08.2017.
 */

public class PhysicComponent implements IComponent {


    private Vector2 position;

    public PhysicComponent(Vector2 position)
    {
        this.position = position;
    }

    public Vector2 getPosition() {
        return position;
    }
    @Override
    public void update(float delta) {

    }


    @Override
    public void clearData() {

    }
}
