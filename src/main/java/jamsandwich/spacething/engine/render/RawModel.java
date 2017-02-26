package jamsandwich.spacething.engine.render;

import jamsandwich.spacething.engine.utils.Vector2d;

public class RawModel {

	int fill;
	int shape;
	float[] colour;
	Vector2d[] mesh;
	Vector2d center;
	
	public RawModel(int fill, int shape, float[] colour, Vector2d[] mesh) {
		this.fill = fill;
		this.shape = shape;
		this.colour = colour;
		this.mesh = mesh;
		center = centroid();
	}

	public void rotate(double angle) {
		rotateAround(angle,center);
	}
	
	public void rotateAround(double angle, Vector2d point) {
		Vector2d c = centroid();
		for(int i = 0; i < mesh.length; i++) {
			double distX = mesh[i].x - c.x;
			double distY = mesh[i].y - c.y;
			mesh[i].x = c.x + (distX*Math.cos(Math.toRadians(360-angle)) + (distY*Math.sin(Math.toRadians(360-angle))));
			mesh[i].y = c.y - (distX*Math.sin(Math.toRadians(360-angle)) - (distY*Math.cos(Math.toRadians(360-angle))));
		}
		this.center = centroid();
	}

	public Vector2d centroid() {
		Vector2d av = new Vector2d();
		for(int i = 0; i < mesh.length; i++) {
			av.add(mesh[i]);
		}
		av.scale(1d/mesh.length);
		return av;
	}

	public int getFill() {
		return fill;
	}

	public int getShape() {
		return shape;
	}

	public float[] getColour() {
		return colour;
	}

	public Vector2d[] getMesh() {
		return mesh;
	}

	public Vector2d getCenter() {
		return center;
	}
}