package com.corviolis.gorge;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Gorge");

		Settings settings = new Settings();
		settings.maxWidth = 2048;
		settings.maxHeight = 2048;
		TexturePacker.process(settings, "images", "packed-images", "textures_packed");

		new Lwjgl3Application(new Gorge(), config);
	}
}
