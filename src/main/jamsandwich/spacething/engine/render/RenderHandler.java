package jamsandwich.spacething.engine.render;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_FRONT_AND_BACK;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glDepthMask;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLineWidth;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glPolygonMode;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.awt.Font;
import java.util.HashMap;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.TextureImpl;

import jamsandwich.spacething.engine.utils.Log;

public class RenderHandler {
		
	static HashMap<String, TrueTypeFont> fonts = new HashMap<String, TrueTypeFont>();
	
	public static void init(String t, int[] i) {
		try{
		Log.print("Creating Display");
		Display.setTitle(t);
		Display.setResizable(false);
		Display.setDisplayMode(new DisplayMode(i[0],i[1]));
		Display.setVSyncEnabled(true);
		Display.setFullscreen(false);
		Display.create();
		Log.done();
		Log.print("Initializing Fonts");
		initFonts();
		Log.done();
		glDisable(GL_DEPTH_TEST);
		glDepthMask(false);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glClearColor(0f, 0f, 0f, 0f);
		} catch(Exception e) {
			Log.printError(e);
		}
	}
	
	static void initFonts() {
		fonts.put("Times New Roman;24", new TrueTypeFont(new Font("Times New Roman", Font.BOLD, 24), false));
		fonts.put("Times New Roman;48", new TrueTypeFont(new Font("Times New Roman", Font.BOLD, 48), false));
	}
	
	public static void render() {
		glClear(GL_COLOR_BUFFER_BIT);
		for(Sprite s : RenderRegistry.spriteRegistry) {
			renderSprite(s);
		}
		TextureImpl.bindNone();
		for(Text t : RenderRegistry.textRegistry) {
			renderText(t);
		}
		glBindTexture(GL_TEXTURE_2D, 0);
		Display.update();
	}
	
	static void renderSprite(Sprite s) {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glPolygonMode(GL_FRONT_AND_BACK, SpriteHelper.fills[s.fill]);
		glColor3f(s.colour[0], s.colour[1], s.colour[2]);
		glBegin(SpriteHelper.shapes[s.shape]);
		glLineWidth(1);
		for(int i = 0 ; i < s.vertices.length; i++) {
			glVertex2f((int)s.vertices[i][0], (int)s.vertices[i][1]);
		}
		glEnd();
	}
	
	static void renderText(Text t) {
		fonts.get(t.font + ";" + t.size).drawString(t.pos[0], t.pos[1], t.text);
	}
}