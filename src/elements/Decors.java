package elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import core.Jeu;
import handlers.Animation;
import states.PlayScreen;

public abstract class Decors{
	protected Texture[] text;
	protected Animation animation;
	protected Body body;
	private PlayScreen screen;
	private Jeu jeu;
	private World monde;
	private BodyDef bdef;
	private FixtureDef fdef;
	private PolygonShape pshape;
	
	public Decors(Jeu jeu, World monde, PlayScreen screen, int TailleX, int TailleY, float PosX, float PosY){
		this.screen = screen;
		this.jeu = jeu;
		this.monde = monde;
		
		bdef = new BodyDef();
		bdef.position.set(PosX, PosY);
		bdef.type = BodyDef.BodyType.StaticBody;
		body = monde.createBody(bdef);
		fdef = new FixtureDef();
		pshape = new PolygonShape();
		pshape.setAsBox(TailleX, TailleY);
		
		fdef.shape = pshape;
		fdef.filter.categoryBits = screen.BITGROUND;
		fdef.filter.maskBits = (short) (screen.BITPLAYER | screen.BITOBJET);
		body.createFixture(fdef).setUserData(this.getClass().getSimpleName() + "Decors");
	}
	public abstract void render(SpriteBatch sb);
}
