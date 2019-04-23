package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import core.Jeu;

public class ParaScreen implements Screen{

	private ShapeRenderer sr;
	private final Jeu game;
	private Skin skin;
	private Stage stage;
	private Texture Titre;
	private Image ImgTitre;
	private SelectBox<String> selbox;
	private TextButton butRetour;
	private String[] items;
	private int ParaVie;
	
	public ParaScreen(final Jeu game) {
		this.game = game;
		this.sr = new ShapeRenderer();
		
		skin = new Skin();
		skin.addRegions(game.assets.get("Assets/UI/uiskin.atlas", TextureAtlas.class));
		skin.load(Gdx.files.internal("Assets/UI/uiskin.json"));
		
		selbox = new SelectBox<String>(skin);
		selbox.setPosition(100, 300);
		selbox.addListener(new ClickListener());
		selbox.setSize(120, 40);
		items = new String[2];
		items[0] = "Faiblard";
		items[1] = "Mastodonte";
		selbox.setItems(items);
		selbox.setSelected("Faiblard");
		selbox.addListener(new ChangeListener() {
			public void changed(ChangeEvent arg0, Actor arg1) {
				if(selbox.getSelected() == "Faiblard") {
					ParaVie = 20;
				}
				if(selbox.getSelected() == "Mastodonte") {
					ParaVie = 120;
				}
				System.out.println(selbox.getSelected());
			}
		  });
		
		butRetour = new TextButton("RETOUR", skin, "default");
		butRetour.setPosition(20, 750);
		butRetour.setSize(130, 30);
		butRetour.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new MenuScreen(game, ParaVie));
			}
		});
		this.stage = new Stage(new FitViewport(game.V_width, game.V_height, Jeu.cam));
		
		Titre = game.assets.get("Assets/ParaScreenTitre.png");
		ImgTitre = new Image(Titre);
		ImgTitre.scaleBy(5);
		ImgTitre.setPosition(120, 340);
		
		stage.addActor(ImgTitre);
		stage.addActor(butRetour);
		stage.addActor(selbox);
	}
	public void dispose() {
		sr.dispose();
	}

	public void hide() {
		
	}

	public void pause() {
		
	}

	public void render(float delta) {
		Gdx.gl.glClearColor(0.25f, 0.25f, 0.25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.draw();
		stage.act(delta);
		
		game.batch.begin();
		game.batch.end();
		
	}

	public void resize(int width, int height) {
		
	}

	public void resume() {
		
	}

	public void show() {
		Gdx.input.setInputProcessor(stage);
	}


}
