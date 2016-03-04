package com.okapi.colorun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

    public static final Texture PLAY_BUTTON;
    public static final Texture SCORES_BUTTON;
    public static final Texture RUNWAY_BLUE;
    public static final Texture RUNWAY_RED;
    public static final Texture RUNWAY_GREEN;
    public static final Texture RUNWAY_YELLOW;
    public static final Texture SCORE_TABLE;
    public static final Texture GAME_OVER;
    public static final Texture NEW;
    static {
        PLAY_BUTTON = new Texture("playbtn.png");
        SCORES_BUTTON = new Texture("rankbtn.png");
        RUNWAY_RED = new Texture("red_runway.png");
        RUNWAY_GREEN = new Texture("green_runway.png");
        RUNWAY_BLUE = new Texture("blue_runway.png");
        RUNWAY_YELLOW = new Texture("yellow_runway.png");
        SCORE_TABLE = new Texture("scoretable.png");
        GAME_OVER = new Texture("gameover.png");
        NEW = new Texture("new.png");
    }

    public static final Animation LOGO;
    public static final Animation RUN;
    public static final Animation JUMP;
    public static final Animation FALL;
    static {
        Texture textureRunning = new Texture("run_animation_64.png");
        RUN = new Animation(0.06f,
                new TextureRegion(textureRunning, 0, 0, 45, 60),
                new TextureRegion(textureRunning, 45, 0, 45, 60),
                new TextureRegion(textureRunning, 90, 0, 45, 60),
                new TextureRegion(textureRunning, 135, 0, 45, 60),
                new TextureRegion(textureRunning, 180, 0, 45, 60));

        Texture textureJumping = new Texture("jump_yeni.png");
        JUMP = new Animation(0.09f,
                new TextureRegion(textureJumping, 0, 60, 60, 61),
                new TextureRegion(textureJumping, 60, 60, 60, 61),
                new TextureRegion(textureJumping, 120, 60, 60, 61),
                new TextureRegion(textureJumping, 180, 60, 60, 61),
                new TextureRegion(textureJumping, 0, 0, 60, 61),
                new TextureRegion(textureJumping, 60, 0, 60, 61),
                new TextureRegion(textureJumping, 120, 0, 60, 61),
                new TextureRegion(textureJumping, 180, 0, 60, 61));

        FALL = new Animation(0.3f,
                new TextureRegion(textureRunning, 0, 0, 45, 60),
                new TextureRegion(textureRunning, 45, 0, 45, 60),
                new TextureRegion(textureRunning, 90, 0, 45, 60),
                new TextureRegion(textureRunning, 135, 0, 45, 60),
                new TextureRegion(textureRunning, 180, 0, 45, 60));

        Texture textureLogo = new Texture("logo_buyuk.png");
        LOGO = new Animation(0.3f,
                new TextureRegion(textureLogo, 0, 0, 201, 50),
                new TextureRegion(textureLogo, 201, 0, 201, 50));
    }

    public static final Sound JUMP_SOUND;
    static {
        JUMP_SOUND = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public static final BitmapFont FONT;
    static {
        FONT = new BitmapFont(Gdx.files.internal("fonts/whitetext.fnt"));
        FONT.getData().setScale(.25f, .25f);
    }

    public static void setHighScore(int score) {
        Gdx.app.getPreferences("ColoRunner").putInteger("highScore", score);
        Gdx.app.getPreferences("ColoRunner").flush();
    }

    public static int getHighScore() {
        return Gdx.app.getPreferences("ColoRunner").getInteger("highScore");
    }

    public static void dispose() {
        PLAY_BUTTON.dispose();
        SCORES_BUTTON.dispose();
        RUNWAY_BLUE.dispose();
        RUNWAY_RED.dispose();
        RUNWAY_GREEN.dispose();
        RUNWAY_YELLOW.dispose();
        SCORE_TABLE.dispose();
        GAME_OVER.dispose();
        NEW.dispose();
        JUMP_SOUND.dispose();
        FONT.dispose();
    }
}
