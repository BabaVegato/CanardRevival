package states;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import handlers.MonContactListener;
import core.Jeu;
import elements.Ak47;
import elements.Canard;
import elements.Decors;
import elements.Epee;
import elements.Mur;
import elements.Objet;
import elements.Projectile;
import elements.Sol;


public class PlayScreen implements Screen{


	public short BITGROUND = 4;
	public short BITPLAYER = 2;
	public short BITOBJET = 8;
	private final Jeu game;
	public Stage stage;
	private SpriteBatch batch;
	private OrthographicCamera cam;
	private World Monde;
	private Box2DDebugRenderer debugR;
	private Canard canardJ1;
	private Canard canardJ2;
	private Sol sol;
	private Mur mur;
	public ArrayList<Objet> objets;
	public ArrayList<Projectile> projectiles;
	public ArrayList<Decors> decors;
	private MonContactListener contList;
	private Music Bgm;
	private Sound Jump;
	private Sound hurt;
	private int ScoreJ2;
	private int ScoreJ1;
	private String score;
	private BitmapFont font = new BitmapFont();
	private int ParaVie;
	private int randposAk;

	public PlayScreen(final Jeu game, int ScoreJ1, int ScoreJ2, int ParaVie) {
		this.ScoreJ1 = ScoreJ1;
		this.ScoreJ2 = ScoreJ2;
		this.score = ScoreJ1 + " - " + ScoreJ2;
		this.ParaVie = ParaVie;
		
		randposAk = ThreadLocalRandom.current().nextInt(-100, 100 + 1);
		
		Monde = new World(new Vector2(0, -20.81f), true);
		
		this.stage = new Stage(new FitViewport(game.V_width, game.V_height, Jeu.cam));
		
		Jump = game.assets.get("Assets/Jumpsound.wav");
		Bgm = game.assets.get("Assets/MarioDubstep.mp3");
		Bgm.setVolume(0.01f);
		hurt = game.assets.get("Assets/Hurtsound.wav");
		
		batch = new SpriteBatch();
		debugR = new Box2DDebugRenderer();
		this.game = game;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 400, 400);
		
		contList = new MonContactListener();
		Monde.setContactListener(contList);
		
		decors = new ArrayList<Decors>();
		
		sol = new Sol(game, Monde, this, 100, 35, 250, 200);
		decors.add(sol);
		
		mur = new Mur(game, Monde, this, 10, 10, 300, 350);
		decors.add(mur);
		mur = new Mur(game, Monde, this, 10, 10, 300, 245);
		decors.add(mur);
		
		objets = new ArrayList<Objet>();
		objets.add(new Ak47(game, Monde, this, 8, 5, 250+randposAk, 280));
		objets.add(new Epee(game, Monde, this, 10, 10, 200, 280));
		canardJ1 = new Canard(game, Monde, this, 13, 15, 150, 400, 1, ParaVie);
		canardJ2 = new Canard(game, Monde, this, 13, 15, 300, 400, 2, ParaVie);
		
		projectiles = new ArrayList<Projectile>();
		
	}

	public void dispose() {
		Monde.dispose();
		batch.dispose();
		stage.dispose();
		debugR.dispose();
	}

	public void hide() {
		
	}

	public void pause() {

		
	}

	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		cam.update();
		game.batch.setProjectionMatrix(cam.combined);
		
		
		game.batch.begin();
		
		for(int i = 0; i<objets.size(); i++) {
			objets.get(i).render(game.batch);
		}
		for(int i = 0; i<projectiles.size(); i++) {
			projectiles.get(i).render(game.batch);
		}
		for(int i = 0; i<decors.size(); i++) {
			decors.get(i).render(game.batch);
		}
		
		canardJ1.render(game.batch);
		canardJ2.render(game.batch);
		
		font.draw(game.batch, score, 100, 100);
		
		game.batch.end();
		
		
		debugR.render(Monde, cam.combined);
		
		stage.act(delta);
		stage.draw();
		
		
		Monde.step(1/20f, 6, 2);
		
		handleInput(delta);
		
		for(int i = 0; i<projectiles.size(); i++) {
			projectiles.get(i).update();
		}
	}


	public void resize(int width, int height) {
	}


	public void resume() {
		
	}

	public void show() {
		Bgm.play();
	}
	
	public void handleInput(float dt){
		float x1 = canardJ1.body.getLinearVelocity().x;
		float y1 = canardJ1.body.getLinearVelocity().y;
        if(canardJ1.state != "Mort") {
        	
        	//input Canard 1
            if ( (Gdx.input.isKeyJustPressed(Input.Keys.Z)) && contList.canard1OnGround) {
                y1+=300;
            	Jump.play();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D) && canardJ1.body.getLinearVelocity().x <= 20f) {
            	x1+=10;
                canardJ1.body.setLinearVelocity(new Vector2(x1, y1));
                if(!canardJ1.Droite) {
            		canardJ1.Droite = true;
            		canardJ1.setAnimation();
            	}
            }
            if (Gdx.input.isKeyPressed(Input.Keys.Q) && canardJ1.body.getLinearVelocity().x >= -20) {
            	x1-=10;
            	canardJ1.body.setLinearVelocity(new Vector2(x1, y1));
            	if(canardJ1.Droite) {
            		canardJ1.Droite = false;
            		canardJ1.setAnimation();
            	}
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                canardJ1.fire();
            }
            //events  Canard 1
            if(contList.canard1OnGround) {
            	x1 -= x1/4;
            	canardJ1.body.setLinearVelocity(new Vector2(x1, y1));
            }
            for(int i=0; i<objets.size(); i++) {
            	if(objets.get(i).getClass() == Epee.class && contList.canard1Epee) {
            		objets.get(i).tenu(canardJ1);
            		if(Gdx.input.isKeyJustPressed(Input.Keys.F)) objets.get(i).action();
            	}
            	if(objets.get(i).getClass() == Ak47.class && contList.canard1Ak) {
            		objets.get(i).tenu(canardJ1);
            		if(Gdx.input.isKeyJustPressed(Input.Keys.F)) objets.get(i).action();
            	}
           }
           if(contList.canard1Bullet) {
        	   canardJ1.Vie -= 10;
        	   hurt.play();
        	   contList.canard1Bullet = false;
        	   System.out.println("Vie de canard 1 : " + canardJ1.Vie);
           }
           if(canardJ1.body.getPosition().y +30 < 0) canardJ1.Vie = 0;
       } 	
        
        
        float x2 = canardJ2.body.getLinearVelocity().x;
		float y2 = canardJ2.body.getLinearVelocity().y;
        if(canardJ2.state != "Mort") {
        	
        	//input Canard 2
            if ( (Gdx.input.isKeyJustPressed(Input.Keys.UP)) && contList.canard2OnGround) {
                y2+=300;
            	Jump.play();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && canardJ2.body.getLinearVelocity().x <= 20f) {
            	x2+=10;
                canardJ2.body.setLinearVelocity(new Vector2(x2, y2));
                if(!canardJ2.Droite) {
            		canardJ2.Droite = true;
            		canardJ2.setAnimation();
            	}
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && canardJ2.body.getLinearVelocity().x >= -20) {
            	x2-=10;
            	canardJ2.body.setLinearVelocity(new Vector2(x2, y2));
            	if(canardJ2.Droite) {
            		canardJ2.Droite = false;
            		canardJ2.setAnimation();
            	}
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                canardJ2.fire();
            }
            //events Canard 2
            if(contList.canard2OnGround) {
            	x2 -= x2/4;
            	canardJ2.body.setLinearVelocity(new Vector2(x2, y2));
            }
            for(int i=0; i<objets.size(); i++) {
            	if(objets.get(i).getClass() == Epee.class && contList.canard2Epee) {
            		objets.get(i).tenu(canardJ2);
            		if(Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_0)) objets.get(i).action();
            	}
            	if(objets.get(i).getClass() == Ak47.class && contList.canard2Ak) {
            		objets.get(i).tenu(canardJ2);
            		if(Gdx.input.isKeyJustPressed(Input.Keys.NUMPAD_0)) objets.get(i).action();
            	}
           }
           if(contList.canard2Bullet) {
        	   canardJ2.Vie -= 10;
        	   contList.canard2Bullet = false;
        	   hurt.play();
        	   System.out.println("Vie de canard 2 : " + canardJ2.Vie);
           }
           if(canardJ2.body.getPosition().y +30 < 0) canardJ2.Vie = 0;
           //fin de game
           if(canardJ1.Vie <= 0 ) {
        	   System.out.println("CanardJ2 a gagné !");
        	   ScoreJ2 += 1;
        	   game.setScreen(new PlayScreen(game, ScoreJ1, ScoreJ2, ParaVie));
           }
           if(canardJ2.Vie <= 0 ) {
        	   System.out.println("CanardJ1 a gagné !");
        	   ScoreJ1 += 1;
        	   game.setScreen(new PlayScreen(game, ScoreJ1, ScoreJ2, ParaVie));
           }
           if(ScoreJ1 > 5 || ScoreJ2 > 5) {
        	   ScoreJ1 = 0;
        	   ScoreJ2 = 0;
        	   game.setScreen(new MenuScreen(game, ParaVie));
           }
       }
   }
}