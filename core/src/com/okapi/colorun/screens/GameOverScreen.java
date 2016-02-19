package com.okapi.colorun.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.okapi.colorun.AssetLoader;
import com.okapi.colorun.ColoRunnerDemo;

/**
 * Created by burakuyar on 18.02.2016.
 */
public class GameOverScreen extends Screens {

    private Stage stage;

    private Texture playBtn, gameOver, scoreTable, rankBtn, newIcon;
    private BitmapFont font;
    private Label scoreLabel, hiscoreLabel;

    public GameOverScreen(int score, boolean record){
        super();
        stage = new Stage(new FitViewport(ColoRunnerDemo.WIDTH, ColoRunnerDemo.HEIGHT));
        Gdx.input.setInputProcessor(stage);

        playBtn = AssetLoader.playBtn;
        rankBtn = AssetLoader.rankBtn;
        gameOver = AssetLoader.gameOver;
        scoreTable = AssetLoader.scoreTable;
        newIcon = AssetLoader.newIcon;

        font = AssetLoader.font;
        font.getData().setScale(.3f, .3f);

        MyActor myActor = new MyActor(background, 0, 0);
        stage.addActor(myActor);
        myActor = new MyActor(playBtn, ColoRunnerDemo.WIDTH/2 - playBtn.getWidth() - 5, ColoRunnerDemo.HEIGHT/2 - scoreTable.getHeight(), MyActor.ButtonType.PLAY);
        stage.addActor(myActor);
        myActor = new MyActor(rankBtn, ColoRunnerDemo.WIDTH/2 + 5, ColoRunnerDemo.HEIGHT/2 - scoreTable.getHeight(), MyActor.ButtonType.MAIN);
        stage.addActor(myActor);
        myActor = new MyActor(gameOver, (ColoRunnerDemo.WIDTH - gameOver.getWidth())/2,  (ColoRunnerDemo.HEIGHT + scoreTable.getHeight() + gameOver.getHeight() +10 )/2);
        stage.addActor(myActor);
        myActor = new MyActor(scoreTable, (ColoRunnerDemo.WIDTH - scoreTable.getWidth())/2,  (ColoRunnerDemo.HEIGHT - scoreTable.getHeight() + 30)/2);
        stage.addActor(myActor);

        scoreLabel = new Label(String.format("%3d", score), new Label.LabelStyle(font, Color.WHITE));
        scoreLabel.setPosition((ColoRunnerDemo.WIDTH + playBtn.getWidth()+10)/2, (ColoRunnerDemo.HEIGHT+30)/2);
        stage.addActor(scoreLabel);

        hiscoreLabel = new Label(String.format("%3d", AssetLoader.getHighScore()), new Label.LabelStyle(font, Color.RED));
        hiscoreLabel.setPosition((ColoRunnerDemo.WIDTH + playBtn.getWidth()+10)/2, (ColoRunnerDemo.HEIGHT-playBtn.getHeight())/2);
        stage.addActor(hiscoreLabel);


        if(record){
            myActor =  new MyActor(newIcon, (ColoRunnerDemo.WIDTH +48)/2,  ColoRunnerDemo.HEIGHT/2);
            stage.addActor(myActor);
        }
    }

    @Override
    public void handleInput() {
    }
    @Override
    public void update(float dt) {
        handleInput();
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
    }
}
