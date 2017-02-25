package jamsandwich.spacething;

import java.io.File;

import jamsandwich.spacething.engine.core.CoreMain;

public class Main {
    public static void main(final String[] args) {
        System.setProperty("org.lwjgl.librarypath", new File("build/natives").getAbsolutePath());

        CoreMain.coreMain(args);
    }
}
