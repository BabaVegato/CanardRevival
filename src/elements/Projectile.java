package elements;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

import handlers.Animation;
import core.Jeu;
import states.PlayScreen;


public abstract class Projectile {
	
	protected Animation animation;
	public Body body;
	protected Jeu jeu;
	protected int TailleX, TailleY;
	protected World monde;
	protected PlayScreen screen;
	protected BodyDef bdef;
	protected RevoluteJointDef rdef;
	protected FixtureDef fdef;
	protected PolygonShape pshape;
	protected Canard can;
	public int num;
	
	public Projectile(Jeu jeu, World monde, PlayScreen screen, Canard can, int TailleX, int TailleY, float PosX, float PosY) {
		this.can = can;
		this.screen = screen;
		this.jeu = jeu;
		this.monde = monde;
		this.TailleX = TailleX;
		this.TailleY = TailleY;
		
		rdef = new RevoluteJointDef();
		
		bdef = new BodyDef();
		bdef.position.set(PosX, PosY);
		bdef.type = BodyDef.BodyType.DynamicBody;
		body = monde.createBody(bdef);
		
		fdef = new FixtureDef();
		fdef.isSensor = true;
		pshape = new PolygonShape();
		pshape = new PolygonShape();
		pshape.setAsBox(this.TailleX, this.TailleY);
		fdef.shape = pshape;
		fdef.filter.categoryBits = screen.BITOBJET;
		fdef.filter.maskBits = (short) (screen.BITGROUND | screen.BITPLAYER);
		if (screen.IdProjectiles<=9) {
			body.createFixture(fdef).setUserData(this.getClass().getSimpleName() + "Proj" + "0" + screen.IdProjectiles);
		}
		else {
			body.createFixture(fdef).setUserData(this.getClass().getSimpleName() + "Proj" + screen.IdProjectiles);
		}
		this.num = screen.IdProjectiles;
		
	}
	
	public abstract void render(SpriteBatch sb);
	
	public abstract void setAnimation();
	
	public abstract void update();
	
	public void Dispose() {
		monde.destroyBody(body);
	}

}
