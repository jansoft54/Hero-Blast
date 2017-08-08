package com.shooterman.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.shooterman.game.MainClass.ShooterMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = (int) ShooterMain.WIDTH;
		config.height = (int) ShooterMain.HEIGHT;
		new LwjglApplication(new ShooterMain(), config);
	}
}
