package jamsandwich.spacething;

import jamsandwich.spacething.engine.core.CoreMain;

import java.io.File;

public class Main {
    public static void main(final String[] args) {
        System.setProperty("org.lwjgl.librarypath", new File("build/natives").getAbsolutePath());

        CoreMain.coreMain(args);
    }
}
