package jamsandwich.spacething.engine.physics;

import jamsandwich.spacething.engine.entity.Circle;
import jamsandwich.spacething.engine.utils.Vector2d;

public class CircleCircleCollider extends Collider{

	public CircleCircleCollider() {
		super(CollisionHandler.TYPE_CIRCLE+CollisionHandler.TYPE_CIRCLE);
	}

	@Override
	public void intersects(CollisionPacket c) {
		Circle c1 = (Circle) c.e1;
		Circle c2 = (Circle) c.e2;
		double r = c1.getRadius()+c2.getRadius();
		r*=r;
		Vector2d d = new Vector2d(c1.getPosition()).sub(c2.getPosition());
		c.setResults(d.lengthSquared()<r,d);
	}

	@Override
	public void collide(CollisionPacket c) {
		Circle c1 = (Circle) c.e1;
		Circle c2 = (Circle) c.e2;
		Vector2d dir = (Vector2d)c.data[0];
		double r = c1.getRadius()+c2.getRadius();
		r*=r;
		double d = r/(dir.lengthSquared()+r)-0.5;
		dir.scale(d);
		c1.translate(dir.x,dir.y);
		c2.translate(-dir.x,-dir.y);
		Vector2d l = new Vector2d(c1.getPosition()).sub(c2.getPosition());
		c.setResults(l.lengthSquared()<r,l);
	}
}