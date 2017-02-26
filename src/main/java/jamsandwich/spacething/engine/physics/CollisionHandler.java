package jamsandwich.spacething.engine.physics;

import java.util.HashMap;

import jamsandwich.spacething.engine.entity.Circle;
import jamsandwich.spacething.engine.entity.GravitationalBody;
import jamsandwich.spacething.engine.entity.Plane;
import jamsandwich.spacething.engine.entity.Player;

public class CollisionHandler {

	public static final int TYPE_CIRCLE = 1;
	public static final int TYPE_PLANE = 2;
	public static final int TYPE_GENERIC = 4;
	
	@SuppressWarnings("rawtypes")
	static HashMap<Class,Integer> classes = new HashMap<Class,Integer>();
	static HashMap<Integer,Collider> colliders = new HashMap<Integer,Collider>();
	
	public static void init() {
		classes.put(Circle.class,TYPE_CIRCLE);
		classes.put(Player.class,TYPE_CIRCLE);
		classes.put(GravitationalBody.class,TYPE_CIRCLE);
		classes.put(Plane.class,TYPE_PLANE);
		new CircleCircleCollider();
		new CirclePlaneCollider();
	}
	
	static Collider getCollider(CollisionPacket c) {
		return colliders.get(classes.get(c.e1.getClass())+classes.get(c.e2.getClass()));
	}
	
	public static boolean intersects(CollisionPacket packet) {
		Collider c = getCollider(packet);
		if(c!=null)c.intersects(packet);
		return packet.collides;
	}
	
	public static boolean resolve(CollisionPacket packet) {
		Collider c = getCollider(packet);
		if(c!=null)c.collide(packet);
		return packet.collides;
	}
}