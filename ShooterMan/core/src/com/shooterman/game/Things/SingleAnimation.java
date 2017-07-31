package com.shooterman.game.Things;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.Animation;

public class SingleAnimation {
    private float time;
    private Animation<TextureRegion> animation;
    private TextureAtlas atlas;

    public SingleAnimation(String name,float time) {
        atlas = new TextureAtlas(name);
        animation = new Animation(time, atlas.getRegions());

    }

    public TextureRegion getFrame(float time) {

        this.time = time;
        return  animation.getKeyFrame(time,true);

    }

    public boolean isAnimfinsihed() {

        return animation.isAnimationFinished(time);
    }

}
