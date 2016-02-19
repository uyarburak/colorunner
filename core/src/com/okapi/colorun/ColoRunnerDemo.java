package com.okapi.colorun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.okapi.colorun.screens.MainScreen;

/**
 * Created by burakuyar on 15.02.2016.
 */
public class ColoRunnerDemo extends Game {

	public static int HEIGHT = 360;
	public static int WIDTH = 640;
	public static final float PPM = 100;
	public static final String TITLE = "ColoRunner";

	private SpriteBatch batch;
	private static ColoRunnerDemo coloRunnerDemo;

	@Override
	public void create () {

		this.coloRunnerDemo = this;

		AssetLoader.load();
		batch = new SpriteBatch();

		Gdx.gl.glClearColor(1, 0, 0, 1);
		setScreen(new MainScreen());
	}

	@Override
	public void render () {

		super.render();
	}

	@Override
	public void dispose() {

		super.dispose();
		batch.dispose();
		AssetLoader.dispose();
	}

	public static ColoRunnerDemo getGame(){

		return coloRunnerDemo;
	}
	public SpriteBatch getBatch(){

		return batch;
	}
}
