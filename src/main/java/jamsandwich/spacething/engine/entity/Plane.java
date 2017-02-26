package jamsandwich.spacething.engine.entity;

import jamsandwich.spacething.engine.render.RawModel;
import jamsandwich.spacething.engine.utils.Vector2d;

public class Plane extends Entity{

	public Plane(Vector2d p1,Vector2d p2,float[] c,double m) {
		super(createPlaneModel(p1,p2,c),new Vector2d(p1).add(p2).scale(0.5),m);
	}

	public Vector2d getNormal() {
		Vector2d p1 = getModel().getMesh()[0];
		Vector2d p2 = getModel().getMesh()[1];
		Vector2d dir = new Vector2d();
		dir.x=p2.y-p1.y;
		dir.y=p1.x-p2.x;
		dir.normalise();
		return dir;
	}
	
	public Vector2d getP1() {
		return getModel().getMesh()[0];
	}	

	public Vector2d getP2() {
		return getModel().getMesh()[1];
	}

	public static RawModel createPlaneModel(Vector2d p1,Vector2d p2,float[] c) {
		return new RawModel(2,1,c,new Vector2d[]{p1,p2});
	}	
}