package com.okapi.colorun.screens.actors;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.okapi.colorun.Assets;
import com.okapi.colorun.screens.MainScreen;
import com.okapi.colorun.screens.PlayScreen;

public class ButtonActor extends AbstractActor {

    public enum Button {
        PLAY, HIGHSCORE
    }

    private Button button;
    private Texture texture;
    private boolean pressed;

    public ButtonActor(Game game, Button button, float x, float y) {
        super(game, x, y);
        this.button = button;

        switch(button) {
            case PLAY:
                texture = Assets.PLAY_BUTTON;
                break;
            case HIGHSCORE:
                texture = Assets.SCORES_BUTTON;
                break;
            // ...
        }

        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ((ButtonActor)event.getTarget()).pressed = true;
                return true;
            }
        });

        setBounds(x, y, texture.getWidth(), texture.getHeight());
        setTouchable(Touchable.enabled);
    }

    @Override
    public void act(float delta) {
        if (pressed) {
            if (button == Button.HIGHSCORE)
                game.setScreen(new MainScreen(game));
            else if (button == Button.PLAY)
                game.setScreen(new PlayScreen(game));
            // ...
        }
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(texture, x, y);
    }


}
