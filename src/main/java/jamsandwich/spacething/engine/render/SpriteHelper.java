package jamsandwich.spacething.engine.render;

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

import jamsandwich.spacething.engine.entity.Circle;
import jamsandwich.spacething.engine.entity.Entity;
import jamsandwich.spacething.engine.entity.GravitationalBody;

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
		return new Sprite(name, 2, 2, colour, new double[][] {{x,y},{x+(width*Math.cos(Math.toRadians(angle))),y+(width*Math.sin(Math.toRadians(angle)))},{(int) (x+(height*Math.sin(Math.toRadians(angle)))),(int) (y-(height*Math.cos(Math.toRadians(angle))))}}, layer);
	}
	
	public static Sprite newArc(double x, double y, double radius, double angle, float[] colour, int layer) {
		double[][] v = new double[361][2];
		v[0][0] = x;
		v[0][1] = y;
		for(int i = 0; i < 360; i++) {
			v[i+1][0] = x+(radius*Math.cos(Math.toRadians(i*angle/359)));
			v[i+1][1] = y-(radius*Math.sin(Math.toRadians(i*angle/359)));
		}
		return new Sprite("", 2, 6, colour, v, layer);
	}
	
	public static Circle newCircle(double x, double y, double r, double mass, float[] colour) {
		return new Circle(x,y,r,mass,colour);
	}
	
	public static Sprite newMarker(double x, double y, int type) {
		double[][] p = new double[360][2];
		for(int i = 0; i < 360; i++) {
			p[i][0] = x+(40*Math.cos(Math.toRadians(i)));
			p[i][1] = y+(40*Math.sin(Math.toRadians(i)));
		}
		return new Sprite(type==0?"Start":"End", 2, 6, new float[] {1,0,1}, p, 0);
	}
	
	public static GravitationalBody newAsteroid(double x, double y, double r, double mass) {
		return new GravitationalBody(x,y,r,mass,new float[] {1,0,0});
	}
	
	public static void rotateAll(double angle) {
		for(Sprite s : RenderRegistry.spriteRegistry) {
			s.rotate(angle);
		}
	}
}