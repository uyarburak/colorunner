package com.okapi.colorun.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.okapi.colorun.AssetLoader;
import com.okapi.colorun.ColoRunnerDemo;

/**
 * Created by burakuyar on 19.02.2016.
 */
public class MyActor extends Actor {

    private enum Type{TEXTURE, ANIMATION};
    private Type type;

    public enum ButtonType{PLAY, MAIN};
    private ButtonType buttonType;

    private Texture texture;
    private Animation animation;

    private float actorX, actorY, stateTime;
    private boolean started = false;

    public MyActor(Texture texture, float x, float y, ButtonType buttonType){

        this(texture, x, y);

        this.buttonType = buttonType;
        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                ((MyActor) event.getTarget()).started = true;
                return true;
            }
        });
        setTouchable(Touchable.enabled);
    }

    public MyActor(Texture texture, float x, float y){

        this.texture = texture;
        this.actorX = x;
        this.actorY = y;
        type = Type.TEXTURE;

        setBounds(x, y, texture.getWidth(), texture.getHeight());
        setTouchable(Touchable.disabled);
    }
    public MyActor(Animation animation, float x, float y){

        this.animation = animation;
        this.actorX = x;
        this.actorY = y;
        type = Type.ANIMATION;
        stateTime = 0f;

        setBounds(x, y, animation.getKeyFrame(stateTime).getTexture().getWidth(), animation.getKeyFrame(stateTime).getTexture().getHeight());
        setTouchable(Touchable.disabled);
    }


    @Override
    public void draw(Batch batch, float alpha){

        if(type == Type.TEXTURE){
            if(texture != AssetLoader.background) {
                batch.draw(texture, actorX, actorY);
            }
            else {
                batch.draw(texture, actorX, actorY, ColoRunnerDemo.WIDTH, ColoRunnerDemo.HEIGHT);
            }
        }else {
            batch.draw(animation.getKeyFrame(stateTime), actorX, actorY);
        }
    }

    @Override
    public void act(float delta){

        if(type == Type.ANIMATION){
            stateTime += delta;
            if(animation.getAnimationDuration() < stateTime) {
                stateTime = 0;
            }

        }
        if(started){
            ColoRunnerDemo cd = ColoRunnerDemo.getGame();
            if(buttonType == ButtonType.MAIN){
                cd.setScreen(new MainScreen());
            }
            else{
                cd.setScreen(new PlayScreen());
            }
        }
    }
}
