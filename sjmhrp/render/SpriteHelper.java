package sjmhrp.render;

import static org.lwjgl.opengl.GL11.GL_FILL;
import static org.lwjgl.opengl.GL11.GL_LINE;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_POINT;
import static org.lwjgl.opengl.GL11.GL_POINTS;
import static org.lwjgl.opengl.GL11.GL_POLYGON;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_TRIANGLE_FAN;
import static org.lwjgl.opengl.GL11.GL_TRIANGLE_STRIP;
import static sjmhrp.utils.Utils.toRadians;

import java.awt.Polygon;

import sjmhrp.core.entity.Circle;
import sjmhrp.core.entity.Entity;
import sjmhrp.core.entity.GravitationalBody;
import sjmhrp.core.entity.LineSegment;

public class SpriteHelper {
		
	public static int[] shapes = {GL_POINTS, GL_LINES, GL_TRIANGLES, GL_QUADS, GL_TRIANGLE_STRIP, GL_TRIANGLE_FAN, GL_POLYGON};
	public static int[] fills = {GL_POINT, GL_LINE, GL_FILL};
	
	public static void newText(String label, String text, int size, int x, int y) {
		RenderRegistry.registerText(new Text(label, text, "Times New Roman", size, x, y));
	}

	public static Entity newSquare(String n, double x, double y, double width, double height, float[] colour, int layer) {
		return new Entity(n, 2, 3, colour, new double[][] {{x,y},{x+width,y},{x+width,y+height},{x,y+height}}, layer,2);
	}
	
	public static Sprite newRightTriangle(String name, double x, double y, double width, double height, double angle, float[] colour, int layer) {
		return new Sprite(name, 2, 2, colour, new double[][] {{x,y},{x+(width*Math.cos(toRadians(angle))),y+(width*Math.sin(toRadians(angle)))},{(int) (x+(height*Math.sin(toRadians(angle)))),(int) (y-(height*Math.cos(toRadians(angle))))}}, layer);
	}
	
	public static Sprite newArc(double x, double y, double radius, double angle, float[] colour, int layer) {
		double[][] v = new double[361][2];
		v[0][0] = x;
		v[0][1] = y;
		for(int i = 0; i < 360; i++) {
			v[i+1][0] = x+(radius*Math.cos(toRadians(i*angle/359)));
			v[i+1][1] = y-(radius*Math.sin(toRadians(i*angle/359)));
		}
		return new Sprite("", 2, 6, colour, v, layer);
	}
	
	public static Circle newCircle(double x, double y, double r, double mass, float[] colour) {
		return new Circle(x,y,r,mass,colour);
	}
	
	public static Sprite newMarker(double x, double y, int type) {
		double[][] p = new double[360][2];
		for(int i = 0; i < 360; i++) {
			p[i][0] = x+(40*Math.cos(toRadians(i)));
			p[i][1] = y+(40*Math.sin(toRadians(i)));
		}
		return new Sprite(type==0?"Start":"End", 2, 6, new float[] {1,0,1}, p, 0);
	}
	
	public static GravitationalBody newBlackHole(double x, double y, double r, double mass) {
		return new GravitationalBody(x,y,r,mass,new float[] {1,0,0});
	}

	public static LineSegment newLineSeg(float[] c) {
		return new LineSegment(c);
	}
	
	public static Sprite newLine(double[]u , double[] v, float[] c) {
		return new Sprite("", 2, 1, c, new double[][]{u,v}, 0);
	}
	
	public static void rotateAll(double angle) {
		for(Sprite s : RenderRegistry.spriteRegistry) {
			s.rotate(angle);
		}
	}
	
	public static boolean collides(Sprite a, Sprite b) {
		boolean collides1 = false;
		int[] xs = new int[a.vertices.length];
		int[] ys = new int[a.vertices.length];
		for(int i = 0; i < a.vertices.length; i++) {
			xs[i] = (int)a.vertices[i][0];
			ys[i] = (int)a.vertices[i][1];
		}
		Polygon p = new Polygon(xs,ys, a.vertices.length);
		for(double[] v : b.vertices) {
			if(p.contains(v[0], v[1])) {
				collides1 = a!=b;
			}
		}
		boolean collides2 = false;
		xs = new int[b.vertices.length];
		ys = new int[b.vertices.length];
		for(int i = 0; i < b.vertices.length; i++) {
			xs[i] = (int)b.vertices[i][0];
			ys[i] = (int)b.vertices[i][1];
		}
		p = new Polygon(xs,ys, b.vertices.length);
		for(double[] v : a.vertices) {
			if(p.contains(v[0], v[1])) {
				collides2 = a!=b;
			}
		}
		return collides1||collides2;
	}
}
