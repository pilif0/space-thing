package jamsandwich.spacething.engine.core;

import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import jamsandwich.spacething.engine.entity.Plane;
import jamsandwich.spacething.engine.entity.Player;
import jamsandwich.spacething.engine.event.KeyHandler;
import jamsandwich.spacething.engine.physics.CollisionHandler;
import jamsandwich.spacething.engine.physics.Engine;
import jamsandwich.spacething.engine.render.RenderHandler;
import jamsandwich.spacething.engine.render.SpriteHelper;
import jamsandwich.spacething.engine.utils.Config;
import jamsandwich.spacething.engine.utils.Vector2d;
import jamsandwich.spacething.world.World;

public class CoreMain {

	static final String name = "Space Thingy";
	static final int[] size = {720,480};
	static final int speed = 1;

	static World world;

	public static void coreMain(String[] args) {
		Config.read();
		init();
		while(!Display.isCloseRequested()) {
			long time = System.nanoTime();
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))System.exit(0);
			if(Engine.tick%speed==0)RenderHandler.render();
			tick((System.nanoTime()-time)/1000000000d);
		}
	}

	static void init() {
		RenderHandler.init(name, size);	
		CollisionHandler.init();
		world = new World();
		world.addEntity(new Player(150,200));
		world.addEntity(new Plane(new Vector2d(-1,Display.getHeight()),new Vector2d(-1,0),new float[]{1,0,0},0));
		world.addEntity(new Plane(new Vector2d(Display.getWidth()+1,0),new Vector2d(Display.getWidth()+1,Display.getHeight()),new float[]{1,0,0},0));
		world.addEntity(new Plane(new Vector2d(Display.getWidth(),-1),new Vector2d(0,-1),new float[]{1,0,0},0));
		world.addEntity(new Plane(new Vector2d(0,Display.getHeight()+1),new Vector2d(Display.getWidth(),Display.getHeight()+1),new float[]{1,0,0},0));
		Random r = new Random();
		for(int i = 0; i < 20; i++) {
			world.addEntity(SpriteHelper.newAsteroid(r.nextFloat()*200+300,r.nextFloat()*200+100,10,10));
		}
	}

	static void tick(double dt) {
		KeyHandler.tick();
		Engine.tick(dt);
	}
}