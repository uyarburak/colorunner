package com.okapi.colorun.screens.actors;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class AbstractActor extends Actor {

    protected Game game;
    protected float x, y;

    public AbstractActor(Game game, float x, float y) {
        this.game = game;
        this.x = x;
        this.y = y;
    }

}
