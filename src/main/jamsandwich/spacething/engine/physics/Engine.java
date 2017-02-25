package jamsandwich.spacething.engine.physics;

import java.util.ArrayList;
import java.util.Comparator;

import jamsandwich.spacething.engine.utils.Log;

public class Engine {
	
	public static ArrayList<PhysicsEngine> engines = new ArrayList<PhysicsEngine>();
	public static int tick = 0;
	public static double timeStep;
	
	public static void registerEngine(PhysicsEngine e) {
		Log.print("Registering " + e.getClass().getSimpleName());
		engines.add(e);
		sort();
		Log.done();
	}
	
	public static void tick(double dt) {
		timeStep = dt;
		for(PhysicsEngine e : engines) {
			e.tick(tick);
		}
		tick++;
	}
	
	static void sort() {
		Comparator<PhysicsEngine> c = (PhysicsEngine a, PhysicsEngine b)->(a.priority-b.priority);
		engines.sort(c);
	}
}