package elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import handlers.Animation;
import core.Jeu;
import states.PlayScreen;

public class Sol extends Decors{

	public Sol(Jeu jeu, World monde, PlayScreen screen, int TailleX, int TailleY, int PosX, int PosY) {
		BodyDef bdef = new BodyDef();
		bdef.position.set(PosX, PosY);
		bdef.type = BodyDef.BodyType.StaticBody;
		body = monde.createBody(bdef);
		FixtureDef fdef = new FixtureDef();
		PolygonShape pshape = new PolygonShape();
		pshape.setAsBox(TailleX, TailleY);
		
		fdef.shape = pshape;
		fdef.filter.categoryBits = screen.BITGROUND;
		fdef.filter.maskBits = (short) (screen.BITPLAYER | screen.BITOBJET);
		body.createFixture(fdef).setUserData("Sol");
		
		text = new Texture[1];
		text[0] = jeu.assets.get("Assets/sol.png");
		
		animation = new Animation(text, 10);
	}
	public void render(SpriteBatch sb) {
		sb.draw(animation.getFrame(), body.getPosition().x-100, body.getPosition().y-162, 200, 200);
		animation.update(1);
	}
}
