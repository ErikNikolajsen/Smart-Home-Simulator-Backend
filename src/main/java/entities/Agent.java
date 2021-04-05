package entities;

import java.awt.Point;

public class Agent {
	
	private Point position;
	private final int speed = 1; // m/s
	
	public Agent(Point point) {
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
	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

}
