package jamsandwich.spacething.engine.event;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class KeyHandler{

	static ArrayList<KeyListener> listeners = new ArrayList<KeyListener>();

	public static void clear() {
		listeners.clear();
	}

	public static void addKeyListener(KeyListener l) {
		listeners.add(l);
	}

	public static void tick() {
		for(KeyListener s : listeners) {
			while(Keyboard.next()) {
				if(Keyboard.getEventKeyState()) {
					((KeyListener)s).keyPressed(Keyboard.getEventKey());
				} else {
					((KeyListener)s).keyRelease(Keyboard.getEventKey());
				}
			}
			((KeyListener)s).keyHeld();
			while(Mouse.next()) {
				if(Mouse.getEventButton()>=0){
					if(Mouse.getEventButtonState()) {
						((KeyListener)s).mousePressed(Mouse.getEventButton());
					} else {
						((KeyListener)s).mouseReleased(Mouse.getEventButton());
					}
				}
			}
			((KeyListener)s).mouseHeld();
		}
	}	
}
