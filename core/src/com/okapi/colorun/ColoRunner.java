package com.okapi.colorun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.okapi.colorun.screens.MainScreen;

public class ColoRunner extends Game {

	public static final int HEIGHT = 360;
	public static final int WIDTH = 640;
	public static final float PPM = 100;

	private SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MainScreen(this));
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
		Assets.dispose();
	}

}
