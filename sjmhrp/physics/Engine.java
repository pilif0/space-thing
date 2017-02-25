package sjmhrp.physics;

import java.util.ArrayList;
import java.util.Comparator;

import sjmhrp.render.RenderRegistry;
import sjmhrp.render.Sprite;
import sjmhrp.utils.Log;

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