package jamsandwich.spacething.engine.render;

import java.util.ArrayList;

import jamsandwich.spacething.engine.core.entity.Entity;

public class RenderRegistry {
	
	public static ArrayList<Sprite> spriteRegistry = new ArrayList<Sprite>();
	public static ArrayList<Entity> entityRegistry = new ArrayList<Entity>();
	public static ArrayList<Text> textRegistry = new ArrayList<Text>();
	
	public static void clear() {
		spriteRegistry.clear();
		textRegistry.clear();
	}
	
	public static void registerSprite(Sprite s) {
		spriteRegistry.add(s);
	}
	
	public static void registerText(Text t) {
		textRegistry.add(t);
	}
	
	public static void registerEntity(Entity e) {
		entityRegistry.add(e);
	}
	
	public static Sprite getSprite(String name) {
		for(Sprite s : spriteRegistry) {
			if(s.name.equals(name)) {
				return s;
			}
		}
		return null;
	}
	
	public static Text getText(String text) {
		for(Text t : textRegistry) {
			if(t.label.equals(text)) {
				return t;
			}
		}
		return null;
	}
	
	public static void removeSprite(String sprite) {
		for(int i = 0; i < spriteRegistry.size(); i++) {
			if(spriteRegistry.get(i).name.equals(sprite)) {
				spriteRegistry.remove(i);
			}
		}
	}
}
