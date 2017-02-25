package jamsandwich.spacething.engine.physics;

import jamsandwich.spacething.engine.render.Sprite;

public class SpriteEngine extends PhysicsEngine{

	public SpriteEngine(int p) {
		super(p);
	}

	@Override
	public void tickSprite(int tick, Sprite s) {
		s.tick();
	}	
}
