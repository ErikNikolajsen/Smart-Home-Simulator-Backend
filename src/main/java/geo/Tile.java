package geo;

import java.awt.Point;

import lombok.Getter;
import lombok.Setter;

public class Tile {
	@Getter @Setter private Point position;
	@Getter @Setter private int type; // 0=nothing, 1=collisionObject, >1=sensorID

	public Tile(int x, int y, int type) {
		this.position = new Point(x,y);
		this.type = type;
	}
	
	// empty constructor used for POST requests
	public Tile() {
	}
	
	public boolean isPassable() {
		if (this.type == 1) {
			return false;
		} else {
			return true;
		}
	}
	

	
	
}