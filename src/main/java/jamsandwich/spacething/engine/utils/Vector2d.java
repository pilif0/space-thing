package jamsandwich.spacething.engine.utils;

public class Vector2d {

	public double x,y;

	public Vector2d(double x, double y) {
		this.x=x;
		this.y=y;
	}

	public Vector2d() {
		this(0,0);
	}

	public Vector2d(Vector2d d) {
		this(d.x,d.y);
	}

	public Vector2d add(Vector2d v) {
		x+=v.x;
		y+=v.y;
		return this;
	}

	public Vector2d add(double x, double y) {
		this.x+=x;
		this.y+=y;
		return this;
	}

	public Vector2d sub(Vector2d v) {
		x-=v.x;
		y-=v.y;
		return this;
	}

	public Vector2d scale(double d) {
		x*=d;
		y*=d;
		return this;
	}

	public double lengthSquared() {
		return x*x+y*y;
	}

	public double length() {
		return Math.sqrt(lengthSquared());
	}

	public void normalise() {
		double l = 1d/length();
		x*=l;
		y*=l;
	}

	public void zero() {
		x=0;
		y=0;
	}

	@Override
	public String toString() {
		return "Vector2d["+x+", "+y+"]";
	}

	public static Vector2d add(Vector2d v1,Vector2d v2,Vector2d out) {
		if(out==null)out=new Vector2d();
		out.x=v1.x+v2.x;
		out.y=v1.y+v2.y;
		return out;
	}
	
	public static Vector2d sub(Vector2d v1,Vector2d v2,Vector2d out) {
		if(out==null)out=new Vector2d();
		out.x=v1.x-v2.x;
		out.y=v1.y-v2.y;
		return out;
	}

	public static double dot(Vector2d v1, Vector2d v2) {
		return v1.x*v2.x+v1.y*v2.y;
	}
}