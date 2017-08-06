package com.shooterman.game.Things;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.shooterman.game.KotlinBackend.Kotlin.Assets.AssetsManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.shooterman.game.MainClass.PlayScreen;


public class TouchpadInput {
    Stage stage;
    Skin skin;
    Touchpad touchpad;
    Touchpad.TouchpadStyle touchpadStyle;
    PlayScreen ps;


    public TouchpadInput(Stage stage, PlayScreen ps) {
        this.stage = stage;
        this.ps = ps;
        skin = new Skin();
        touchpadStyle = new Touchpad.TouchpadStyle();
        Texture background = AssetsManager.Manager.getManager().get("environment/togglebackground.png");
        Texture pad = AssetsManager.Manager.getManager().get("environment/toggle.png");

        background.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        pad.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);


        skin.add("background", background);
        skin.add("pad", pad);

        touchpadStyle.background = skin.getDrawable("background");
        touchpadStyle.knob = skin.getDrawable("pad");
        touchpad = new Touchpad(15, touchpadStyle);


        touchpad.setBounds(30, 20, 150, 150);

        stage.addActor(touchpad);
        Gdx.input.setInputProcessor(stage);


    }


    public void draw() {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public Touchpad getTouchpad() {
        return touchpad;
    }

}
