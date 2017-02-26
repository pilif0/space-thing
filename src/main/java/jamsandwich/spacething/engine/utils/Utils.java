package jamsandwich.spacething.engine.utils;

public class Utils {

	public static Vector2d closestPointOnLine(Vector2d point, Vector2d a, Vector2d b) {
		Vector2d c = Vector2d.sub(point, a, null);
		Vector2d v = Vector2d.sub(b, a, null);
		double d = v.length();
		v.normalise();
		double t = Vector2d.dot(v, c);
		if(t<0)return a;
		if(t>d)return b;
		v.scale(t);
		return Vector2d.add(a, v, null);
	}
}