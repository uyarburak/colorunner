package com.okapi.colorun.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.okapi.colorun.AssetLoader;
import com.okapi.colorun.ColoRunnerDemo;
import com.okapi.colorun.screens.PlayScreen;

import java.util.Random;

/**
 * Created by burakuyar on 15.02.2016.
 */
public class Runway extends GameObject{

    protected static Random rand = new Random();

    public static final int TUBE_WIDTH = AssetLoader.runwayRed.getWidth();
    private static final int FLUCTUATION = 35;
    private static final float LOWEST_OPENING = -120;

    private Texture texture;
    private Fixture fixture;

    public Runway(float x){

        define(x, LOWEST_OPENING);
        rand();
        setBounds(getX(), getY(), getWidth() / ColoRunnerDemo.PPM, getHeight() / ColoRunnerDemo.PPM);
    }

    public Texture getTexture() {

        return texture;
    }

    public void reposition(float x){

        rand();
        short bit = fixture.getFilterData().categoryBits;
        define(x, rand.nextInt(FLUCTUATION) + LOWEST_OPENING);

        Filter filter = new Filter();
        filter.categoryBits = bit;
        fixture.setFilterData(filter);

        setPosition(getX(), getY());
    }
    public void rand(){

        Filter filter = new Filter();
        switch (rand.nextInt(4)){
            case 0:
                texture = AssetLoader.runwayRed;
                filter.categoryBits = PlayScreen.RED_BIT;
                break;
            case 1:
                texture = AssetLoader.runwayGreen;
                filter.categoryBits = PlayScreen.GREEN_BIT;
                break;
            case 2:
                texture = AssetLoader.runwayBlue;
                filter.categoryBits = PlayScreen.BLUE_BIT;
                break;
            case 3:
                texture = AssetLoader.runwayYellow;
                filter.categoryBits = PlayScreen.YELLOW_BIT;
                break;
        }
        fixture.setFilterData(filter);
        setTexture(texture);
        setPosition(getX(), getY());
    }
    public void dispose(){
    }

    public void define(float x, float y){

        this.world = PlayScreen.getWorld();

        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(x / ColoRunnerDemo.PPM, y / ColoRunnerDemo.PPM);
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        EdgeShape shape = new EdgeShape();
        shape.set(new Vector2(0, AssetLoader.runwayRed.getHeight()/ColoRunnerDemo.PPM), new Vector2(AssetLoader.runwayRed.getWidth()/ColoRunnerDemo.PPM, AssetLoader.runwayRed.getHeight()/ColoRunnerDemo.PPM));
        fdef.shape = shape;
        fdef.friction = 0f;
        fixture = b2body.createFixture(fdef);
    }

    @Override
    public void draw(Batch batch) {

        batch.draw(texture, getX(), getY(), getWidth()/ColoRunnerDemo.PPM, getHeight()/ColoRunnerDemo.PPM);
    }
}
