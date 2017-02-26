package jamsandwich.spacething.world;

import java.util.ArrayList;

import jamsandwich.spacething.engine.entity.Entity;
import jamsandwich.spacething.engine.entity.GravitationalBody;
import jamsandwich.spacething.engine.physics.CollisionHandler;
import jamsandwich.spacething.engine.physics.CollisionPacket;
import jamsandwich.spacething.engine.physics.Engine;
import jamsandwich.spacething.engine.physics.GravityHandler;

public class World {

	ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public World() {
		Engine.addWorld(this);
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}

	public void step(double dt) {
		for(Entity e : entities) {
			addGravity();
			e.integrate(dt);
		}
		collide(dt);
	}

	void addGravity() {
		ArrayList<Entity> checked  = new ArrayList<Entity>();
		for(Entity e1 : entities) {
			checked.add(e1);
			if(!(e1 instanceof GravitationalBody))continue;
			for(Entity e2 : entities) {
				if(!(e2 instanceof GravitationalBody))continue;
				if(!checked.contains(e2))GravityHandler.addGravity((GravitationalBody)e1, (GravitationalBody)e2);
			}
		}
	}
	
	void collide(double dt) {
		ArrayList<CollisionPacket> collisions = new ArrayList<CollisionPacket>();
		ArrayList<Entity> checked = new ArrayList<Entity>();
		for(Entity e1 : entities) {
			checked.add(e1);
			for(Entity e2 : entities) {
				if(!checked.contains(e2)) {
					CollisionPacket c = new CollisionPacket(e1,e2);
					if(CollisionHandler.intersects(c))collisions.add(c);
				}
			}
		}
		for(int i = 0;i<100&&collisions.size()>0;i++) {
			ArrayList<CollisionPacket> remove = new ArrayList<CollisionPacket>();
			for(CollisionPacket c : collisions) {
				boolean b = CollisionHandler.resolve(c);
				if(b)remove.add(c);
			}
			collisions.removeAll(remove);
		}
	}
}