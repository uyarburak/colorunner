package com.okapi.colorun.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.okapi.colorun.ColoRunner;
import com.okapi.colorun.screens.PlayScreen;

import java.util.Random;

import static com.okapi.colorun.Assets.*;

public class Runway extends GameObject {

    protected static Random rand = new Random();

    public static final int TUBE_WIDTH = RUNWAY_RED.getWidth();
    private static final int FLUCTUATION = 35;
    private static final float LOWEST_OPENING = -120;

    private Texture texture;
    private Fixture fixture;

    public Runway(float x) {
        define(x, LOWEST_OPENING);
        rand();
        setBounds(getX(), getY(), getWidth() / ColoRunner.PPM, getHeight() / ColoRunner.PPM);
    }

    public Texture getTexture() {
        return texture;
    }

    public void reposition(float x) {
        rand();
        short bit = fixture.getFilterData().categoryBits;
        define(x, rand.nextInt(FLUCTUATION) + LOWEST_OPENING);

        Filter filter = new Filter();
        filter.categoryBits = bit;
        fixture.setFilterData(filter);

        setPosition(getX(), getY());
    }

    public void rand() {
        Filter filter = new Filter();
        switch (rand.nextInt(4)) {
            case 0:
                texture = RUNWAY_RED;
                filter.categoryBits = PlayScreen.RED_BIT;
                break;
            case 1:
                texture = RUNWAY_GREEN;
                filter.categoryBits = PlayScreen.GREEN_BIT;
                break;
            case 2:
                texture = RUNWAY_BLUE;
                filter.categoryBits = PlayScreen.BLUE_BIT;
                break;
            case 3:
                texture = RUNWAY_YELLOW;
                filter.categoryBits = PlayScreen.YELLOW_BIT;
                break;
        }
        fixture.setFilterData(filter);
        setTexture(texture);
        setPosition(getX(), getY());
    }

    public void dispose() {
    }

    public void define(float x, float y) {
        this.world = PlayScreen.getWorld();

        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(x / ColoRunner.PPM, y / ColoRunner.PPM);
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        EdgeShape shape = new EdgeShape();
        shape.set(new Vector2(0, RUNWAY_RED.getHeight() / ColoRunner.PPM),
                new Vector2(RUNWAY_RED.getWidth() / ColoRunner.PPM,
                        RUNWAY_RED.getHeight() / ColoRunner.PPM));
        fdef.shape = shape;
        fdef.friction = 0f;
        fixture = b2body.createFixture(fdef);
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(texture, getX(), getY(), getWidth() / ColoRunner.PPM,
                getHeight() / ColoRunner.PPM);
    }
}
