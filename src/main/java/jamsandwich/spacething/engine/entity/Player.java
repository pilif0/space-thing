package jamsandwich.spacething.engine.entity;

import org.lwjgl.input.Keyboard;

import jamsandwich.spacething.engine.event.KeyHandler;
import jamsandwich.spacething.engine.event.KeyListener;

public class Player extends GravitationalBody implements KeyListener{

	static final double radius = 10;
	static final double mass = 10;
	static final float[] colour = {0,0,1};

	int timer = 0;
	int fuel = 0;
	public boolean started = false;
	final double speed = 1000;
	
	public Player(double x, double y) {
		super(x,y,radius,mass,colour);
		KeyHandler.addKeyListener(this);
	}

	@Override
	public void keyPressed(int key) {
		keys[key] = true;
	}

	@Override
	public void keyRelease(int key) {
		keys[key] = false;
	}
	
	@Override
	public void tick() {
		if(timer>0)timer--;
	}
	
	@Override
	public void keyHeld() {
		if(keys[Keyboard.KEY_W]) {
			addForce(0,-speed);
		}
		if(keys[Keyboard.KEY_A]) {
			addForce(-speed,0);
		}
		if(keys[Keyboard.KEY_S]) {
			addForce(0,speed);
		}
		if(keys[Keyboard.KEY_D]) {
			addForce(speed,0);
		}
	}

	@Override
	public void mousePressed(int button) {
		buttons[button] = true;
	}
	
	@Override
	public void mouseReleased(int button) {
		buttons[button] = false;
	}

	@Override
	public void mouseHeld() {
	}
}