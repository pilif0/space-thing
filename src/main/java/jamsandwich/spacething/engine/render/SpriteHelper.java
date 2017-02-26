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

import jamsandwich.spacething.engine.entity.GravitationalBody;

public class SpriteHelper {
		
	public static int[] shapes = {GL_POINTS, GL_LINES, GL_TRIANGLES, GL_QUADS, GL_TRIANGLE_STRIP, GL_TRIANGLE_FAN, GL_POLYGON};
	public static int[] fills = {GL_POINT, GL_LINE, GL_FILL};
	
	public static void newText(String label, String text, int size, int x, int y) {
		RenderRegistry.registerText(new Text(label, text, "Times New Roman", size, x, y));
	}
	
	public static GravitationalBody newAsteroid(double x, double y, double r, double mass) {
		return new GravitationalBody(x,y,r,mass,new float[] {1,0,0});
	}
	
	public static void rotateAll(double angle) {
		for(Sprite s : RenderRegistry.spriteRegistry) {
			s.getModel().rotate(angle);
		}
	}
}