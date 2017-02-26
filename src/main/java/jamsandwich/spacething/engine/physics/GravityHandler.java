package jamsandwich.spacething.engine.physics;

import jamsandwich.spacething.engine.entity.GravitationalBody;
import jamsandwich.spacething.engine.utils.Config;
import jamsandwich.spacething.engine.utils.Vector2d;

public class GravityHandler {

	public static float gravity = Config.getFloat("gravity");
	
	public static void addGravity(GravitationalBody e1, GravitationalBody e2) {
		Vector2d dir = Vector2d.sub(e2.getPosition(), e1.getPosition(), null);
		double r2 = dir.lengthSquared();
		double d = e1.getRadius()+e2.getRadius();
		d*=d;
		if(r2<d)return;
		double g = gravity*e1.getMass()*e2.getMass()/r2;
		dir.scale(g);
		e1.addForce(dir);
		dir.scale(-1);
		e2.addForce(dir);
	}
}