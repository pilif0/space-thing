package jamsandwich.spacething.engine.utils;

import java.io.FileInputStream;
import java.io.InputStream;

public class Config {

	static final String path = "";
	
	public static double velocityConstant = 100;
	public static double gravitationalConstant = 50000;
	public static double maxCollisionForce = 99999999999999d;
	
	public static void readConfig() {
		try {
			InputStream stream = new FileInputStream(path);
			stream.close();
		} catch (Exception e) {
			Log.printError(e);
		}
	}
	
}
