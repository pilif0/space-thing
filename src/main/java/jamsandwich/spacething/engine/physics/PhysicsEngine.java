package jamsandwich.spacething.engine.physics;

import java.util.ArrayList;

import jamsandwich.spacething.engine.render.RenderRegistry;
import jamsandwich.spacething.engine.render.Sprite;

public abstract class PhysicsEngine {

	int priority;
	
	public PhysicsEngine(int p) {
		priority = p;
	}
	
	void tick(int tick) {
		ArrayList<Sprite> sprites = RenderRegistry.spriteRegistry;
		for(int i = 0; i < sprites.size(); i++) {
			Sprite s = sprites.get(i);
			tickSprite(tick, s);
		}
	}

	public abstract void tickSprite(int tick, Sprite s);
}
