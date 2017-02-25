package sjmhrp.physics;

import java.util.ArrayList;

import sjmhrp.render.RenderRegistry;
import sjmhrp.render.Sprite;

public abstract class PhysicsEngine {

	int priority;
	
	public PhysicsEngine(int p) {
		priority = p;
	}
	
	ArrayList<Sprite> tick(int tick, ArrayList<Sprite> sprites) {
		ArrayList<Sprite> nextSprites = RenderRegistry.spriteRegistry;
		for(int i = 0; i < sprites.size(); i++) {
//			System.out.println(i+";"+sprites.size()+";"+RenderRegistry.spriteRegistry.size()+";"+nextSprites.size());
			Sprite s = sprites.get(i);
			nextSprites.set(i, tickSprite(tick, s));
		}
		return nextSprites;
	}

	public abstract Sprite tickSprite(int tick, Sprite s);
}
