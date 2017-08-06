package com.shooterman.game.Component;
import com.annimon.stream.Stream;
import com.badlogic.gdx.audio.Sound;
import com.shooterman.game.Component.IComponent.IComponent;
import com.shooterman.game.Component.IComponent.IMessage;
import java.util.HashMap;

public class SoundComponent implements IComponent, IMessage {
    private HashMap<Object, Sound> sounds;

    {
        sounds = new HashMap<>();
    }

    SoundComponent addSound(Object id, Sound sound) {
        sounds.put(id, sound);
        return this;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void clearData() {
        Stream.of(sounds).forEach(sound -> sound.getValue().dispose());
    }

    @Override
    public <T> void sendMessage(T data) {
        if (data instanceof String) {
            sounds.get(data).play();
        } else throw new IllegalArgumentException();
    }
}
