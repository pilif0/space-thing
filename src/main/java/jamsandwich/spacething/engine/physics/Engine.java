package jamsandwich.spacething.engine.physics;

import java.util.ArrayList;

import jamsandwich.spacething.world.World;

public class Engine {

	static ArrayList<World> worlds = new ArrayList<World>();

	public static int tick = 0;
	public static double timeStep;

	public static void addWorld(World w) {
		worlds.add(w);
	}

	public static void tick(double dt) {
		timeStep = dt;
		for(World world : worlds) {
			world.step(dt);
		}
		tick++;
	}
}