package jamsandwich.spacething.engine.entity;

import jamsandwich.spacething.engine.render.RawModel;
import jamsandwich.spacething.engine.utils.Vector2d;

public class Circle extends Entity{

	double radius;

	public Circle(Vector2d pos,double r,double m,float[] c) {
		super(createCircleModel(c,r),pos,m);
		this.radius = r;
	}

	public double getRadius() {
		return radius;
	}

	public static RawModel createCircleModel(float[]c,double r) {
		Vector2d[] p = new Vector2d[360];
		for(int i = 0; i < 360; i++) {
			p[i] = new Vector2d(r*Math.cos(Math.toRadians(i)),r*Math.sin(Math.toRadians(i)));
		}
		return new RawModel(2,6,c,p);
	}
}