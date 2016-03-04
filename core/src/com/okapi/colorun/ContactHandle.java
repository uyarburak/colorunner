package com.okapi.colorun;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.okapi.colorun.objects.Runner;

public class ContactHandle implements ContactListener {

    private Runner runner;

    @Override
    public void endContact(Contact contact) {

        if(runner == null){
            runner = (Runner) (contact.getFixtureA().getUserData() instanceof Runner ? contact.getFixtureA().getUserData() : contact.getFixtureB().getUserData());
        }
        runner.endContact();
    }

    @Override
    public void beginContact(Contact contact) {

        if(runner == null){
            runner = (Runner) (contact.getFixtureA().getUserData() instanceof Runner ? contact.getFixtureA().getUserData() : contact.getFixtureB().getUserData());
        }
        runner.beginContact();
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
};
