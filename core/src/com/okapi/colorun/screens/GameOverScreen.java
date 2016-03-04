package com.okapi.colorun.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.okapi.colorun.ColoRunner;
import com.okapi.colorun.screens.actors.ButtonActor;
import com.okapi.colorun.screens.actors.TextureActor;

import static com.badlogic.gdx.graphics.Color.RED;
import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.okapi.colorun.Assets.FONT;
import static com.okapi.colorun.Assets.GAME_OVER;
import static com.okapi.colorun.Assets.NEW;
import static com.okapi.colorun.Assets.SCORE_TABLE;
import static com.okapi.colorun.Assets.getHighScore;
import static com.okapi.colorun.screens.Positions.GAME_OVER_POS;
import static com.okapi.colorun.screens.Positions.HIGH_SCORE_LABEL_POS;
import static com.okapi.colorun.screens.Positions.NEW_POS;
import static com.okapi.colorun.screens.Positions.PLAY_POS;
import static com.okapi.colorun.screens.Positions.SCORES_POS;
import static com.okapi.colorun.screens.Positions.SCORE_LABEL_POS;
import static com.okapi.colorun.screens.Positions.SCORE_TABLE_POS;
import static com.okapi.colorun.screens.actors.ButtonActor.Button.HIGHSCORE;
import static com.okapi.colorun.screens.actors.ButtonActor.Button.PLAY;

public class GameOverScreen extends Screens {

    private Stage stage;

    public GameOverScreen(Game game, int score, boolean recordBroken) {
        super(game);

        Gdx.input.setInputProcessor(stage =
                new Stage(new FitViewport(ColoRunner.WIDTH, ColoRunner.HEIGHT)));

        stage.addActor(new ButtonActor(game, PLAY, PLAY_POS.X, PLAY_POS.Y));
        stage.addActor(new ButtonActor(game, HIGHSCORE, SCORES_POS.X, SCORES_POS.Y));
        stage.addActor(new TextureActor(game, GAME_OVER, GAME_OVER_POS.X, GAME_OVER_POS.Y));
        stage.addActor(new TextureActor(game, SCORE_TABLE, SCORE_TABLE_POS.X, SCORE_TABLE_POS.Y));

        if (recordBroken)
            stage.addActor(new TextureActor(game, NEW, NEW_POS.X, NEW_POS.Y));

        CharSequence scoreText = String.format("%3d", score);
        Label scoreLabel = new Label(scoreText, new LabelStyle(FONT, WHITE));
        scoreLabel.setPosition(SCORE_LABEL_POS.X, SCORE_LABEL_POS.Y);
        stage.addActor(scoreLabel);

        CharSequence highScoreText = String.format("%3d", getHighScore());
        Label highScoreLabel = new Label(highScoreText, new LabelStyle(FONT, RED));
        highScoreLabel.setPosition(HIGH_SCORE_LABEL_POS.X, HIGH_SCORE_LABEL_POS.Y);
        stage.addActor(highScoreLabel);
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
