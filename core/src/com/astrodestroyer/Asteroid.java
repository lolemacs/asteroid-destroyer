package com.astrodestroyer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.physics.box2d.*;

import java.util.Random;


public class Asteroid
{

    // physics stuff
    public Body body;
    public Fixture fixture;
    public FixtureDef fixtureDef;
    public CircleShape shape;
    public float radius;

    public Asteroid()
    {
        Random rnd = new Random();

        radius = (rnd.nextFloat()) * 0.2f + 0.2f;

        // Creating a box2D entity
        BodyDef circleDef = new BodyDef();
        circleDef.type = BodyDef.BodyType.DynamicBody;

        body = Game.b2World.createBody(circleDef);

        shape = new CircleShape();
        shape.setRadius(radius);

        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f;
        fixtureDef.filter.groupIndex = 1;
        fixture = body.createFixture(fixtureDef);

        body.setTransform( (rnd.nextFloat() -0.5f) * 20f, (rnd.nextFloat() - 0.5f) * 20f, rnd.nextFloat() * 2f - 1f);
        body.setLinearVelocity(rnd.nextFloat() * 0.5f - 0.25f, rnd.nextFloat() * 0.5f - 0.25f);
    }

    public void render()
    {

    }


}
