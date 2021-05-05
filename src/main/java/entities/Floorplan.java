package entities;

import java.util.ArrayList;

import geo.Position;

public class Floorplan {
	
	private int tileSideLength = 1; // width and height of a square tile in meters
	private int width; // width of floor plan in tiles
	private int height; // height of floor plan in tiles
	private Agent agent;
	private ArrayList<Sensor> sensors;
	private ArrayList<Position> walls;
	private ArrayList<Entity> entities;
	
	/**
	 * @param width
	 * @param height
	 * @param agent
	 * @param sensors
	 * @param walls
	 */
	public Floorplan(int tileSideLength, int width, int height, Agent agent, ArrayList<Sensor> sensors, ArrayList<Position> walls, ArrayList<Entity> entities) {
		this.tileSideLength = tileSideLength;
		this.width = width;
		this.height = height;
		this.agent = agent;
		this.sensors = sensors;
		this.walls = walls;
		this.entities = entities;
	}
	
	public Floorplan() {
	}
	
	//Accessors and Mutators
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public ArrayList<Sensor> getSensors() {
		return sensors;
	}

	public void setSensors(ArrayList<Sensor> sensors) {
		this.sensors = sensors;
	}

	public ArrayList<Position> getWalls() {
		return walls;
	}

	public void setWalls(ArrayList<Position> walls) {
		this.walls = walls;
	}

	public int getTileSideLength() {
		return tileSideLength;
	}

	public void setTileSideLength(int tileSideLength) {
		this.tileSideLength = tileSideLength;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	
	
}
