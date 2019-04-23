package elements;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.scenes.scene2d.Stage;

import handlers.Animation;
import core.Jeu;
import states.PlayScreen;


public abstract class Objet {
	
	protected Animation animation;
	public Body body;
	protected Jeu jeu;
	protected int TailleX, TailleY;
	protected World monde;
	protected Stage stage;
	protected PlayScreen screen;
	protected BodyDef bdef;
	protected RevoluteJointDef rdef;
	protected boolean isTenu = false;
	protected FixtureDef fdef;
	protected PolygonShape pshape;
	
	public Objet(Jeu jeu, World monde, PlayScreen screen, int TailleX, int TailleY, int PosX, int PosY) {
		this.stage = screen.stage;
		this.screen = screen;
		this.jeu = jeu;
		this.monde = monde;
		this.TailleX = TailleX;
		this.TailleY = TailleY;
		this.screen = screen;
		
		rdef = new RevoluteJointDef();
		
		bdef = new BodyDef();
		bdef.position.set(PosX, PosY);
		bdef.type = BodyDef.BodyType.StaticBody;
		body = monde.createBody(bdef);
		fdef = new FixtureDef();
		
		fdef = new FixtureDef();
		pshape = new PolygonShape();
		pshape = new PolygonShape();
		pshape.setAsBox(this.TailleX, this.TailleY);
		fdef.shape = pshape;
		fdef.isSensor = true;
		fdef.filter.categoryBits = screen.BITOBJET;
		fdef.filter.maskBits = (short) (screen.BITPLAYER | screen.BITGROUND);
		body.createFixture(fdef).setUserData(this.getClass().getSimpleName());
		
	}
	
	public abstract void render(SpriteBatch sb);
	
	public abstract void setAnimation();

	public abstract void tenu(Canard can);
	
	public abstract void action();
}
