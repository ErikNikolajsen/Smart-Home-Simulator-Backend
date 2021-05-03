package entities;

import java.awt.Point;
import java.util.UUID;

public abstract class Entity {
	private String name;
	private Point position;
	
	//Constructors
	public Entity(String name, int x, int y) {
		this.name = name;
		this.position = new Point(x,y);
	}
	
	//Accessors and Mutators
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


