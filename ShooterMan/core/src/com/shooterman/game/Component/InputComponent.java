package com.shooterman.game.Component;

import com.shooterman.game.Component.Entity.Entity;
import com.shooterman.game.Component.IComponent.IComponent;
import com.shooterman.game.Component.IComponent.IMessage;

import static com.shooterman.game.KotlinBackend.Kotlin.B2d.Controler.Physics.Vars.PPM;

public class InputComponent implements IComponent, IMessage {
    private PhysicComponent physicComponent;
    private Entity myEnity;

    public InputComponent(Entity myEnity) {
        this.myEnity = myEnity;
        this.physicComponent = (PhysicComponent) this.myEnity.getComponent(PhysicComponent.class);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void clearData() {

    }

    @Override
    public <T> void sendMessage(T... data) {
        if (data[0] instanceof float[]) {
            float Vx = ((float[]) data[0])[0];
            float Vy = ((float[]) data[0])[1];
            if (Math.abs(Vx) > 0f || Math.abs(Vy) > 0f)
                physicComponent.setLinearVelocity(Vx, Vy);
        } else throw new IllegalArgumentException();
    }


}
