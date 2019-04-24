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
	public int ProjMur = 0;
	
	public void beginContact(Contact cont) {
		Fixture fa = cont.getFixtureA();
		Fixture fb = cont.getFixtureB();
		//Canard1
		if((fa.getUserData().toString().contains("Decors") && fb.getUserData().equals("Canard1")) ||(fb.getUserData().toString().contains("Decors") && fa.getUserData().equals("Canard1"))) {
			canard1OnGround = true;
		}
		if((fa.getUserData().equals("Epee") && fb.getUserData().equals("Canard1")) ||(fb.getUserData().equals("Epee") && fa.getUserData().equals("Canard1"))) {
			canard1Epee = true;
		}
		if((fa.getUserData().equals("Ak47") && fb.getUserData().equals("Canard1")) ||(fb.getUserData().equals("Ak47") && fa.getUserData().equals("Canard1"))) {
			canard1Ak = true;
		}
		//Canard2
		if((fa.getUserData().toString().contains("Decors") && fb.getUserData().equals("Canard2")) ||(fb.getUserData().toString().contains("Decors") && fa.getUserData().equals("Canard2"))) {
			canard2OnGround = true;
		}
		if((fa.getUserData().equals("Epee") && fb.getUserData().equals("Canard2")) ||(fb.getUserData().equals("Epee") && fa.getUserData().equals("Canard2"))) {
			canard2Epee = true;
		}
		if((fa.getUserData().equals("Ak47") && fb.getUserData().equals("Canard2")) ||(fb.getUserData().equals("Ak47") && fa.getUserData().equals("Canard2"))) {
			canard2Ak = true;
		}
		//Bullet VS Canard
		if((fa.getUserData().equals("Canard1") && fb.getUserData().toString().startsWith("Bullet")) ||(fb.getUserData().equals("Canard1") && fa.getUserData().toString().startsWith("Bullet"))) {
			canard1Bullet = true;
			System.out.println("Ouch !");
		}
		if((fa.getUserData().equals("Canard2") && fb.getUserData().toString().startsWith("Bullet")) ||(fb.getUserData().equals("Canard2") && fa.getUserData().toString().startsWith("Bullet"))) {
			canard2Bullet = true;
			System.out.println("Ouch !");
			
		}
		//Projectile VS Mur
		if((fa.getUserData().toString().contains("Decors") && fb.getUserData().toString().contains("Proj"))) {
			ProjMur = Integer.parseInt(fb.getUserData().toString().subSequence(fb.getUserData().toString().length()-2, fb.getUserData().toString().length()).toString());
		}
		else if((fb.getUserData().toString().contains("Decors") && fa.getUserData().toString().contains("Proj"))) {
			ProjMur = Integer.parseInt(fa.getUserData().toString().subSequence(fa.getUserData().toString().length()-2, fa.getUserData().toString().length()).toString());
		}
		
	}

	public void endContact(Contact cont) {
		Fixture fa = cont.getFixtureA();
		Fixture fb = cont.getFixtureB();
		if((fa.getUserData().toString().contains("Decors") && fb.getUserData().equals("Canard1")) ||(fb.getUserData().toString().contains("Decors") && fa.getUserData().equals("Canard1"))) {
			canard1OnGround = false;
		}
		if((fa.getUserData().toString().contains("Decors") && fb.getUserData().equals("Canard2")) ||(fb.getUserData().toString().contains("Decors") && fa.getUserData().equals("Canard2"))) {
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