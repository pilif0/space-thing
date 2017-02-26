package jamsandwich.spacething.engine.physics;

import jamsandwich.spacething.engine.entity.Entity;

public class CollisionPacket {

	public Entity e1;
	public Entity e2;
	
	boolean collides;
	Object[] data;
	
	public CollisionPacket(Entity e1, Entity e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	
	public void integrate(double dt) {
		e1.integrate(dt);
		e2.integrate(dt);
	}

	public void setResults(boolean collides,Object... data) {
		this.collides = collides;
		this.data = data;
	}
}