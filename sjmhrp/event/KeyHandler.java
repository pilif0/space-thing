package sjmhrp.event;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import sjmhrp.physics.PhysicsEngine;
import sjmhrp.render.Sprite;

public class KeyHandler extends PhysicsEngine{

	public KeyHandler(int p) {
		super(p);
	}

	@Override
	public Sprite tickSprite(int tick, Sprite s) {
		if(s instanceof KeyListener) {
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
		return s;
	}	
}
