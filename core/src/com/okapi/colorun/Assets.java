package com.okapi.colorun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

    public static Texture playBtn, rankBtn, runwayBlue, runwayRed, runwayGreen,
            runwayYellow, scoreTable, gameOver, newIcon;
    public static Animation logoAnimation, runningAnimation, jumpingAnimation, fallingAnimation;
    public static Sound soundJump;
    public static BitmapFont font, shadow;


    public static void load() {
        //Load textures
        playBtn = new Texture("playbtn.png");
        rankBtn = new Texture("rankbtn.png");
        runwayRed = new Texture("red_runway.png");
        runwayGreen = new Texture("green_runway.png");
        runwayBlue = new Texture("blue_runway.png");
        runwayYellow = new Texture("yellow_runway.png");
        scoreTable = new Texture("scoretable.png");
        gameOver = new Texture("gameover.png");
        newIcon = new Texture("new.png");

        // Load animations and what they need to be loaded
        Texture textureRunning = new Texture("run_animation_64.png");
        runningAnimation = new Animation(0.06f,
                new TextureRegion(textureRunning, 0, 0, 45, 60),
                new TextureRegion(textureRunning, 45, 0, 45, 60),
                new TextureRegion(textureRunning, 90, 0, 45, 60),
                new TextureRegion(textureRunning, 135, 0, 45, 60),
                new TextureRegion(textureRunning, 180, 0, 45, 60));

        Texture textureJumping = new Texture("jump_yeni.png");
        jumpingAnimation = new Animation(0.09f,
                new TextureRegion(textureJumping, 0, 60, 60, 61),
                new TextureRegion(textureJumping, 60, 60, 60, 61),
                new TextureRegion(textureJumping, 120, 60, 60, 61),
                new TextureRegion(textureJumping, 180, 60, 60, 61),
                new TextureRegion(textureJumping, 0, 0, 60, 61),
                new TextureRegion(textureJumping, 60, 0, 60, 61),
                new TextureRegion(textureJumping, 120, 0, 60, 61),
                new TextureRegion(textureJumping, 180, 0, 60, 61));

        fallingAnimation = new Animation(0.3f,
                new TextureRegion(textureRunning, 0, 0, 45, 60),
                new TextureRegion(textureRunning, 45, 0, 45, 60),
                new TextureRegion(textureRunning, 90, 0, 45, 60),
                new TextureRegion(textureRunning, 135, 0, 45, 60),
                new TextureRegion(textureRunning, 180, 0, 45, 60));

        Texture textureLogo = new Texture("logo_buyuk.png");
        logoAnimation = new Animation(0.3f,
                new TextureRegion(textureLogo, 0, 0, 201, 50),
                new TextureRegion(textureLogo, 201, 0, 201, 50));

        // Load sounds
        soundJump = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));

        // Load bitmap fonts
        font = new BitmapFont(Gdx.files.internal("fonts/whitetext.fnt"));
        font.getData().setScale(.25f, .25f);

        shadow = new BitmapFont(Gdx.files.internal("fonts/shadow.fnt"));
        shadow.getData().setScale(.25f, -.25f);
    }

    public static void setHighScore(int score) {
        Gdx.app.getPreferences("ColoRunner").putInteger("highScore", score);
        Gdx.app.getPreferences("ColoRunner").flush();
    }

    public static int getHighScore() {
        return Gdx.app.getPreferences("ColoRunner").getInteger("highScore");
    }

    public static void dispose() {
        // Dispose textures
        playBtn.dispose();
        rankBtn.dispose();
        runwayBlue.dispose();
        runwayRed.dispose();
        runwayGreen.dispose();
        runwayYellow.dispose();
        scoreTable.dispose();
        gameOver.dispose();
        newIcon.dispose();

        // Dispose sounds
        soundJump.dispose();

        // Dispose bitmap fonts
        font.dispose();
        shadow.dispose();
    }
}
