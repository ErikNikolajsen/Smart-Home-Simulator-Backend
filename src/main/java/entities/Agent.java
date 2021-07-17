package entities;

import geo.Position;

public class Agent {
	
	private String id;
	private Position position;
	private double speed; // meter per second
	
	public Agent(String id, Position point, double speed) {
		this.id = id;
		this.position = point;
		this.speed = speed;
	}
	
	public Agent() {
	}
	
	// copy constructor
	public Agent(Agent another) {
		this.id = another.id;
		this.position = another.position;
		this.speed = another.speed;
	}
	
	//Accessors and Mutators
	public Position getPosition() {
		return position;
	}

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
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
