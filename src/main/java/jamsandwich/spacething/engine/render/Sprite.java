package jamsandwich.spacething.engine.render;

import jamsandwich.spacething.engine.utils.Vector2d;

public class Sprite {

	RawModel model;
	Vector2d position;
	double rotation;
	
	public Sprite(RawModel m, Vector2d position, double rotation) {
		this.model = m;
		this.position = position;
		this.rotation = rotation;
		RenderRegistry.registerSprite(this);
	}

	public Sprite(RawModel m, Vector2d position) {
		this(m,position,0);
	}

	public void translate(double x, double y) {
		position.add(x,y);
	}
	
	public void translate(Vector2d v) {
		position.add(v);
	}
	
	public RawModel getModel() {
		return model;
	}

	public Vector2d getPosition() {
		return position;
	}

	public double getRotation() {
		return rotation;
	}

	public void tick() {}
}