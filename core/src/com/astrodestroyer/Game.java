package com.astrodestroyer;

import box2dLight.RayHandler;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.io.File;
import java.util.ArrayList;

public class Game extends ApplicationAdapter
{
    SpriteBatch batch;
    Texture img;

    // Screens
//		public static MainMenu mainMenu;
//		public static LevelManager levelManager;
//		public static Renderable activeRenderer;

    // IO event listener
    static MainInputProcessor inputProcessor;

    // Main singleton objects
    public static TextureAtlas atlas;
    public static OrthographicCamera camera;
    public static World b2World;
    public static RayHandler rayHandler;

    // Debug objects
    static boolean debugOn = true;
    static Box2DDebugRenderer b2Debug;
    static FPSLogger fpsLogger;

    // Game font
    public static BitmapFont font;

    // Our sounds
    public static Music bgMusic;

    // Entities
    ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
	ArrayList<PatrolShip> pShips = new ArrayList<PatrolShip>();
    VillainShip vShip = null;

    @Override
    public void create()
    {

        // Our game's camera
        camera = new OrthographicCamera();

        // Atlas with most textures used in the game
		atlas = new TextureAtlas(new FileHandle(new File("atlas/atlas.atlas")));

        // Physics world - where all physics calculation is done
        b2World = new World(new Vector2(0f, 0f), true);
//		b2World.setContactListener(new WorldContactListener());

        // Box2D debugger - will draw Box2D objects on the screen, when activated
        b2Debug = new Box2DDebugRenderer();

        // Handles lights and shadows in the game
        rayHandler = new RayHandler(b2World);
        RayHandler.useDiffuseLight(true);
        RayHandler.setGammaCorrection(true);

        // Object used to log our game's FPS
        fpsLogger = new FPSLogger();

        // Object that handles input callbacks
        inputProcessor = new MainInputProcessor();
        Gdx.input.setInputProcessor(inputProcessor);

        vShip = new VillainShip();

        for (int i = 0; i < 10; i++)
        {
            asteroids.add(new Asteroid());
        }

        for (int i = 0; i < 3; i++)
        {
            pShips.add(new PatrolShip());
        }

        // Manages loading and displaying current maps
//		levelManager = new LevelManager();

        // The main menu of our game
//		mainMenu = new MainMenu();

        // The screen we are rendering at the moment - Main Menu or one of the levels.
//		activeRenderer = mainMenu;

//		bgMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/inGameMusic.wav"));

//		bgMusic.setLooping(true);
//		bgMusic.play();


    }

    @Override
    public void render()
    {
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        System.out.println(vShip.body.getAngle());

        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Move forward and break
        if (Gdx.input.isKeyPressed(Input.Keys.UP))
        {
            Vector2 f = new Vector2(0.1f, 0.f);
            f.set(f.rotateRad(vShip.body.getAngle()));
            vShip.body.applyForceToCenter(f, true);
        }
        // Turning
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            vShip.body.applyTorque(.1f, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            vShip.body.applyTorque(-0.1f, true);
        }

//		cameraHandler.update();

        // Render lights and shadows
//		Game.rayHandler.setCombinedMatrix(camera.combined);
//		Game.rayHandler.updateAndRender();


        // Render Box2D debug and log fps, if requested
        if (debugOn)
        {
            b2Debug.render(b2World, camera.combined);
            fpsLogger.log();
        }

        // Update the game's physics
        b2World.step(1 / 60f, 6, 2);
    }

    @Override
    public void resize(int width, int height)
    {
        // Adapt the camera to the new viewport resolution
        camera.setToOrtho(false, Gdx.graphics.getWidth()/32f, Gdx.graphics.getHeight()/32f);

		camera.position.set(0, 0, 0);
        camera.update();
    }

}