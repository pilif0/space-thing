package jamsandwich.spacething.engine.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Config {

	static final String path = "config/Engine.cfg";

	static HashMap<String,String> config = new HashMap<String,String>();

	public static void read() {
		try {
			InputStreamReader in = new InputStreamReader(new FileInputStream(path));
			BufferedReader reader = new BufferedReader(in);
			String line = "";
			while(line!=null) {
				if(line.contains("=")&&line.split("=").length==2){
					String[] s = line.split("=");
					config.put(s[0], s[1]);
				}
				line = reader.readLine();
			}
			in.close();
		} catch (Exception e) {
			Log.printError(e);
		}
	}

	public static float getFloat(String key) {
		if(config.containsKey(key)) {
			return Float.parseFloat(config.get(key));
		}
		return 0;
	}
	
	public static int getInt(String key) {
		if(config.containsKey(key)) {
			return Integer.parseInt(config.get(key));
		}
		return 0;
	}	
	
	public static String get(String key) {
		return config.get(key);
	}
}