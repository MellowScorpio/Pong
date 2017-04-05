package com.amilek.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.amilek.main.Pong;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Pong";
		config.width = Pong.WIDTH;
		config.height = Pong.HEIGHT;
		config.useGL30 = false;
		config.resizable = false;
		new LwjglApplication(new Pong(), config);
	}
}
