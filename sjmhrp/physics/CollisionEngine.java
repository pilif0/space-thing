package sjmhrp.physics;

import sjmhrp.core.entity.Circle;
import sjmhrp.core.entity.Entity;
import sjmhrp.render.RenderRegistry;
import sjmhrp.render.Sprite;

public class CollisionEngine extends PhysicsEngine{

	double force = 100;
	
	public CollisionEngine(int p) {
		super(p);
	}

	@Override
	public Sprite tickSprite(int tick, Sprite s1) {
		if(s1 instanceof Circle) {
			for(Entity s2 : RenderRegistry.entityRegistry) {
				if(s2 instanceof Circle) {
					Circle c1 = (Circle) s1;
					Circle c2 = (Circle) s2;
					double d = c1.getRadius()+c2.getRadius();
					d*=d;
					double[] dir = new double[2];
					dir[0] = c1.getCenter()[0]-c2.getCenter()[0];
					dir[1] = c1.getCenter()[1]-c2.getCenter()[1];
					dir[0]*=dir[0];
					dir[1]*=dir[1];
					System.out.println(dir[0]*dir[0]+dir[1]*dir[1]>d);
				}
			}
		}
		return s1;
	}
}