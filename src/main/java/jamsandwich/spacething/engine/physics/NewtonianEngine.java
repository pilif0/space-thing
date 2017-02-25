package jamsandwich.spacething.engine.physics;

import jamsandwich.spacething.engine.entity.Entity;
import jamsandwich.spacething.engine.render.Sprite;

public class NewtonianEngine extends PhysicsEngine{

	public NewtonianEngine(int p) {
		super(p);
	}

	@Override
	public void tickSprite(int tick, Sprite s) {
		if(s instanceof Entity) {
			double[] v = ((Entity)s).velocity;
			double[] a = ((Entity)s).acceleration;
			double w = ((Entity)s).angularVel;
			double aw = ((Entity)s).angularAccel;
			s.translate(v[0]*Engine.timeStep, -v[1]*Engine.timeStep);
			s.rotate(w);
			((Entity)s).addVelocity(a[0], a[1]);
			((Entity)s).addAngularVel(aw);
		}
	}
}