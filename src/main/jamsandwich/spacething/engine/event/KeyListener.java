package jamsandwich.spacething.engine.event;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public interface KeyListener {
	
	boolean[] keys = new boolean[Keyboard.KEYBOARD_SIZE];
	boolean[] buttons = new boolean[Mouse.getButtonCount()];
	
	void keyPressed(int key);

	void keyRelease(int key);
	
	void keyHeld();
	
	void mousePressed(int button);
	
	void mouseReleased(int button);
	
	void mouseHeld();
}
