package sjmhrp.core.entity;

import sjmhrp.physics.Engine;
import sjmhrp.physics.GravityEngine;
import sjmhrp.physics.PhysicsEngine;

public class GravitationalBody extends Circle{

	public GravitationalBody(double x, double y, double radius, double m, float[] c) {
		super(x,y,radius,m,c);
		for(PhysicsEngine e : Engine.engines) {
			if(e instanceof GravityEngine) {
				((GravityEngine)e).register(this);
			}
		}
	}
}
