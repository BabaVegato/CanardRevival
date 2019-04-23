package elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import handlers.Animation;
import core.Jeu;
import states.PlayScreen;

public class Epee extends Objet{
	
	private Texture[] text;
	private Animation animation;
	private boolean Droite;
	private RevoluteJointDef rdef;

	public Epee(Jeu jeu, World monde, PlayScreen screen, int TailleX, int TailleY, int PosX, int PosY) {
		super(jeu, monde, screen, TailleX, TailleY, PosX, PosY);
		Droite = false;
		setAnimation();
	}
	public void render(SpriteBatch sb) {
		sb.draw(animation.getFrame(), body.getPosition().x-11, body.getPosition().y- 12, 25, 25);
		animation.update(1);
		
	}

	public void tenu(Canard can) {
		
		if(!isTenu) {
			monde.destroyBody(body);
    		bdef.position.set(can.body.getPosition().x-10, can.body.getPosition().y-4);
    		body = monde.createBody(bdef);
    		this.body.setType(BodyType.DynamicBody);
        	body.setGravityScale(0);
        	rdef = new RevoluteJointDef();
        	rdef.bodyA = can.body;
        	rdef.bodyB = this.body;
        	rdef.localAnchorA.set(-16,5);
        	rdef.collideConnected = false;
        	
        	fdef.shape = pshape;
    		fdef.filter.categoryBits = screen.BITOBJET;
    		fdef.filter.maskBits = (short) (screen.BITPLAYER | screen.BITGROUND);
        	body.createFixture(fdef).setUserData(this.getClass().getSimpleName());
        	
    		text[0] = jeu.assets.get("Assets/epeeG.png");
    		this.Droite = false;
    		monde.createJoint(rdef);
    		isTenu = true;
		}
		
    	if(!can.Droite && this.Droite && isTenu) {
    		
    		//System.out.println("On tourne l'épée à gauche !");
    		monde.destroyBody(body);
    		bdef.position.set(can.body.getPosition().x-10, can.body.getPosition().y-4);
    		body = monde.createBody(bdef);
    		this.body.setType(BodyType.DynamicBody);
        	body.setGravityScale(0);
        	rdef = new RevoluteJointDef();
        	rdef.bodyA = can.body;
        	rdef.bodyB = this.body;
        	rdef.localAnchorA.set(-16,5);
        	rdef.collideConnected = false;
        	
        	fdef.shape = pshape;
    		fdef.filter.categoryBits = screen.BITOBJET;
    		fdef.filter.maskBits = (short) (screen.BITPLAYER | screen.BITGROUND);
        	body.createFixture(fdef).setUserData(this.getClass().getSimpleName());
        	
    		text[0] = jeu.assets.get("Assets/epeeG.png");
    		this.Droite = false;
    		monde.createJoint(rdef);
    		
    	}
    	if(can.Droite && !this.Droite && isTenu) {
    		//System.out.println("On tourne l'épée à droite !");
    		monde.destroyBody(body);
    		bdef.position.set(can.body.getPosition().x+10, can.body.getPosition().y-4);
    		body = monde.createBody(bdef);
    		this.body.setType(BodyType.DynamicBody);
        	body.setGravityScale(0);
        	rdef = new RevoluteJointDef();
        	rdef.bodyA = can.body;
        	rdef.bodyB = this.body;
        	rdef.localAnchorA.set(16,5);
        	rdef.collideConnected = false;
        	
        	fdef.shape = pshape;
    		fdef.filter.categoryBits = screen.BITOBJET;
    		fdef.filter.maskBits = (short) (screen.BITPLAYER | screen.BITGROUND);
        	body.createFixture(fdef).setUserData(this.getClass().getSimpleName());
        	
    		text[0] = jeu.assets.get("Assets/epeeD.png");
    		this.Droite = true;
    		monde.createJoint(rdef);
    	}
	}

	public void setAnimation() {
		text = new Texture[1];
		text[0] = jeu.assets.get("Assets/epeeG.png");
		animation = new Animation(text, 10);
	}

	public void action() {
		body.setTransform(body.getWorldCenter(), 2f);
	}
}
