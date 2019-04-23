package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import java.util.concurrent.TimeUnit;

import core.Jeu;

public class Intro implements Screen{
	
	private int ParaVie;
	private Jeu game;
	public Texture KANARIMAGE;
	public SpriteBatch batch;
	private Stage stage;
	private Image ImgLogo;
	private Music Intromusic;
	private Sound Jumpsound;
	private Image ImgCanarwar;
	private Boolean AnimationEnding;


	public Intro(final Jeu game) {
		ParaVie = 20;
		AnimationEnding = false;
		this.game = game;
		this.stage = new Stage(new FitViewport(game.V_width, game.V_height, Jeu.cam));
		Gdx.input.setInputProcessor(stage);
		
		Jumpsound = game.assets.get("Assets/Jumpsound.wav");
		Intromusic = game.assets.get("Assets/Introsound.wav");
		
		Texture Canarwar = game.assets.get("Assets/CanWar_Titre.png");
		ImgCanarwar = new Image(Canarwar);
		ImgCanarwar.scaleBy(5);
		
		Texture Logo = game.assets.get("Assets/canardG.png");
		ImgLogo = new Image(Logo);
		ImgLogo.scaleBy(8);
		
		stage.addActor(ImgCanarwar);
		stage.addActor(ImgLogo);
		
	}
	

	public void dispose() {
		stage.dispose();
	}

	public void hide() {
		
	}


	public void pause() {

		
	}

	public void render(float delta) {
		Gdx.gl.glClearColor(0.25f, 0.25f, 0.25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		update(delta);
		
		stage.draw();
		if (ImgCanarwar.getActions().size == 0 && AnimationEnding == true) {
			game.setScreen(new MenuScreen(game, ParaVie));
		}
	}
	public void update(float delta) {
		stage.act(delta);
	}


	public void resize(int width, int height) {
		stage.getViewport().update(width, height, false);
	}

	public void resume() {
		
	}

	public void show() {
		ImgCanarwar.setPosition(stage.getWidth()/2 - 200, stage.getHeight()/2 - 650);
		ImgCanarwar.addAction(alpha(0));
		ImgLogo.setPosition(stage.getWidth(), stage.getHeight() - 100);
		ImgLogo.addAction(  
				sequence(
						alpha(0) , 
						scaleTo(0.1f, 0.1f) ,
						parallel(fadeIn(1.5f, Interpolation.elastic) , 
								scaleTo(10, 10, 1.85f,  Interpolation.pow5) , 
								moveTo(stage.getWidth()/2 - 110, 
										stage.getHeight()/2 - 100 , 1.85f, Interpolation.exp5)),
						parallel(moveTo(stage.getWidth()/2 - 110, 
										stage.getHeight()/2 - 120 , 0.5f, Interpolation.exp10),	
								Actions.rotateBy(4f)),
						parallel(moveTo(stage.getWidth()/2 - 110, 
								stage.getHeight()/2 - 100 , 0.2f, Interpolation.exp10),	
								Actions.rotateBy(-4f))
								
						 ));
		ImgLogo.addAction(fadeIn(3f));
		Intromusic.play();
		Intromusic.setOnCompletionListener(new Music.OnCompletionListener() {
			
			public void onCompletion(Music arg0) {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Jumpsound.play(1f);
				ImgCanarwar.addAction(fadeIn(1f, Interpolation.pow2));
				
				ImgCanarwar.addAction(fadeOut(6, Interpolation.pow2));
				ImgLogo.addAction(fadeOut(6, Interpolation.pow2));
				AnimationEnding = true;
			}
		});
		
	}

}
