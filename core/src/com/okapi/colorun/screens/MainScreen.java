package com.okapi.colorun.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.okapi.colorun.Assets;
import com.okapi.colorun.ColoRunner;
import com.okapi.colorun.screens.screens.actors.*;
import com.okapi.colorun.screens.screens.actors.AnimationActor;
import com.okapi.colorun.screens.screens.actors.ButtonActor;

public class MainScreen extends Screens {

    private Stage stage;

    private Texture playBtn;

    public MainScreen(Game game) {
        super(game);

        stage = new Stage(new FitViewport(ColoRunner.WIDTH, ColoRunner.HEIGHT));
        Gdx.input.setInputProcessor(stage);

        stage.addActor(new ButtonActor(game, ButtonActor.Button.PLAY,
                (ColoRunner.WIDTH - Assets.playBtn.getWidth())/2,
                (ColoRunner.HEIGHT - Assets.playBtn.getHeight())/2));
        stage.addActor(new AnimationActor(game, Assets.logoAnimation,
                (ColoRunner.WIDTH - 201 )/2, ColoRunner.HEIGHT/2 + 50));
    }

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

}
