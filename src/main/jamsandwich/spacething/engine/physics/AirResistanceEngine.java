package jamsandwich.spacething.engine.physics;

import jamsandwich.spacething.engine.entity.Entity;
import jamsandwich.spacething.engine.render.Sprite;

public class AirResistanceEngine extends PhysicsEngine{

	public AirResistanceEngine(int p) {
		super(p);
	}

	@Override
	public void tickSprite(int tick, Sprite s) {
		if(s instanceof Entity) {
			((Entity) s).applyForce(-0.001*((Entity)s).velocity[0]*Math.abs(((Entity)s).velocity[0]),-0.001*((Entity)s).velocity[1]*Math.abs(((Entity)s).velocity[1]), 0, 0);
		}
	}
}