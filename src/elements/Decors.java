package elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;

import handlers.Animation;

public abstract class Decors {
	protected Texture[] text;
	protected Animation animation;
	protected Body body;
	
	public Decors(){
		
	}
	public abstract void render(SpriteBatch sb);
}
