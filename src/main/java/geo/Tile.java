package geo;

import java.awt.Point;

public class Tile {
	private final Point position;
	private final int type;

	public Tile(int x, int y, int type) {
		this.position = new Point(x,y);
		this.type = type;
	}
	
	
	
	public Point getPosition() {
		return position;
	}

	public int getType() {
		return type;
	}
}
