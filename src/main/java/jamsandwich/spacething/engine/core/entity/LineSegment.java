package jamsandwich.spacething.engine.core.entity;

import jamsandwich.spacething.engine.render.RenderRegistry;
import jamsandwich.spacething.engine.render.Sprite;
import jamsandwich.spacething.engine.utils.Utils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

public class LineSegment extends Sprite {

	public LineSegment(float[] c) {
		super("LineSeg", 2, 1, c, new double[][]{{0,0},{0,0}}, 0);
	}

	@Override
	public void tick() {
		Player p = (Player) RenderRegistry.getSprite("Player");
		double[] c = p.centroid();
		double[] v = {Mouse.getX()-c[0],(Display.getHeight()-Mouse.getY())-c[1]};
		if(Utils.magnitude(v).doubleValue()>400) {
			v = Utils.toDoubles(Utils.normalize(v));
			v[0] *= 400d;
			v[1] *= 400d;
		}
		v[0]+=c[0];
		v[1]+=c[1];
		vertices = new double[][] {c,v};
	}
}
