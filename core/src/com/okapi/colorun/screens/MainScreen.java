package com.okapi.colorun.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.okapi.colorun.Assets;
import com.okapi.colorun.ColoRunner;
import com.okapi.colorun.screens.actors.AnimationActor;
import com.okapi.colorun.screens.actors.ButtonActor;

public class MainScreen extends Screens {

    private Stage stage;

    private Texture playBtn;

    public MainScreen(Game game) {
        super(game);

        stage = new Stage(new FitViewport(ColoRunner.WIDTH, ColoRunner.HEIGHT));
        Gdx.input.setInputProcessor(stage);

        stage.addActor(new ButtonActor(game, ButtonActor.Button.PLAY,
                (ColoRunner.WIDTH - Assets.PLAY_BUTTON.getWidth())/2,
                (ColoRunner.HEIGHT - Assets.PLAY_BUTTON.getHeight())/2));
        stage.addActor(new AnimationActor(game, Assets.LOGO,
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
