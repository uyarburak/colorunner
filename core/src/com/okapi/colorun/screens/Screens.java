package com.okapi.colorun.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.okapi.colorun.AssetLoader;
import com.okapi.colorun.ColoRunnerDemo;

/**
 * Created by burakuyar on 16.02.2016.
 */
public abstract class Screens implements Screen{

    protected ColoRunnerDemo coloRunnerDemo;
    protected SpriteBatch batch;

    protected Texture background;

    public Screens(){

        this.coloRunnerDemo = ColoRunnerDemo.getGame();
        batch = coloRunnerDemo.getBatch();

        background = AssetLoader.background;
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void dispose();
}
