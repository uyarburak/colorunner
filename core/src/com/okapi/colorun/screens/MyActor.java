package com.okapi.colorun.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class MyActor extends Actor {

    private Type type;
    private enum Type {TEXTURE, ANIMATION}

    private ButtonType buttonType;
    public enum ButtonType {PLAY, MAIN}

    private Texture texture;
    private Animation animation;

    private float actorX, actorY, stateTime;
    private boolean started;

    private Game game;

    public MyActor(Game game, Texture texture, float x, float y) {
        this.game = game;
        this.texture = texture;
        this.actorX = x;
        this.actorY = y;
        type = Type.TEXTURE;

        setBounds(x, y, texture.getWidth(), texture.getHeight());
        setTouchable(Touchable.disabled);
    }

    public MyActor(Game game, Texture texture, float x, float y, ButtonType buttonType) {
        this(game, texture, x, y);

        this.buttonType = buttonType;
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ((MyActor) event.getTarget()).started = true;
                return true;
            }
        });
        setTouchable(Touchable.enabled);
    }

    public MyActor(Game game, Animation animation, float x, float y) {
        this.game = game;
        this.animation = animation;
        this.actorX = x;
        this.actorY = y;
        type = Type.ANIMATION;
        stateTime = 0f;

        //setBounds(x, y, animation.getKeyFrame(stateTime).getTexture().getWidth(),
        // animation.getKeyFrame(stateTime).getTexture().getHeight());
        //setTouchable(Touchable.disabled);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        if (type == Type.TEXTURE)
                batch.draw(texture, actorX, actorY);
        else
            batch.draw(animation.getKeyFrame(stateTime), actorX, actorY);
    }

    @Override
    public void act(float delta) {
        if (type == Type.ANIMATION) {
            stateTime += delta;
            if (animation.getAnimationDuration() < stateTime)
                stateTime = 0;
        }

        if (started) {
            if (buttonType == ButtonType.MAIN)
                game.setScreen(new MainScreen(game));
            else
                game.setScreen(new PlayScreen(game));
        }
    }
}
