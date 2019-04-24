package elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import handlers.Animation;
import core.Jeu;
import states.PlayScreen;

public class Mur extends Decors{
	
	private Texture[] text;
	private Animation animation;

	public Mur(Jeu jeu, World monde, PlayScreen screen, int TailleX, int TailleY, int PosX, int PosY) {
		super(jeu, monde, screen, TailleX, TailleY, PosX, PosY);
		
		text = new Texture[1];
		text[0] = jeu.assets.get("Assets/mur.png");

	}
	public void render(SpriteBatch sb) {
		sb.draw(text[0], body.getPosition().x-10, body.getPosition().y-10, 20, 20);
	}
}
