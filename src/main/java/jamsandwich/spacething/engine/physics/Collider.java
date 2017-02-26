package jamsandwich.spacething.engine.physics;

public abstract class Collider {

	int type;

	public Collider(int type) {
		this.type = type;
		CollisionHandler.colliders.put(type, this);
	}

	public abstract void intersects(CollisionPacket c);

	public abstract void collide(CollisionPacket c);
}
