package jamsandwich.spacething.engine.core.entity;

import static jamsandwich.spacething.engine.utils.Utils.toRadians;

public class Circle extends Entity{

	double radius;

	public Circle(double x, double y, double r, double m, float[] c) {
		super("",2,6,c,getVertices(x,y,r),0,m);
		this.radius = r;
	}

	public static double[][] getVertices(double x, double y, double r) {
		double[][] p = new double[360][2];
		for(int i = 0; i < 360; i++) {
			p[i][0] = x+(r*Math.cos(toRadians(i)));
			p[i][1] = y+(r*Math.sin(toRadians(i)));
		}
		return p;
	}

	public double getRadius() {
		return radius;
	}
}