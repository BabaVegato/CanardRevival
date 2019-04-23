package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import core.Jeu;

public class LoadingScreen implements Screen{

	private ShapeRenderer sr;
	private final Jeu jeu;
	private float progress;
	
	public LoadingScreen(final Jeu jeu) {
		this.jeu = jeu;
		this.sr = new ShapeRenderer();
		queueasset();
	}
	public void dispose() {
		sr.dispose();
	}

	public void hide() {
		
	}

	public void pause() {
		
	}
	private void update(float delta) {
		progress = MathUtils.lerp(progress, jeu.assets.getProgress(), .1f);
        if (jeu.assets.update() && progress >= jeu.assets.getProgress() - .001f) {
        	jeu.setScreen(new PlayScreen(jeu, 0, 0, 1));
        	
        }
	}

	public void render(float delta) {
		Gdx.gl.glClearColor(0.25f, 0.25f, 0.25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		update(delta);
		
		jeu.batch.begin();
		jeu.font.draw(jeu.batch, "Loading...", 20, 20);
		jeu.batch.end();
	}

	public void resize(int width, int height) {
		
	}

	public void resume() {
		
	}

	public void show() {
		this.progress = 0f;
		
	}
	public void queueasset() {
		jeu.assets.load("Assets/ParaScreenTitre.png", Texture.class);
		jeu.assets.load("Assets\\BouttonPara.png", Texture.class);
		jeu.assets.load("Assets\\BouttonParaHover.png", Texture.class);
		jeu.assets.load("Assets\\sol.png", Texture.class);
		jeu.assets.load("Assets\\mur.png", Texture.class);
		jeu.assets.load("Assets\\CanWar_Titre.png", Texture.class);
		jeu.assets.load("Assets\\canardD.png", Texture.class);
		jeu.assets.load("Assets\\canardD2.png", Texture.class);
		jeu.assets.load("Assets\\canardD3.png", Texture.class);
		jeu.assets.load("Assets\\canardG.png", Texture.class);
		jeu.assets.load("Assets\\canardG2.png", Texture.class);
		jeu.assets.load("Assets\\canardG3.png", Texture.class);
		jeu.assets.load("Assets\\epeeD.png", Texture.class);
		jeu.assets.load("Assets\\epeeG.png", Texture.class);
		jeu.assets.load("Assets\\Ak47D.png", Texture.class);
		jeu.assets.load("Assets\\Ak47G.png", Texture.class);
		jeu.assets.load("Assets\\BulletD.png", Texture.class);
		jeu.assets.load("Assets\\BulletG.png", Texture.class);
		jeu.assets.load("Assets\\BouttonJouer.png", Texture.class);
		jeu.assets.load("Assets\\BouttonJouerHover.png", Texture.class);
		jeu.assets.load("Assets\\Menumusic.wav", Music.class);
		jeu.assets.load("Assets\\Introsound.wav", Music.class);
		jeu.assets.load("Assets\\MarioDubstep.mp3", Music.class);
		jeu.assets.load("Assets\\Jumpsound.wav", Sound.class);
		jeu.assets.load("Assets\\Explosound.wav", Sound.class);
		jeu.assets.load("Assets\\ButtonParaSound.wav", Sound.class);
		jeu.assets.load("Assets\\Quack.mp3", Sound.class);
		jeu.assets.load("Assets\\Ak47Shotsound.wav", Sound.class);
		jeu.assets.load("Assets\\Ak47Shotsound2.wav", Sound.class);
		jeu.assets.load("Assets\\Ak47Shotsound3.wav", Sound.class);
		jeu.assets.load("Assets\\Hurtsound.wav", Sound.class);
		jeu.assets.load("Assets\\UI\\uiskin.atlas", TextureAtlas.class);
		
		jeu.assets.finishLoading();
	}
}
