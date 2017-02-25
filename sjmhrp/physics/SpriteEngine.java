package sjmhrp.physics;

import sjmhrp.render.Sprite;

public class SpriteEngine extends PhysicsEngine{

	public SpriteEngine(int p) {
		super(p);
	}

	@Override
	public Sprite tickSprite(int tick, Sprite s) {
		s.tick();
		return s;
	}	
}
