package handlers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class MonContactListener implements ContactListener{
	
	public boolean canard1OnGround = false;
	public boolean canard1Epee = false;
	public boolean canard1Ak = false;
	
	public boolean canard2OnGround = false;
	public boolean canard2Epee = false;
	public boolean canard2Ak = false;
	public boolean canard2Bullet;
	public boolean canard1Bullet;
	
	public void beginContact(Contact cont) {
		Fixture fa = cont.getFixtureA();
		Fixture fb = cont.getFixtureB();
		//Canard1
		if((fa.getUserData().equals("Sol") && fb.getUserData().equals("Canard1")) ||(fb.getUserData().equals("Sol") && fa.getUserData().equals("Canard1"))) {
			canard1OnGround = true;
		}
		if((fa.getUserData().equals("Epee") && fb.getUserData().equals("Canard1")) ||(fb.getUserData().equals("Epee") && fa.getUserData().equals("Canard1"))) {
			canard1Epee = true;
		}
		if((fa.getUserData().equals("Ak47") && fb.getUserData().equals("Canard1")) ||(fb.getUserData().equals("Ak47") && fa.getUserData().equals("Canard1"))) {
			canard1Ak = true;
		}
		//Canard2
		if((fa.getUserData().equals("Sol") && fb.getUserData().equals("Canard2")) ||(fb.getUserData().equals("Sol") && fa.getUserData().equals("Canard2"))) {
			canard2OnGround = true;
		}
		if((fa.getUserData().equals("Epee") && fb.getUserData().equals("Canard2")) ||(fb.getUserData().equals("Epee") && fa.getUserData().equals("Canard2"))) {
			canard2Epee = true;
		}
		if((fa.getUserData().equals("Ak47") && fb.getUserData().equals("Canard2")) ||(fb.getUserData().equals("Ak47") && fa.getUserData().equals("Canard2"))) {
			canard2Ak = true;
		}
		//Bullet
		if((fa.getUserData().equals("Canard1") && fb.getUserData().equals("Bullet")) ||(fb.getUserData().equals("Canard1") && fa.getUserData().equals("Bullet"))) {
			canard1Bullet = true;
			System.out.println("Ouch !");
		}
		if((fa.getUserData().equals("Canard2") && fb.getUserData().equals("Bullet")) ||(fb.getUserData().equals("Canard2") && fa.getUserData().equals("Bullet"))) {
			canard2Bullet = true;
			System.out.println("Ouch !");
			
		}
	}

	public void endContact(Contact cont) {
		Fixture fa = cont.getFixtureA();
		Fixture fb = cont.getFixtureB();
		if((fa.getUserData().equals("Sol") && fb.getUserData().equals("Canard1")) ||(fb.getUserData().equals("Sol") && fa.getUserData().equals("Canard1"))) {
			canard1OnGround = false;
		}
		if((fa.getUserData().equals("Sol") && fb.getUserData().equals("Canard2")) ||(fb.getUserData().equals("Sol") && fa.getUserData().equals("Canard2"))) {
			canard2OnGround = false;
		}
	}

	public void postSolve(Contact arg0, ContactImpulse arg1) {
		
	}

	public void preSolve(Contact arg0, Manifold arg1) {
		
	}
	public boolean isOnGround(int numPlayer) {
		if (numPlayer == 1) {
			return canard1OnGround;
		}
		else {
			return canard2OnGround;
		}
	}
}