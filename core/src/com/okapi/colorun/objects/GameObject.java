package com.okapi.colorun.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class GameObject extends Sprite {

    protected World world;
    protected Body b2body;

    public float getX() {
        return b2body.getPosition().x;
    }

    public float getY() {
        return b2body.getPosition().y;
    }

    public float getWidth(){
        return getTexture().getWidth();
    }

    public float getHeight(){
        return getTexture().getHeight();
    }

    public abstract void define(float x, float y);
    public abstract void dispose();
    public abstract Texture getTexture();

}

