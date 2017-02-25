package jamsandwich.spacething.engine.physics;

import jamsandwich.spacething.engine.core.entity.Entity;
import jamsandwich.spacething.engine.render.Sprite;
import jamsandwich.spacething.engine.utils.Config;

public class NewtonianEngine extends PhysicsEngine{

	public NewtonianEngine(int p) {
		super(p);
	}

	@Override
	public Sprite tickSprite(int tick, Sprite s) {
		if(s instanceof Entity) {
			double[] v = ((Entity)s).velocity;
			double[] a = ((Entity)s).acceleration;
			double w = ((Entity)s).angularVel;
			double aw = ((Entity)s).angularAccel;
			s.translate(v[0]/ Config.velocityConstant, -v[1]/Config.velocityConstant);
			s.rotate(w);
			((Entity)s).addVelocity(a[0], a[1]);
			((Entity)s).addAngularVel(aw);
		}
		return s;
	}
}