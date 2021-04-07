package entities;

import java.util.UUID;

import geo.Position;

public class Wall {
	
	private UUID id;
	private Position position;
	
	public Wall(Position position) {
		this.id = UUID.randomUUID();
		this.position = position;
	}
	
	public Wall() {
		this.id = UUID.randomUUID();
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public UUID getId() {
		return id;
	}
	
	
}
