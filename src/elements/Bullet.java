package elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import handlers.Animation;
import core.Jeu;
import states.PlayScreen;

public class Bullet extends Projectile{

	private Texture[] text;
	private Animation animation;
	private boolean Droite;

	public Bullet(Jeu jeu, World monde, PlayScreen screen, Canard can, int TailleX, int TailleY, float PosX, float PosY) {
		super(jeu, monde, screen, can, TailleX, TailleY, PosX, PosY);
		this.Droite = can.Droite;
		
		this.body.setType(BodyType.DynamicBody);
		body.setGravityScale(0);
		
		setAnimation();
	}
	public void render(SpriteBatch sb) {
		sb.draw(animation.getFrame(), body.getPosition().x-11, body.getPosition().y- 12, 25, 25);
		animation.update(1);
	}

	public void setAnimation() {
		text = new Texture[1];
		if (Droite) text[0] = jeu.assets.get("Assets/BulletD.png");
		else text[0] = jeu.assets.get("Assets/BulletG.png");
		animation = new Animation(text, 10);
	}
	
	public void action() {
	}
	
	public void update() {
		if (Droite) body.applyLinearImpulse(900000000, 0, body.getLocalCenter().x, body.getLocalCenter().y, true);
		else body.applyLinearImpulse(-90000000, 0, body.getLocalCenter().x, body.getLocalCenter().y, true);
	}

}