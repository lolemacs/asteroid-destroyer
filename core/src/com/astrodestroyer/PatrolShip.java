package com.astrodestroyer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.Random;


public class PatrolShip {
    Body psBody;
    int psID;
    Vector2 psPosition;

    // physics stuff
    public Body body;
    public Fixture fixture;
    public FixtureDef fixtureDef;
    public CircleShape shape;
    public float radius = 0.10f;

    public PatrolShip()
    {
        // Creating a box2D entity
        BodyDef circleDef = new BodyDef();
        circleDef.type = BodyDef.BodyType.DynamicBody;

        body = com.astrodestroyer.Game.b2World.createBody(circleDef);

        shape = new CircleShape();
        shape.setRadius(radius);

        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f;
        fixtureDef.filter.groupIndex = 1;
        fixture = body.createFixture(fixtureDef);

        Random rnd = new Random();
        body.setTransform((rnd.nextFloat() - 0.5f) * 2f, (rnd.nextFloat() - 0.5f) * 2f, rnd.nextFloat() * 2f - 1f);
        body.setLinearVelocity(rnd.nextFloat() * 0.5f - 0.25f, rnd.nextFloat() * 0.5f - 0.25f);
    }

}
