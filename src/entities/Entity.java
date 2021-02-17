package entities;

import java.awt.Point;
import java.util.UUID;

public class Entity {
	private UUID id;
	private String name;
	private Point position;
	
	//Constructors
	public Entity(String name, int x, int y) {
		this.id = UUID.randomUUID();
		this.name = name;
		this.position = new Point(x,y);
	}
	
	//Accessors and Mutators
	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}	
	
	
}


