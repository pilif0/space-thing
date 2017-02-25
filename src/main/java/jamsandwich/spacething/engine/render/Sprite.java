package jamsandwich.spacething.engine.render;

import jamsandwich.spacething.engine.utils.Utils;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Sprite {
	
	public String name;
	int fill;
	int shape;
	float[] colour;
	public double[][] vertices;
	double[] center;
	int layer;
	public ArrayList<Object> metadata;
	
	public Sprite(String n, int f, int s, float[] c, double[][] v, int l) {
		name = n;
		fill = f;
		shape = s;
		colour = c;
		vertices = v;
		layer = l;
		center=centroid();
		RenderRegistry.registerSprite(this);
	}

	public static Sprite blankSprite(double[][] v) {
		Sprite sp = new Sprite("",0,0,new float[] {},v,0);
		RenderRegistry.spriteRegistry.remove(sp);
		return sp;
	}
	
	public Sprite rotate(double angle) {
		double[] c = centroid();
		for(int i = 0; i < vertices.length; i++) {
			double distX = vertices[i][0] - c[0];
			double distY = vertices[i][1] - c[1];
			vertices[i][0] = c[0] + (distX*Math.cos(Utils.toRadians(360-angle)) + (distY*Math.sin(Utils.toRadians(360-angle))));
			vertices[i][1] = c[1] - (distX*Math.sin(Utils.toRadians(360-angle)) - (distY*Math.cos(Utils.toRadians(360-angle))));
		}
		this.center = centroid();
		return this;
	}
	
	public double getArea() {
		BigDecimal A = BigDecimal.ZERO;
		for(int i = 0; i < vertices.length; i++) {
			double[] p1 = vertices[i];
			double[] p2 = vertices[(i+1)%vertices.length];
			double[] p3 = centroid();
			BigDecimal x = Utils.distance(p1,p2);
			BigDecimal b = Utils.distance(p2,p3);
			BigDecimal d = Utils.distance(p1,p3);
			BigDecimal a = ((d.multiply(d)).add(b.multiply(b)).subtract(x.multiply(x))).divide(b.add(b));
			BigDecimal h = Utils.sqrt((d.multiply(d)).subtract(a.multiply(a)),d.scale());
			A = a.add(BigDecimal.valueOf(0.5).multiply(b).multiply(h));
		}
		return A.doubleValue();
	}

	public double[] getCenter() {
		return center;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double[] centroid() {
		double[] av = {0,0};
		for(int i = 0; i < vertices.length; i++) {
			av[0] += vertices[i][0];
			av[1] += vertices[i][1];
		}
		av[0] /= vertices.length;
		av[1] /= vertices.length;
		return av;
	}
	
	public void rotateAround(double[] pos, double angle) {
		for(int i = 0; i < vertices.length; i++) {
			double distX = vertices[i][0] - pos[0];
			double distY = vertices[i][1] - pos[1];
			vertices[i][0] = pos[0] + (distX*Math.cos(Utils.toRadians(360-angle)) + (distY*Math.sin(Utils.toRadians(360-angle))));
			vertices[i][1] = pos[1] - (distX*Math.sin(Utils.toRadians(360-angle)) - (distY*Math.cos(Utils.toRadians(360-angle))));
		}
		center=centroid();
	}
	
	public Sprite translate(double x, double y) {
		for(int i = 0; i < vertices.length; i++) {
			vertices[i][0] += x;
			vertices[i][1] += y;
		}
		center=centroid();
		return this;
	}
	
	public Sprite translateAndRotate(double x, double y, double angle) {
		translate(x,y);
		rotate(angle);
		return this;
	}
	
	public ArrayList<double[][]> decompose() {
		ArrayList<double[][]> triangles = new ArrayList<double[][]>();
		double[] c = centroid();
		for(int i = 0; i < vertices.length; i++) {
			triangles.add(new double[][] {c,vertices[i],vertices[(i+1)%vertices.length]});
		}
		return triangles;
	}
	
	public void tick() {}
}
