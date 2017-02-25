package jamsandwich.spacething.engine.core;

import jamsandwich.spacething.engine.core.entity.Player;
import jamsandwich.spacething.engine.event.KeyHandler;
import jamsandwich.spacething.engine.physics.CollisionEngine;
import jamsandwich.spacething.engine.physics.Engine;
import jamsandwich.spacething.engine.physics.GravityEngine;
import jamsandwich.spacething.engine.physics.NewtonianEngine;
import jamsandwich.spacething.engine.physics.SpriteEngine;
import jamsandwich.spacething.engine.physics.ZeroEngine;
import jamsandwich.spacething.engine.render.RenderHandler;
import jamsandwich.spacething.engine.render.SpriteHelper;
import jamsandwich.spacething.engine.utils.Log;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import java.util.Random;

public class CoreMain {

	static final String name = "Space Thingy";
	static final int[] size = {720,480};
	static final int speed = 1;
	
	public static void coreMain(String[] args) {
		init();
		while(!Display.isCloseRequested()) {
			tick();
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))System.exit(0);
			if(Engine.tick%speed==0) RenderHandler.render();
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