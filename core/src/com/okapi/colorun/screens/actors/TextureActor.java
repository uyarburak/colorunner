package com.okapi.colorun.screens.actors;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class TextureActor extends AbstractActor {

    public Texture texture;

    public TextureActor(Game game, Texture texture, float x, float y) {
        super(game, x, y);
        this.texture = texture;

        setBounds(x, y, texture.getWidth(), texture.getHeight());
        setTouchable(Touchable.disabled);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        batch.draw(texture, x, y);
    }

}
