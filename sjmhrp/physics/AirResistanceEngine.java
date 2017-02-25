package sjmhrp.physics;

import sjmhrp.core.entity.Entity;
import sjmhrp.render.Sprite;

public class AirResistanceEngine extends PhysicsEngine{

	public AirResistanceEngine(int p) {
		super(p);
	}

	@Override
	public Sprite tickSprite(int tick, Sprite s) {
		if(s instanceof Entity) {
			((Entity) s).applyForce(-0.001*((Entity)s).velocity[0]*Math.abs(((Entity)s).velocity[0]),-0.001*((Entity)s).velocity[1]*Math.abs(((Entity)s).velocity[1]), 0, 0);
		}
		return s;
	}

}
