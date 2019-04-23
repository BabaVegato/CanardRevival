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
		sb.draw(animation.getFrame(), body1.getPosition().x-11, body1.getPosition().y- 12, 25, 25);
		animation.update(1);
	}

	public void tenu(Canard can) {
		
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
        	
        	pshape.setAsBox(this.TailleX/2, this.TailleY/2);
        	fdef.shape = pshape;
    		fdef.filter.categoryBits = screen.BITOBJET;
    		fdef.filter.maskBits = (short) (screen.BITPLAYER | screen.BITGROUND);
        	body1.createFixture(fdef).setUserData(this.getClass().getSimpleName());
        	
    		text[0] = jeu.assets.get("Assets/epeeG.png");
    		this.Droite = false;
    		monde.createJoint(rdef);
    		isTenu = true;
		}
		
    	if(!can.Droite && this.Droite && isTenu) {
    		
    		//System.out.println("On tourne l'épée à gauche !");
    		monde.destroyBody(body1);
    		if(body2 != null) {
    			monde.destroyBody(body2);
    		}
    		//body1 = bodyGarde
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
        	
        	monde.createJoint(rdef);
        	//body2 = bodyPointe
        	bdef.position.set(can.body.getPosition().x+10, can.body.getPosition().y-4);
    		body2 = monde.createBody(bdef);
    		this.body2.setType(BodyType.DynamicBody);
        	body2.setGravityScale(0);
        	rdef = new RevoluteJointDef();
        	rdef.bodyA = can.body;
        	rdef.bodyB = this.body2;
        	rdef.localAnchorA.set(-24,12);
        	rdef.collideConnected = false;
        	
        	pshape.setAsBox(this.TailleX/3, this.TailleY/3);
        	fdef.shape = pshape;
    		fdef.filter.categoryBits = screen.BITOBJET;
    		fdef.filter.maskBits = (short) (screen.BITPLAYER | screen.BITGROUND);
        	body2.createFixture(fdef).setUserData(this.getClass().getSimpleName());
        	monde.createJoint(rdef);
        	
    		text[0] = jeu.assets.get("Assets/epeeG.png");
    		this.Droite = false;
    		
    	}
    	if(can.Droite && !this.Droite && isTenu) {
    		//System.out.println("On tourne l'épée à droite !");
    		monde.destroyBody(body1);
    		if(body2 != null) {
    			monde.destroyBody(body2);
    		}
    		//body garde
    		bdef.position.set(can.body.getPosition().x+10, can.body.getPosition().y-4);
    		body1 = monde.createBody(bdef);
    		this.body1.setType(BodyType.DynamicBody);
        	body1.setGravityScale(0);
        	rdef = new RevoluteJointDef();
        	rdef.bodyA = can.body;
        	rdef.bodyB = this.body1;
        	rdef.localAnchorA.set(16,5);
        	rdef.collideConnected = false;
        	
        	fdef.shape = pshape;
    		fdef.filter.categoryBits = screen.BITOBJET;
    		fdef.filter.maskBits = (short) (screen.BITPLAYER | screen.BITGROUND);
        	body1.createFixture(fdef).setUserData(this.getClass().getSimpleName());
        	monde.createJoint(rdef);
        	
        	//body pointe
        	bdef.position.set(can.body.getPosition().x+10, can.body.getPosition().y-4);
    		body2 = monde.createBody(bdef);
    		this.body2.setType(BodyType.DynamicBody);
        	body2.setGravityScale(0);
        	rdef = new RevoluteJointDef();
        	rdef.bodyA = can.body;
        	rdef.bodyB = this.body2;
        	rdef.localAnchorA.set(24,12);
        	rdef.collideConnected = false;
        	
        	pshape.setAsBox(this.TailleX/3, this.TailleY/3);
        	fdef.shape = pshape;
    		fdef.filter.categoryBits = screen.BITOBJET;
    		fdef.filter.maskBits = (short) (screen.BITPLAYER | screen.BITGROUND);
        	body2.createFixture(fdef).setUserData(this.getClass().getSimpleName());
        	monde.createJoint(rdef);
        	
    		text[0] = jeu.assets.get("Assets/epeeD.png");
    		this.Droite = true;
    	}
	}

	public void setAnimation() {
		text = new Texture[1];
		text[0] = jeu.assets.get("Assets/epeeG.png");
		animation = new Animation(text, 10);
	}

	public void action() {
		body1.setTransform(body1.getWorldCenter(), 2f);
	}
}
