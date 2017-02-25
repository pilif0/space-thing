package jamsandwich.spacething.engine.physics;


import jamsandwich.spacething.engine.render.RenderRegistry;
import jamsandwich.spacething.engine.render.Sprite;
import jamsandwich.spacething.engine.utils.Log;

import java.util.ArrayList;
import java.util.Comparator;

public class Engine {
	
	public static ArrayList<PhysicsEngine> engines = new ArrayList<PhysicsEngine>();
	public static int tick = 0;
	
	public static void registerEngine(PhysicsEngine e) {
		Log.print("Registering " + e.getClass().getSimpleName());
		engines.add(e);
		sort();
		Log.done();
	}
	
	public static void tick() {
		ArrayList<Sprite> sprites = RenderRegistry.spriteRegistry;
		for(PhysicsEngine e : engines) {
			sprites = e.tick(tick, sprites);
		}
		tick++;
		RenderRegistry.spriteRegistry = sprites;
	}
	
	static void sort() {
		Comparator<PhysicsEngine> c = (PhysicsEngine a, PhysicsEngine b)->(a.priority-b.priority);
		engines.sort(c);
	}
}