package entities;

import java.awt.Point;
import java.util.UUID;

public class Wall {
	
	private UUID id;
	private Point position;
	
	public Wall(Point position) {
		this.id = UUID.randomUUID();
		this.position = position;
	}
	
	public Wall() {
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public UUID getId() {
		return id;
	}
	
	
}
