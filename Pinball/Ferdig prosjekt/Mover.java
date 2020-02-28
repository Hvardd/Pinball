public class Mover {

	PVector location;
	PVector velocity;
	PVector acceleration;
	

	public Mover(float width, float height)  {
		location = new PVector (width/2, height/2);
		velocity = new PVector (0,0);
		acceleration = new PVector (0,0);
		}

	public void applyForce(PVector force) {
		acceleration.add(force);
	}

	public void update() {
		velocity.add(acceleration);
		location.add(velocity);
		acceleration.multiply(0);
	}



}