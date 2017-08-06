package com.shooterman.game.Component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private HashMap<String, Animation<TextureRegion>> animations = new HashMap<>();
    private Entity myEnity;
    private RenderComponent renderComponent;
    private String animationActive;


    public AnimationComponent(Entity entity) {
        myEnity = entity;
        renderComponent = (RenderComponent) myEnity.getComponent(RenderComponent.class);
    }

    /**
     * This method is important for TextureRegion arrays e.g. for easily splitting up Textures
     * The correct implementation will follow later
     */
/*
    public AnimationComponent addAnimation(String id, float duration, Animation.PlayMode playMode, TextureRegion... textures) {
        Animation<Texture> animationtoAdd = new Animation<>(duration, textures);
        animationtoAdd.setPlayMode(playMode);

        animations.put(id, new Animation<TextureRegion>(duration,textures,playMode));
        animationActive = id;
        animationTimes.put(id, 0f);
        return this;
    }
    */
    public AnimationComponent addAnimation(String id, float duration, Animation.PlayMode playMode, Array<TextureAtlas.AtlasRegion> regions) {

        Array<Texture> textures = new Array<>();
        /*extracting the regions out of the Array<TextureAtlas.AtlasRegion>*/
        for (TextureAtlas.AtlasRegion atlasRegion : regions)
            textures.add(atlasRegion.getTexture());
        /*Setting the playmode directly, because Animation only supports the playmode in combination with an Array(class based) of the generic type paramter we specified above*/
        animations.put(id, new Animation<>(duration, regions, playMode));
        animationActive = id;
        animationTimes.put(id, 0f);
        return this;
    }

    public void setAnimationActive(String active) {
        this.animationActive = active;
    }

    @Override
    public void update(float delta) {
        animationTimes.put(animationActive, (animationTimes.get(animationActive)) + delta);
        render();

    }

    /* At the Moment the AniamtionComponent only supports LOOP Animations,Animations that only occur once are not supported yet*/
    private void render() {
        TextureRegion t = animations.get(animationActive).getKeyFrame(animationTimes.get(animationActive), true);
        renderComponent.setCurrentFrame(t);
    }

    @Override
    public void clearData() {

    }
}
