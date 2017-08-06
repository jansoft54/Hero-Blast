package com.shooterman.game.Component;
import com.shooterman.game.Component.Entity.Entity;
import com.shooterman.game.Component.IComponent.IComponent;
import com.shooterman.game.Component.IComponent.IMessage;

public class InputComponent implements IComponent,IMessage {
    private PhysicComponent physicComponent;
    private Entity myEnity;

    public InputComponent(Entity myEnity){
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
    public <T> void sendMessage(T data) {
        if(data instanceof float[])
        {
            float Vx = ((float[]) data)[0];
            float Vy = ((float[]) data)[1];
            physicComponent.setPosition(Vx,Vy);
        }
        else throw new IllegalArgumentException();
    }


}
