package jamsandwich.spacething.engine.physics;

import java.util.ArrayList;

import jamsandwich.spacething.engine.entity.Circle;
import jamsandwich.spacething.engine.entity.Entity;
import jamsandwich.spacething.engine.render.RenderRegistry;
import jamsandwich.spacething.engine.render.Sprite;

public class CollisionEngine extends PhysicsEngine{

	double force = 100;
	ArrayList<Sprite> checked = new ArrayList<Sprite>();
	
	public CollisionEngine(int p) {
		super(p);
		checked.clear();
	}

	@Override
	void tick(int tick) {
		super.tick(tick);
		checked.clear();
	}
	
	@Override
	public void tickSprite(int tick, Sprite s1) {
		if(s1 instanceof Circle) {
			checked.add(s1);
			for(Entity s2 : RenderRegistry.entityRegistry) {
				if(s2 instanceof Circle && s1!=s2 && !checked.contains(s2)) {
					Circle c1 = (Circle) s1;
					Circle c2 = (Circle) s2;
					double d = c1.getRadius()+c2.getRadius();
					d*=d;
					double[] dir = new double[2];
					dir[0] = c1.getCenter()[0]-c2.getCenter()[0];
					dir[1] = c1.getCenter()[1]-c2.getCenter()[1];
					double ls = dir[0]*dir[0]+dir[1]*dir[1];
					if(ls==0||ls<d){
						double m1 = c1.mass;
						double m2 = c2.mass;
						double[] uv1 = c1.velocity;
						double[] uv2 = c2.velocity;
						double magnitude = Math.sqrt(ls);
						dir[0]/=magnitude;
						dir[1]/=magnitude;
						double u1 = uv1[0]*dir[0]+uv1[1]*dir[1];
						double u2 = uv2[0]*dir[0]+uv2[1]*dir[1];
						double v1 = (u1*(m1-m2)+2*m2*u2)/(m1+m2);
						double v2 = (u2*(m2-m1)+2*m1*u1)/(m1+m2);
						c1.velocity[0]=v1*dir[0];
						c1.velocity[1]=v1*dir[1];
						c2.velocity[0]=v2*dir[0];
						c2.velocity[1]=v2*dir[1];
					}
				}
			}
		}
	}
}