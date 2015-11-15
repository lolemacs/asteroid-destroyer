package com.astrodestroyer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;


public class VillainShip
{
    float accel = 0f;
    VillainShip si;

//    Vector2 position;

    Sprite sprite;

    // physics stuff
    public Body body;
    public Fixture fixture;
    public FixtureDef fd;
    public ArrayList<PolygonShape> shapeParts = new ArrayList<PolygonShape>();
    public float radius = .30f;

    public VillainShip()
    {
//        position = new Vector2(0,0);

        sprite = new Sprite(Game.atlas.findRegion("EvilShip"));
        sprite.setSize(sprite.getRegionWidth() / 32f, sprite.getRegionHeight() / 32f);

        shapeParts = Utils.htmlToVecArray(Gdx.files.internal("EvilShip.txt").readString(), .05f);

        // Creating a box2D entity
        BodyDef bd = new BodyDef();
        bd.type = BodyDef.BodyType.DynamicBody;
        body = Game.b2World.createBody(bd);

            fd = new FixtureDef();
            fd.density = 1.0f;
            fd.friction = 0.0f;
            fd.restitution = 0.3f;
            fd.filter.groupIndex = 1;
            fd.shape = shapeParts.get(0);
            body.createFixture(fd);


        // Initial state of the ship
        body.setTransform(0, 0, 0);
        body.setLinearVelocity(0, 0);
    }

    public void render()
    {
        sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() / 2);
      /*
      LevelManager.renderer.getSpriteBatch().begin();
	  	portalSprite.draw(LevelManager.renderer.getSpriteBatch());
	  	LevelManager.renderer.getSpriteBatch().end();
      */

    }

    public void dispose()
    {
        Game.b2World.destroyBody(body);
    }


}