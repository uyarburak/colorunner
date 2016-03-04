package com.okapi.colorun.screens.screens.actors;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;

public class AnimationActor extends AbstractActor {

    private Animation animation;
    private float stateTime;

    public AnimationActor(Game game, Animation animation, float x, float y) {
        super(game, x, y);
        this.animation = animation;
        stateTime = 0f;
    }

    @Override
    public void act(float delta) {
        stateTime += delta;
        if (animation.getAnimationDuration() < stateTime)
            stateTime = 0;
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(animation.getKeyFrame(stateTime), x, y);
    }
}
