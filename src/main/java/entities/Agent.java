package entities;

import geo.Position;

public class Agent {
	
	private Position position;
	private double speed = 1; // m/s
	
	public Agent(Position point, double speed) {
		this.position = point;
		this.speed = speed;
	}
	
	public Agent() {
	}
	
	// copy constructor
	public Agent(Agent another) {
		this.position = another.position;
		this.speed = another.speed;
	}
	
	
	/*
	public void interact(ActiveObject object, String interactionName) throws InteractionException {
		object.interact(this, interactionName);
	}
	*/
	
	
	
	//Accessors and Mutators
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public void setPosition(int x, int y) {
		this.position = new Position(x,y);
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	

}
