package states;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.alpha;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;

import core.Jeu;

public class MenuScreen implements Screen{
	
	private Jeu game;
	public Texture KANARIMAGE;
	public SpriteBatch batch;
	private Stage stage;
	private Music Menumusic;
	private Sound Explosound;
	private Sound ButtonParaSound;
	private Sound Quack;
	private Image ImgCanarwar;
	private Image ImgBJ;
	private Image ImgBJH;
	private Image ImgBPara;
	private Image ImgBParaH;
	private Boolean PlayisHovered;
	private Boolean ParaisHovered;
	private Boolean Clicked;
	private int ParaVie;


	public MenuScreen(final Jeu game, int ParaVie) {
		this.ParaVie = ParaVie;
		Clicked = false;
		PlayisHovered = false;
		ParaisHovered = false;
		this.game = game;
		
		this.stage = new Stage(new FitViewport(game.V_width, game.V_height, Jeu.cam));
		Gdx.input.setInputProcessor(stage);
		
		Menumusic = game.assets.get("Assets/Menumusic.wav");
		
		Explosound = game.assets.get("Assets/Explosound.wav");
		ButtonParaSound = game.assets.get("Assets/ButtonParaSound.wav");
		Quack = game.assets.get("Assets/Quack.mp3");
		
		Texture Canarwar = game.assets.get("Assets/CanWar_Titre.png");
		ImgCanarwar = new Image(Canarwar);
		ImgCanarwar.scaleBy(5);
		
		Texture BouttonJouer = game.assets.get("Assets/BouttonJouer.png");
		Texture BouttonJouerHover = game.assets.get("Assets/BouttonJouerHover.png");
		ImgBJ = new Image(BouttonJouer);
		ImgBJ.setOrigin(BouttonJouer.getWidth()/2, BouttonJouer.getHeight()/2);
		ImgBJ.scaleBy(1);
		ImgBJH = new Image(BouttonJouerHover);
		ImgBJH.setOrigin(BouttonJouer.getWidth()/2, BouttonJouer.getHeight()/2);
		ImgBJH.scaleBy(1);

		Texture BouttonPara = game.assets.get("Assets/BouttonPara.png");
		Texture BouttonParaHover = game.assets.get("Assets/BouttonParaHover.png");
		ImgBPara = new Image(BouttonPara);
		ImgBPara.setOrigin(BouttonPara.getWidth()/2, BouttonPara.getHeight()/2);
		ImgBPara.scaleBy(1);
		ImgBParaH = new Image(BouttonParaHover);
		ImgBParaH.setOrigin(BouttonPara.getWidth()/2, BouttonPara.getHeight()/2);
		ImgBParaH.scaleBy(1);
		
		stage.addActor(ImgCanarwar);
		stage.addActor(ImgBJ);
		stage.addActor(ImgBJH);
		stage.addActor(ImgBPara);
		stage.addActor(ImgBParaH);
		
	}
	
	public void dispose() {
		stage.dispose();
		Menumusic.dispose();
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
		
		//Play
		if(isHovered(ImgBJ, ImgBJH, PlayisHovered)) {
			PlayisHovered = true;
		}
		else {
			PlayisHovered = false;
		}
		if(PlayisHovered && Gdx.input.isTouched()) {
			if(!Clicked) {
				Quack.play();
				Clicked = true;
				dispose();
				game.setScreen(new PlayScreen(game, 0, 0, ParaVie));
			}
		}
		//Para
		if(isHovered(ImgBPara, ImgBParaH, ParaisHovered)) {
			ParaisHovered = true;
		}
		else {
			ParaisHovered = false;
		}
		if(ParaisHovered && Gdx.input.isTouched()) {
			if(!Clicked) {
				Clicked = true;
				dispose();
				game.setScreen(new ParaScreen(game));
			}
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
		ImgBJH.setPosition(stage.getWidth()/2 - 230, stage.getHeight()/2 - 200);
		ImgBJH.addAction(alpha(0));
		
		ImgBJ.setPosition(stage.getWidth()/2 - 230, stage.getHeight()/2 - 200);
		ImgBJ.addAction(alpha(0.5f));
		ImgBJ.addAction(fadeIn(1f));
		
		ImgBParaH.setPosition(stage.getWidth()/2 + 110, stage.getHeight()/2 - 210);
		ImgBParaH.addAction(alpha(0));
		
		ImgBPara.setPosition(stage.getWidth()/2 + 110, stage.getHeight()/2 - 210);
		ImgBPara.addAction(alpha(0.5f));
		ImgBPara.addAction(fadeIn(1f));
		
		ImgCanarwar.setPosition(stage.getWidth()/2 - 200, stage.getHeight() - 600);
		ImgCanarwar.addAction(alpha(0.5f));
		ImgCanarwar.addAction(fadeIn(1f));
		Menumusic.play();
		Menumusic.setLooping(true);
		
	}
	public Boolean isHovered(Image Img1, Image Img2, Boolean estsurvole) {
		Vector2 mouseScreenPosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
		Vector2 mouseLocalPosition = Img1.screenToLocalCoordinates(mouseScreenPosition);
		
		if(Img1.hit(mouseLocalPosition.x, mouseLocalPosition.y, false) != null) {
			Img1.addAction(alpha(0));
			Img2.addAction(alpha(1));
			if(!estsurvole && Img1.equals(ImgBJ)) {
				Explosound.play();
				System.out.println("Explosound");
			}
			if(!estsurvole && Img1.equals(ImgBPara)) {
				ButtonParaSound.play();
				System.out.println("BouttonParaSound");
			}
			return true;
		}
		else{
			Img1.addAction(alpha(1));
			Img2.addAction(alpha(0));

			return false;
		}
	}

}
