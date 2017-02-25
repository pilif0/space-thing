package jamsandwich.spacething.engine.physics;

import java.math.BigDecimal;
import java.util.ArrayList;

import jamsandwich.spacething.engine.entity.Entity;
import jamsandwich.spacething.engine.entity.GravitationalBody;
import jamsandwich.spacething.engine.render.Sprite;
import jamsandwich.spacething.engine.utils.Config;
import jamsandwich.spacething.engine.utils.Utils;

public class GravityEngine extends PhysicsEngine{

	double gravitationalConstant = Config.getFloat("gravity");

	ArrayList<GravitationalBody> gravity = new ArrayList<GravitationalBody>();

	public GravityEngine(int p) {
		super(p);
	}

	public GravitationalBody register(GravitationalBody e) {
		gravity.add(e);
		return e;
	}
	
	@Override
	public void tickSprite(int tick, Sprite s) {
		if(s instanceof Entity) {
			for(GravitationalBody g : gravity) {
				if(g != s) {
					double x = g.centroid()[0]-s.centroid()[0];
					double y = g.centroid()[1]-s.centroid()[1];
					BigDecimal distance = Utils.distance(g.centroid(), s.centroid());
					distance = BigDecimal.valueOf(1).divide(distance, 200, Utils.ROUND_HALF_UP);
					BigDecimal[] v = Utils.normalize(new double[] {x,y});
					v[0] = (v[0].multiply(distance.multiply(BigDecimal.valueOf((gravitationalConstant*g.mass*((Entity)s).mass))).multiply(distance).multiply(distance)));
					v[1] = (v[1].multiply(distance.multiply(BigDecimal.valueOf((gravitationalConstant*g.mass*((Entity)s).mass))).multiply(distance).multiply(distance)));
					v[0] = v[0].compareTo(BigDecimal.valueOf(100))>0?BigDecimal.valueOf(100):v[0];
					v[1] = v[1].compareTo(BigDecimal.valueOf(100))>0?BigDecimal.valueOf(100):v[1];
					((Entity)s).applyForce(v[0].doubleValue(), BigDecimal.valueOf(-1).multiply(v[1]).doubleValue(), 0, 0);
				}
			}
		}
	}
}