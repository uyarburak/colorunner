package com.okapi.colorun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by burakuyar on 18.02.2016.
 */
public class AssetLoader {

    public static Texture background, playBtn, rankBtn, runwayBlue, runwayRed, runwayGreen, runwayYellow, scoreTable, gameOver, newIcon;
    public static Animation logoAnimation, runningAnimation, jumpingAnimation, fallingAnimation;
    public static Sound soundDead, soundJump;
    public static BitmapFont font, shadow;

    //These does not need to be accessed
    private static Preferences prefs;
    private static Texture textureLogo, textureRunning, textureJumping;

    public static void load(){

        //Load textures
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
        rankBtn = new Texture("rankbtn.png");
        runwayRed = new Texture("red_runway.png");
        runwayGreen = new Texture("green_runway.png");
        runwayBlue = new Texture("blue_runway.png");
        runwayYellow = new Texture("yellow_runway.png");
        scoreTable = new Texture("scoretable.png");
        gameOver = new Texture("gameover.png");
        newIcon = new Texture("new.png");

        //Load animations and what they need to be loaded

        {

            textureRunning = new Texture("run_animation_64.png");
            TextureRegion[] regions = {
                    new TextureRegion(textureRunning, 0, 0, 45, 60),
                    new TextureRegion(textureRunning, 45, 0, 45, 60),
                    new TextureRegion(textureRunning, 90, 0, 45, 60),
                    new TextureRegion(textureRunning, 135, 0, 45, 60),
                    new TextureRegion(textureRunning, 180, 0, 45, 60)
            };

            runningAnimation = new Animation(0.06f, regions);
        }
        /*
        {

            textureJumping = new Texture("jump_animation_64.png");
            TextureRegion[] regions = {
                    new TextureRegion(textureJumping, 0, 0, 50, 61),
                    new TextureRegion(textureJumping, 50, 0, 50, 61),
                    new TextureRegion(textureJumping, 100, 0, 50, 61),
                    new TextureRegion(textureJumping, 150, 0, 50, 61),
                    new TextureRegion(textureJumping, 200, 0, 50, 61)
            };

            jumpingAnimation = new Animation(0.1f, regions);
        }
         */
        {

            textureJumping = new Texture("jump_yeni.png");
            TextureRegion[] regions = {
                    new TextureRegion(textureJumping, 0, 60, 60, 61),
                    new TextureRegion(textureJumping, 60, 60, 60, 61),
                    new TextureRegion(textureJumping, 120, 60, 60, 61),
                    new TextureRegion(textureJumping, 180, 60, 60, 61),
                    new TextureRegion(textureJumping, 0, 0, 60, 61),
                    new TextureRegion(textureJumping, 60, 0, 60, 61),
                    new TextureRegion(textureJumping, 120, 0, 60, 61),
                    new TextureRegion(textureJumping, 180, 0, 60, 61)
            };

            jumpingAnimation = new Animation(0.09f, regions);
        }

        {
            TextureRegion[] regions = {
                    new TextureRegion(textureRunning, 0, 0, 45, 60),
                    new TextureRegion(textureRunning, 45, 0, 45, 60),
                    new TextureRegion(textureRunning, 90, 0, 45, 60),
                    new TextureRegion(textureRunning, 135, 0, 45, 60),
                    new TextureRegion(textureRunning, 180, 0, 45, 60)
            };

            fallingAnimation = new Animation(0.3f, regions);
        }

        {

            textureLogo = new Texture("logo_buyuk.png");
            TextureRegion[] regions = {
                    new TextureRegion(textureLogo, 0, 0, 201, 50),
                    new TextureRegion(textureLogo, 201, 0, 201, 50)
            };

            logoAnimation = new Animation(0.3f, regions);
        }

        //Load sounds
        soundJump = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));

        //Load bitmap fonts
        font = new BitmapFont(Gdx.files.internal("fonts/whitetext.fnt"));
        font.getData().setScale(.25f, .25f);

        shadow = new BitmapFont(Gdx.files.internal("fonts/shadow.fnt"));
        shadow.getData().setScale(.25f, -.25f);

        //Load preferences
        prefs = Gdx.app.getPreferences("ColoRunner");

        //No need to do this according to my tests, but i put it in case something goes wrong in some devices
        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }

    }

    public static void setHighScore(int score) {

        prefs.putInteger("highScore", score);
        prefs.flush();
    }

    public static int getHighScore() {

        return prefs.getInteger("highScore");
    }

    public static void dispose(){

        //Dispose textures
        background.dispose();
        playBtn.dispose();
        rankBtn.dispose();
        runwayBlue.dispose();
        runwayRed.dispose();
        runwayGreen.dispose();
        runwayYellow.dispose();
        scoreTable.dispose();
        gameOver.dispose();
        newIcon.dispose();
        textureRunning.dispose();
        textureJumping.dispose();
        textureLogo.dispose();

        //Dispose sounds
        soundJump.dispose();

        //Dispose bitmap fonts
        font.dispose();
        shadow.dispose();
    }
}
