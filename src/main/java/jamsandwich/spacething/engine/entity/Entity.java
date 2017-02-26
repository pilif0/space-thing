package jamsandwich.spacething.engine.entity;

import jamsandwich.spacething.engine.render.RawModel;
import jamsandwich.spacething.engine.render.RenderRegistry;
import jamsandwich.spacething.engine.render.Sprite;
import jamsandwich.spacething.engine.utils.Vector2d;

public class Entity extends Sprite {

	public double invmass;

	public Vector2d prevPos = new Vector2d();
	public Vector2d force = new Vector2d();
	
	public Entity(RawModel m, Vector2d pos, double rot, double mass) {
		super(m,pos,rot);
		this.prevPos = new Vector2d(pos);
		this.invmass = mass==0?0:1d/mass;
		RenderRegistry.registerEntity(this);
	}

	public Entity(RawModel m, Vector2d pos, double mass) {
		this(m,pos,0,mass);
	}

	public void setMass(double m) {
		invmass = m==0?0:1d/m;
	}
	
	public void integrate(double dt) {
		if(invmass==0)return;
		Vector2d tmp = new Vector2d(getPosition());
		Vector2d change = new Vector2d(getPosition());
		change.sub(prevPos);
		change.scale(0.99d);
		Vector2d a = new Vector2d(force);
		a.scale(invmass*dt*dt);
		change.add(a);
		translate(change);
		prevPos = tmp;
		force.zero();
	}

	public void addForce(Vector2d force) {
		this.force.add(force);
	}

	public void addForce(double x,double y) {
		this.force.add(x,y);
	}

	public double getMass() {
		return 1d/invmass;
	}
}