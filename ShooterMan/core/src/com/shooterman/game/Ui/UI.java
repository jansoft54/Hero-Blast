package com.shooterman.game.Ui;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.shooterman.game.Screens.PlayScreen;

public class UI {
    private static Button pause;
    private TextureAtlas Buttonatlas;
    private Skin skin;
    private final PlayScreen ps;


    public UI(final PlayScreen ps)
    {
        this.ps = ps;
        skin = new Skin();
        addPauseButtonListener();
    }
    public void addActor(Stage stage)
    {
        stage.addActor(pause);
    }
    private void addPauseButtonListener()
    {
        Button.ButtonStyle pauseStyle = new Button.ButtonStyle();
        Buttonatlas = new TextureAtlas("Atlases/buttonatlas/pauseButton.atlas");
        skin.addRegions(Buttonatlas);

        pauseStyle.up = skin.getDrawable("2");
        pauseStyle.down = skin.getDrawable("2");

        pause = new Button(pauseStyle);
        pause.setBounds(30, 520, pause.getWidth(), pause.getHeight());

        pause.addListener(new ClickListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                pause.setBounds(25, 505, pause.getWidth(), pause.getHeight());
                if (ps.isactive) {
                 //   ps.music.pause();
                    ps.isactive = false;

                } else {
                 //   ps.music.play();
                    ps.isactive = true;
                }
                return super.touchDown(event, x, y, pointer, button);

            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                pause.setBounds(30, 520, pause.getWidth(), pause.getHeight());
                super.touchUp(event, x, y, pointer, button);
            }
        });



    }
    public void dispose(){
        Buttonatlas.dispose();
        skin.dispose();

    }
}
