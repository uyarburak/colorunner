package com.okapi.colorun.sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.okapi.colorun.AssetLoader;
import com.okapi.colorun.ColoRunnerDemo;
import com.okapi.colorun.screens.Hud;
import com.okapi.colorun.screens.PlayScreen;

/**
 * Created by burakuyar on 15.02.2016.
 */

public class Runner extends GameObject{

    private static final float MOVEMENT = 1.3f;

    private final Animation RUNNING_ANIMATION  = AssetLoader.runningAnimation;
    private final Animation JUMPING_ANIMATION  = AssetLoader.jumpingAnimation;
    private final Animation FALLING_ANIMATION  = AssetLoader.fallingAnimation;
    private Animation animation;

    private boolean isOnTheGround;
    private float stateTime;
    private enum State{RUNNING, JUMPING, FALLING};
    private State currentState;

    public enum Color { NONE, RED, GREEN, BLUE, YELLOW };
    public static Color currentColor;

    private Sound soundJump;

    public Runner(float x, float y){

        animation = RUNNING_ANIMATION;
        isOnTheGround = true;
        stateTime = 0;
        currentState = State.RUNNING;
        currentColor = Color.NONE;
        soundJump = AssetLoader.soundJump;

        define(x, y);
        setBounds(getX(), getY(), getWidth() / ColoRunnerDemo.PPM, getHeight() / ColoRunnerDemo.PPM);
    }

    public void update(float dt) {

        stateTime += dt;
        if(stateTime > animation.getAnimationDuration()){
            stateTime = 0;
        }

        if(b2body.getLinearVelocity().y < 0){
            setCurrentState(State.FALLING);
        }
        else if(currentState == State.FALLING){
            setCurrentState(State.RUNNING);
        }

        b2body.setLinearVelocity(MOVEMENT, b2body.getLinearVelocity().y);
        setPosition(getX() - getWidth() / 2 / ColoRunnerDemo.PPM, getY() - getHeight() / 2 / ColoRunnerDemo.PPM);
        setRegion(animation.getKeyFrame(stateTime));
    }
    public void jump(){

        if(isOnTheGround) {
            soundJump.play(0.5f);
            b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
            setCurrentState(State.JUMPING);
        }
        else{
            changeColor();
        }
    }
    public void beginContact(){

        if(currentColor != Color.NONE){
            Hud.addScore(1);
        }
        isOnTheGround = true;
    }
    public void endContact(){

        isOnTheGround = false;
    }
    public void setCurrentState(State state){

        this.currentState = state;
        switch (state){
            case RUNNING:
                animation = RUNNING_ANIMATION;
                break;
            case JUMPING:
                animation = JUMPING_ANIMATION;
                break;
            case FALLING:
                animation = FALLING_ANIMATION;
                break;
        }
        stateTime = 0;
    }

    //YOU WILL NOT NEED TO CHANGE THE FOLLOWING, I HOPE xD
    @Override
    public void define(float x, float y){

        this.world = PlayScreen.getWorld();

        BodyDef bdef = new BodyDef();
        bdef.position.set(x/ ColoRunnerDemo.PPM, y/ ColoRunnerDemo.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape ();
        fdef.filter.categoryBits = PlayScreen.RUNNER_BIT;
        fdef.filter.maskBits = PlayScreen.BLACK_BIT |
                PlayScreen.COIN_BIT |
                PlayScreen.BLUE_BIT |
                PlayScreen.GREEN_BIT |
                PlayScreen.RED_BIT |
                PlayScreen.YELLOW_BIT;

        shape.setAsBox(getWidth()/ 2/ ColoRunnerDemo.PPM, getHeight() / 2/ ColoRunnerDemo.PPM);
        fdef.shape = shape;
        fdef.friction = 0f;
        b2body.createFixture(fdef).setUserData(this);
    }
    public void dispose(){
    }
    public void changeColor(){

        Filter filter = b2body.getFixtureList().first().getFilterData();
        switch (currentColor){
            case RED:
                currentColor = Color.GREEN;
                filter.maskBits = PlayScreen.BLACK_BIT |
                        PlayScreen.COIN_BIT |
                        PlayScreen.GREEN_BIT;
                break;
            case GREEN:
                currentColor = Color.BLUE;
                filter.maskBits = PlayScreen.BLACK_BIT |
                        PlayScreen.COIN_BIT |
                        PlayScreen.BLUE_BIT;
                break;
            case BLUE:
                currentColor = Color.YELLOW;
                filter.maskBits = PlayScreen.BLACK_BIT |
                        PlayScreen.COIN_BIT |
                        PlayScreen.YELLOW_BIT;
                break;
            case NONE:
            case YELLOW:
                currentColor = Color.RED;
                filter.maskBits = PlayScreen.BLACK_BIT |
                        PlayScreen.COIN_BIT |
                        PlayScreen.RED_BIT;
                break;
        }
        Hud.changeColor(currentColor.name());
        b2body.getFixtureList().first().setFilterData(filter);
    }
    public TextureRegion getFrame() {

        return animation.getKeyFrame(stateTime);
    }
    public Texture getTexture() {

        return getFrame().getTexture();
    }
    @Override
    public float getWidth(){

        return animation.getKeyFrame(stateTime).getRegionWidth();
    }
    @Override
    public float getHeight(){

        return animation.getKeyFrame(stateTime).getRegionHeight();
    }
}