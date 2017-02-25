package sjmhrp.physics;

import sjmhrp.core.entity.Entity;
import sjmhrp.render.Sprite;

public class ZeroEngine extends PhysicsEngine{

	public ZeroEngine(int p) {
		super(p);
	}

	@Override
	public Sprite tickSprite(int tick, Sprite s) {
		if(s instanceof Entity) {
			((Entity) s).acceleration = new double[] {0,0};
			((Entity) s).angularAccel = 0;
		}
		return s;
	}
}
