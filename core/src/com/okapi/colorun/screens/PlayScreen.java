package com.okapi.colorun.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.okapi.colorun.AssetLoader;
import com.okapi.colorun.ColoRunnerDemo;
import com.okapi.colorun.ContactHandle;
import com.okapi.colorun.sprites.Runner;
import com.okapi.colorun.sprites.Runway;


public class PlayScreen extends Screens {

    private static final int RUNWAY_SPACING = 75;
    private static final int RUNWAY_COUNT = 5;

    //Box2D Collision Bits
    public static final short NOTHING_BIT = 0;
    public static final short BLACK_BIT = 1;
    public static final short RED_BIT = 2;
    public static final short YELLOW_BIT = 4;
    public static final short GREEN_BIT = 8;
    public static final short BLUE_BIT = 16;
    public static final short RUNNER_BIT = 32;
    public static final short COIN_BIT = 64;


    private OrthographicCamera cam;
    private FitViewport fitViewport;

    private Box2DDebugRenderer b2dr;
    private boolean debug;

    private Hud hud;

    private static World world;
    private Runner runner;
    private Array<Runway> runways;

    public PlayScreen() {

        super();

        cam = new OrthographicCamera();
        cam.setToOrtho(false, ColoRunnerDemo.WIDTH / ColoRunnerDemo.PPM, ColoRunnerDemo.HEIGHT / ColoRunnerDemo.PPM);
        cam.position.set(cam.position.x / ColoRunnerDemo.PPM, 0, 0);
        fitViewport = new FitViewport(ColoRunnerDemo.WIDTH/ColoRunnerDemo.PPM, ColoRunnerDemo.HEIGHT/ColoRunnerDemo.PPM, cam);

        b2dr = new Box2DDebugRenderer();
        debug = false;

        hud = new Hud(batch);

        //create our Box2D world, setting no gravity in X, -10 gravity in Y, and allow bodies to sleep
        world = new World(new Vector2(0, -10f), true);
        world.setContactListener(new ContactHandle());

        runner = new Runner(50, -56);
        runways = new Array<Runway>();
        for(int i = 0; i < RUNWAY_COUNT; i++){
            runways.add(new Runway(i * (RUNWAY_SPACING + Runway.TUBE_WIDTH)+50));
        }
        runways.add(new Runway(50));

    }

    protected void handleInput() {

        if(Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            runner.jump();
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.X)){
            debug = !debug;
        }
    }

    public void update(float dt) {

        handleInput();
        world.step(1 / 60f, 6, 2);

        runner.update(dt);
        cam.position.x = runner.getX() + 80 / ColoRunnerDemo.PPM;

        for(int i = 0; i < RUNWAY_COUNT; i++){
            Runway runway = runways.get(i);
            if(cam.position.x - (cam.viewportWidth / 2) > runway.getX() + runway.getWidth()/ColoRunnerDemo.PPM){
                runway.reposition(runway.getX()*ColoRunnerDemo.PPM  + ((Runway.TUBE_WIDTH + RUNWAY_SPACING) * RUNWAY_COUNT));
            }
        }

        //DIE MOTHERFUCKER
        if(runner.getY() <= cam.position.y - cam.viewportHeight/2) {
            int high = AssetLoader.getHighScore();
            int score = Hud.getScore();
            boolean record = score > high;
            if(record) {
                AssetLoader.setHighScore(score);
            }
            coloRunnerDemo.setScreen(new GameOverScreen(Hud.getScore(), record));
        }
        cam.update();

    }

    @Override
    public void render(float delta) {

        update(delta);

        batch.setProjectionMatrix(cam.combined);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(debug){
            // Debug renderer
            b2dr.render(world, cam.combined);
            return;
        }

        batch.begin();
        batch.draw(background, cam.position.x - cam.viewportWidth / 2, cam.position.y - cam.viewportHeight / 2, cam.viewportWidth, cam.viewportHeight);
        runner.draw(batch);
        for(Runway runway : runways) {
            runway.draw(batch);
        }
        batch.end();

        //Draws hud
        batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void dispose() {

        runner.dispose();
        for(Runway runway : runways)
            runway.dispose();
        hud.dispose();
        System.out.println("Play Screens Disposed");
    }

    public static World getWorld(){

        return world;
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

        fitViewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
