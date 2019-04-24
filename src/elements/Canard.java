package elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import handlers.Animation;
import core.Jeu;
import states.PlayScreen;

public class Canard extends Sprite{
	public int Vie;
	public Body body;
	public Animation animation;
	public Boolean SurLeSol;
	private Texture[] text;
	private Jeu jeu;
	public boolean Droite;
	public String state;
	public FixtureDef fdef;
	private PolygonShape pshape;
	private BodyDef bdef;

	public Canard(Jeu jeu, World monde, PlayScreen screen, int TailleX, int TailleY, int PosX, int PosY, int numPlayer, int Vie) {
		this.state = "Vivant";
		this.Vie = Vie;
		this.jeu = jeu;
		
		bdef = new BodyDef();
		bdef.position.set(PosX, PosY);
		bdef.type = BodyDef.BodyType.DynamicBody;
		bdef.fixedRotation = true;
		body = monde.createBody(bdef);
		
		//Fixture corps
		fdef = new FixtureDef();
		pshape = new PolygonShape();
		pshape.setAsBox(TailleX, TailleY);
		fdef.filter.maskBits = (short) (screen.BITGROUND | screen.BITOBJET);
		fdef.shape = pshape;
		fdef.density = 1f;
		fdef.filter.categoryBits = screen.BITPLAYER;
		body.createFixture(fdef).setUserData("Canard" + numPlayer);
		//Fixture sensor pieds
		fdef = new FixtureDef();
		pshape = new PolygonShape();
		pshape.setAsBox(TailleX/3, TailleY/3);
		fdef.filter.maskBits = (short) (screen.BITGROUND | screen.BITOBJET);
		fdef.shape = pshape;
		fdef.isSensor = true;
		fdef.filter.categoryBits = screen.BITPLAYER;
		body.createFixture(fdef).setUserData("Canard" + numPlayer + "Pieds");
		
		Droite = false;
		
		text = new Texture[3];
		setAnimation();
		SurLeSol = false;
		
	}

	public void fire() {
	}
	public void render(SpriteBatch sb) {
		sb.draw(animation.getFrame(), body.getPosition().x-14, body.getPosition().y-15, 30, 30);
		animation.update(1);
		if(Vie <= 0) {
			state = "Mort";
		}
	}
	public void setAnimation() {
		if(!Droite) {
			text[0] = jeu.assets.get("Assets/canardG.png");
			text[1] = jeu.assets.get("Assets/canardG2.png");
			text[2] = jeu.assets.get("Assets/canardG3.png");
			animation = new Animation(text, 10);
		}
		else{
			text[0] = jeu.assets.get("Assets/canardD.png");
			text[1] = jeu.assets.get("Assets/canardD2.png");
			text[2] = jeu.assets.get("Assets/canardD3.png");
			animation = new Animation(text, 10);
		}
	}
	
	
}
