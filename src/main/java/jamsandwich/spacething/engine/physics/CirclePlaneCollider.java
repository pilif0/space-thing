package jamsandwich.spacething.engine.physics;

import jamsandwich.spacething.engine.entity.Circle;
import jamsandwich.spacething.engine.entity.Plane;
import jamsandwich.spacething.engine.utils.Utils;
import jamsandwich.spacething.engine.utils.Vector2d;

public class CirclePlaneCollider extends Collider{

	public CirclePlaneCollider() {
		super(CollisionHandler.TYPE_CIRCLE+CollisionHandler.TYPE_PLANE);
	}

	@Override
	public void intersects(CollisionPacket packet) {
		Circle c = null;
		Plane p = null;
		if(packet.e1 instanceof Circle)c = (Circle)packet.e1;
		if(packet.e2 instanceof Circle)c = (Circle)packet.e2;
		if(packet.e1 instanceof Plane)p = (Plane)packet.e1;
		if(packet.e2 instanceof Plane)p = (Plane)packet.e2;
		Vector2d change = Vector2d.sub(c.getPosition(), c.prevPos, null);
		Vector2d closestPoint = Utils.closestPointOnLine(c.getPosition(), p.getP1(), p.getP2());
		Vector2d normal = Vector2d.sub(closestPoint, c.getPosition(), null);
		double d = normal.lengthSquared();
		normal.normalise();
		double v = Vector2d.dot(change, normal);
		normal.scale(v);
		packet.setResults(d-v<c.getRadius(),normal);
	}

	@Override
	public void collide(CollisionPacket packet) {
		Circle c = null;
		if(packet.e1 instanceof Circle)c = (Circle)packet.e1;
		if(packet.e2 instanceof Circle)c = (Circle)packet.e2;
		Vector2d v = (Vector2d) packet.data[0];
		v.scale(2);
		Vector2d.add(c.prevPos, v, c.prevPos);
	}
}