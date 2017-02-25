package jamsandwich.spacething.engine.render;

public class Text {
	
	String label;
	String text;
	String font;
	int size;
	int[] pos = new int[2];
	
	public Text(String l, String t, String f, int s, int x, int y) {
		label = l;
		text = t;
		font = f;
		size = s;
		pos[0] = x;
		pos[1] = y;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String t) {
		text = t;
	}
}
