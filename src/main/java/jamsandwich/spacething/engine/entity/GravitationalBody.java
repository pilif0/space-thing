package jamsandwich.spacething.engine.entity;

import jamsandwich.spacething.engine.utils.Vector2d;

public class GravitationalBody extends Circle{

	public GravitationalBody(double x, double y, double radius, double m, float[] c) {
		super(new Vector2d(x,y),radius,m,c);
	}
}