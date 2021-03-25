package entities;

import java.awt.Point;

public class Agent extends Entity {
	
	private int speed = 1; // m/s
	
	public Agent(String name, int x, int y) {
		super(name, x, y);
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
}
