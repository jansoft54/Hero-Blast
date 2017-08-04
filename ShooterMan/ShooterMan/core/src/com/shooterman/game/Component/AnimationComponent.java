package com.shooterman.game.Component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.shooterman.game.Component.IComponent.IComponent;
import com.shooterman.game.Component.Entity.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;


public final class AnimationComponent implements IComponent {
    private HashMap<String, Float> animationTimes = new HashMap<>();
    private HashMap<String, Animation<Texture>> animations = new HashMap<>();
    private Entity myEnity;
    private RenderComponent renderComponent;
    private String animationActive;


    public AnimationComponent(Entity entity) {
        myEnity = entity;
        renderComponent = (RenderComponent) myEnity.getComponent(RenderComponent.class);
    }

    public void addAnimation(String id, float duration, Animation.PlayMode playMode, Texture... textures) {
        Animation animationtoAdd = new Animation<>(duration, textures);
        animationtoAdd.setPlayMode(playMode);

        animations.put(id, animationtoAdd);
        animationActive = id;
        animationTimes.put(id, 0f);
    }

    public void addAnimation(String id, float duration, Animation.PlayMode playMode, Array<TextureAtlas.AtlasRegion> regions) {

        Array<Texture> textures = new Array<>();
        /*extracting the regions out of the Array<TextureAtlas.AtlasRegion>*/
        for (TextureAtlas.AtlasRegion atlasRegion : regions)
            textures.add(atlasRegion.getTexture());
        /*Setting the playmode directly, because Animation only supports the playmode
        in combination with an Array(class based) of the generic type paramter we specified above*/
        animations.put(id, new Animation<>(duration, textures,playMode));
        animationActive = id;
        animationTimes.put(id, 0f);
    }

    public void setAnimationActive(String active) {
        this.animationActive = active;
    }

    @Override
    public void update(float delta) {
        for (Iterator iterator = animationTimes.entrySet().iterator(); iterator.hasNext(); )
            animationTimes.put(animationActive, (animationTimes.get(animationActive)) + delta);
        render();

    }

    private void render() {
        renderComponent.draw(animations.get(animationActive).getKeyFrame(animationTimes.get(animationActive)));

    }

    @Override
    public void clearData() {

    }
}
