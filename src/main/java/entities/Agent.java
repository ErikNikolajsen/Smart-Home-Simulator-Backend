package entities;

import geo.Position;

public class Agent {
	
	private Position position;
	private final int speed = 1; // m/s
	
	public Agent(Position point) {
		this.position = point;
	}
	
	public Agent() {
	}

	
	
	
	
	/*
	public move(int x, int y) {
		
	}
	*/
	/*
	public void interactWith(ActiveObject object, String interactionName) throws InteractionException {
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

}
