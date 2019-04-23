package core;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Desktop {

	public static void main(String args[]) {
		Jeu game = new Jeu();
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.height = game.V_height;
		cfg.width = game.V_width;
		cfg.resizable = false;
		cfg.title = game.title;
		@SuppressWarnings("unused")
		LwjglApplication app = new LwjglApplication(game, cfg);		
	}
}