package com.okapi.colorun.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.okapi.colorun.AssetLoader;
import com.okapi.colorun.ColoRunnerDemo;

public class MainScreen extends Screens {

    private FitViewport fitViewport;
    private Stage stage;

    private Texture playBtn;
    private Animation logoAnimation;

    public MainScreen() {

        super();

        fitViewport = new FitViewport(ColoRunnerDemo.WIDTH, ColoRunnerDemo.HEIGHT);
        stage = new Stage(fitViewport);
        Gdx.input.setInputProcessor(stage);

        playBtn = AssetLoader.playBtn;
        logoAnimation = AssetLoader.logoAnimation;

        MyActor myActor = new MyActor(background, 0, 0);
        stage.addActor(myActor);
        myActor = new MyActor(playBtn, (ColoRunnerDemo.WIDTH - playBtn.getWidth())/2, (ColoRunnerDemo.HEIGHT - playBtn.getHeight())/2, MyActor.ButtonType.PLAY);
        stage.addActor(myActor);
        myActor = new MyActor(logoAnimation,(ColoRunnerDemo.WIDTH - 201 )/2, ColoRunnerDemo.HEIGHT/2 + 50);
        stage.addActor(myActor);
    }
    @Override
    public void handleInput() {

    }
    @Override
    public void update(float dt) {
    }

    @Override
    public void render(float delta) {

        //update(delta);
        stage.act(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void dispose() {

        stage.dispose();
        System.out.println("Main Screen Disposed");
    }

    @Override
    public void resize(int width, int height) {

        fitViewport.update(width, height);
    }

}
