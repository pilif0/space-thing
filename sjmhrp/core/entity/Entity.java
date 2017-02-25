package sjmhrp.core.entity;

import sjmhrp.render.RenderRegistry;
import sjmhrp.render.Sprite;
import sjmhrp.utils.Utils;

public class Entity extends Sprite {
	
	public double mass;
	
	public double[] velocity = {0, 0};
	public double[] acceleration = {0, 0};
	
	public double angularVel = 0;
	public double angularAccel = 0;
	
	
	public Entity(String n, int f, int s, float[] c, double[][] v, int l, double m) {
		super(n,f,s,c,v,l);
		mass = m;
		RenderRegistry.registerEntity(this);
	}
	
	public Entity setMass(double m) {
		mass = m;
		return this;
	}

	public Entity addVelocity(double x, double y) {
		velocity[0] += x;
		velocity[1] += y;
		return this;
	}
	
	public Entity addAcceleration(double x, double y) {
		acceleration[0] += x;
		acceleration[1] += y;
		return this;
	}
	
	public Entity applyForce(double a, double b, double x, double y) {
		addAcceleration(a/mass, b/mass);
		return this;
	}
	
	public Entity applyTorque(double x) {
		addAngularAccel(x);
		return this;
	}

	public double getMomentOfInertia() {
		double I = 0;
//		int i = 0;
		for(int i = 0; i < vertices.length; i++) {
			double[] p1 = vertices[i];
			double[] p2 = vertices[(i+1)%vertices.length];
			double[] p3 = centroid();
			double[] c1 = Utils.centre(new double[][] {p1,p2,p3});
			double x = Utils.distance(p1,p2).doubleValue();
			double b = Utils.distance(p2,p3).doubleValue();
			double d = Utils.distance(p1,p3).doubleValue();
			double a = ((d*d)+(b*b)-(x*x))/(2*b);
			double h = Math.sqrt((d*d)-(a*a));
//			double y = ((h*b*b*b)+(h*a*b*b)+(h*a*a*b)+(b*h*h*h))/12d;
			double y = ((b*h*h*h)+(b*b*b*h))/36d;
			double distance = Utils.distance(c1, p3).doubleValue();
			double z = mass/getArea();
//			Log.println(p2[0]+";"+p2[1]);
			I += (y*z) + (mass*distance*distance);
		}
		return I;
	}
	
	public Entity addAngularVel(double w) {
		angularVel += w;
		return this;
	}
	
	public Entity addAngularAccel(double a) {
		angularAccel += a;
		return this;
	}
}