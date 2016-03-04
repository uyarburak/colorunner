package com.okapi.colorun.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.okapi.colorun.Assets;
import com.okapi.colorun.ColoRunner;
import com.okapi.colorun.screens.screens.actors.*;
import static com.okapi.colorun.screens.screens.actors.ButtonActor.Button.*;

public class GameOverScreen extends Screens {

    private Stage stage;

    private Texture playBtn, gameOver, scoreTable, rankBtn, newIcon;

    public GameOverScreen(Game game, int score, boolean record) {
        super(game);
        stage = new Stage(new FitViewport(ColoRunner.WIDTH, ColoRunner.HEIGHT));
        Gdx.input.setInputProcessor(stage);

        playBtn = Assets.playBtn;
        rankBtn = Assets.rankBtn;
        gameOver = Assets.gameOver;
        scoreTable = Assets.scoreTable;
        newIcon = Assets.newIcon;

        stage.addActor(new ButtonActor(game, ButtonActor.Button.PLAY,
                ColoRunner.WIDTH / 2 - playBtn.getWidth() - 5,
                ColoRunner.HEIGHT / 2 - scoreTable.getHeight()));

        stage.addActor(new ButtonActor(game, ButtonActor.Button.HIGHSCORE,
                ColoRunner.WIDTH / 2 + 5,
                ColoRunner.HEIGHT / 2 - scoreTable.getHeight()));

        stage.addActor(new TextureActor(game, Assets.gameOver,
                (ColoRunner.WIDTH - gameOver.getWidth()) / 2,
                (ColoRunner.HEIGHT + scoreTable.getHeight() + gameOver.getHeight() + 10) / 2));

        stage.addActor(new TextureActor(game, Assets.scoreTable,
                (ColoRunner.WIDTH - scoreTable.getWidth()) / 2,
                (ColoRunner.HEIGHT - scoreTable.getHeight() + 30) / 2));


        BitmapFont font = Assets.font;
        font.getData().setScale(.3f, .3f);

        Label scoreLabel = new Label(String.format("%3d", score), new Label.LabelStyle(font, Color.WHITE));
        scoreLabel.setPosition((ColoRunner.WIDTH + playBtn.getWidth() + 10) / 2, (ColoRunner.HEIGHT + 30) / 2);
        stage.addActor(scoreLabel);

        Label hiscoreLabel = new Label(String.format("%3d", Assets.getHighScore()), new Label.LabelStyle(font, Color.RED));
        hiscoreLabel.setPosition((ColoRunner.WIDTH + playBtn.getWidth() + 10) / 2, (ColoRunner.HEIGHT - playBtn.getHeight()) / 2);
        stage.addActor(hiscoreLabel);

        if (record)
            stage.addActor(new TextureActor(game, Assets.newIcon,
                    (ColoRunner.WIDTH + 48) / 2,
                    ColoRunner.HEIGHT / 2));
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
}
