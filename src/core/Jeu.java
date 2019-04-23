package core;



import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import states.LoadingScreen;

public class Jeu extends Game{

	public BitmapFont font1;
	public final int V_height = 800;
	public final int V_width = 800;
	public final String title = "CanardJeu 9.0";
	public AssetManager assets;
	public static Camera cam;

	public SpriteBatch batch;
	public BitmapFont font;

	public void create() {
		cam = new OrthographicCamera();
		assets = new AssetManager();
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new LoadingScreen(this));
		this.font = new BitmapFont();
		this.batch = new SpriteBatch();
		
	}

	public void render() {
		super.render();
	}
	
	public void dispose() {
		batch.dispose();
		font.dispose();
		assets.dispose();
		this.getScreen().dispose();
	}
}