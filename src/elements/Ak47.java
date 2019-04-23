package elements;

import java.util.concurrent.ThreadLocalRandom;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

import handlers.Animation;
import core.Jeu;
import states.PlayScreen;

public class Ak47 extends Objet{

	private Texture[] text;
	private Animation animation;
	private boolean Droite;
	private RevoluteJointDef rdef;
	private Sound shot;
	private int randint;
	private Canard can;

	public Ak47(Jeu jeu, World monde, PlayScreen screen, int TailleX, int TailleY, int PosX, int PosY) {
		super(jeu, monde, screen, TailleX, TailleY, PosX, PosY);
		Droite = false;
		setAnimation();
	}
	public void render(SpriteBatch sb) {
		sb.draw(animation.getFrame(), body1.getPosition().x-11, body1.getPosition().y- 12, 25, 25);
		animation.update(1);
	}

	public void tenu(Canard can) {
		this.can = can;
		if(!isTenu) {
			monde.destroyBody(body1);
    		bdef.position.set(can.body.getPosition().x-10, can.body.getPosition().y-4);
    		body1 = monde.createBody(bdef);
    		this.body1.setType(BodyType.DynamicBody);
        	body1.setGravityScale(0);
        	rdef = new RevoluteJointDef();
        	rdef.bodyA = can.body;
        	rdef.bodyB = this.body1;
        	rdef.localAnchorA.set(-16,5);
        	rdef.collideConnected = false;
        	
        	fdef.shape = pshape;
    		fdef.filter.categoryBits = screen.BITOBJET;
    		fdef.filter.maskBits = (short) (screen.BITPLAYER | screen.BITGROUND);
        	body1.createFixture(fdef).setUserData(this.getClass().getSimpleName());
        	
    		text[0] = jeu.assets.get("Assets/Ak47G.png");
    		this.Droite = false;
    		monde.createJoint(rdef);
    		isTenu = true;
		}
		
    	if(!can.Droite && this.Droite && isTenu) {
    		
    		monde.destroyBody(body1);
    		bdef.position.set(can.body.getPosition().x-10, can.body.getPosition().y-12);
    		body1 = monde.createBody(bdef);
    		this.body1.setType(BodyType.DynamicBody);
        	body1.setGravityScale(0);
        	rdef = new RevoluteJointDef();
        	rdef.bodyA = can.body;
        	rdef.bodyB = this.body1;
        	rdef.localAnchorA.set(-18,-2);
        	rdef.collideConnected = false;
        	
        	fdef.shape = pshape;
    		fdef.filter.categoryBits = screen.BITOBJET;
    		fdef.filter.maskBits = (short) (screen.BITPLAYER | screen.BITGROUND);
        	body1.createFixture(fdef).setUserData(this.getClass().getSimpleName());
        	
    		text[0] = jeu.assets.get("Assets/Ak47G.png");
    		this.Droite = false;
    		
    	}
    	if(can.Droite && !this.Droite && isTenu) {
    		monde.destroyBody(body1);
    		bdef.position.set(can.body.getPosition().x+10, can.body.getPosition().y-12);
    		body1 = monde.createBody(bdef);
    		this.body1.setType(BodyType.DynamicBody);
        	body1.setGravityScale(0);
        	rdef = new RevoluteJointDef();
        	rdef.bodyA = can.body;
        	rdef.bodyB = this.body1;
        	rdef.localAnchorA.set(18,-2);
        	rdef.collideConnected = false;
        	
        	fdef.shape = pshape;
    		fdef.filter.categoryBits = screen.BITOBJET;
    		fdef.filter.maskBits = (short) (screen.BITPLAYER | screen.BITGROUND);
        	body1.createFixture(fdef).setUserData(this.getClass().getSimpleName());
        	
    		text[0] = jeu.assets.get("Assets/Ak47D.png");
    		this.Droite = true;
    	}
    	monde.createJoint(rdef);
	}

	public void setAnimation() {
		text = new Texture[1];
		text[0] = jeu.assets.get("Assets/Ak47G.png");
		animation = new Animation(text, 10);
	}
	
	public void action() {
		randint = ThreadLocalRandom.current().nextInt(0, 3 + 1);
		
		screen.IdProjectiles += 1;
		
		if(randint == 0 ) {
			shot = jeu.assets.get("Assets/Ak47Shotsound.wav");
		}
		if(randint == 1 ) {
			shot = jeu.assets.get("Assets/Ak47Shotsound2.wav");
		}
		else {
			shot = jeu.assets.get("Assets/Ak47Shotsound3.wav");
		}
		shot.play();
		if (Droite) screen.projectiles.add(new Bullet(jeu, monde, screen, can, 5, 5, body1.getPosition().x + 15, body1.getPosition().y));
		else screen.projectiles.add(new Bullet(jeu, monde, screen, can, 5, 5, body1.getPosition().x - 15, body1.getPosition().y));
		
	}

}