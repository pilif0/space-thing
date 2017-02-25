package jamsandwich.spacething.engine.core.entity;

import jamsandwich.spacething.engine.physics.Engine;
import jamsandwich.spacething.engine.physics.GravityEngine;
import jamsandwich.spacething.engine.physics.PhysicsEngine;

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
