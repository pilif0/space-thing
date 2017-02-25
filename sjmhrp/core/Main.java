package sjmhrp.core;

import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import sjmhrp.core.entity.Player;
import sjmhrp.event.KeyHandler;
import sjmhrp.physics.CollisionEngine;
import sjmhrp.physics.Engine;
import sjmhrp.physics.GravityEngine;
import sjmhrp.physics.NewtonianEngine;
import sjmhrp.physics.SpriteEngine;
import sjmhrp.physics.ZeroEngine;
import sjmhrp.render.RenderHandler;
import sjmhrp.render.SpriteHelper;
import sjmhrp.utils.Log;

public class Main {

	static final String name = "Space Thingy";
	static final int[] size = {720,480};
	static final int speed = 1;
	
	public static void main(String[] args) {
		init();
		while(!Display.isCloseRequested()) {
			tick();
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))System.exit(0);
			if(Engine.tick%speed==0)RenderHandler.render();
		}
	}
	
	static void init() {
		RenderHandler.init(name, size);
		Engine.registerEngine(new ZeroEngine(0));
		Engine.registerEngine(new GravityEngine(1));
		Engine.registerEngine(new CollisionEngine(2));
		Engine.registerEngine(new KeyHandler(4));
		Engine.registerEngine(new NewtonianEngine(5));
		Engine.registerEngine(new SpriteEngine(6));
	
		Log.print("Initializing Level");
		new Player(129, 345);
		Random r = new Random();
		for(int i = 0; i < 1; i++) {
			SpriteHelper.newBlackHole(r.nextFloat()*200+200,r.nextFloat()*200+200,5, 2);
		}
		Log.done();
	}

	static void tick() {
		Engine.tick();
	}
}