package entities;

import java.util.ArrayList;

import geo.Position;

public class Floorplan {
	
	private double tileSideLength; // width and height of a square tile in meters
	private int width; // width of floor plan in tiles
	private int height; // height of floor plan in tiles
	private Agent agent;
	private ArrayList<Position> walls;
	private ArrayList<SensorActive> activeSensors;
	private ArrayList<SensorPassive> passiveSensors;
	private ArrayList<Entity> entities;
	
	public Floorplan(int tileSideLength, int width, int height, Agent agent, ArrayList<Position> walls, ArrayList<SensorActive> activeSensors, ArrayList<SensorPassive> passiveSensors, ArrayList<Entity> entities) {
		this.tileSideLength = tileSideLength;
		this.width = width;
		this.height = height;
		this.agent = agent;
		this.walls = walls;
		this.entities = entities;
		this.activeSensors = activeSensors;
		this.passiveSensors = passiveSensors;
	}
	
	public Floorplan() {
	}
	
	public void test() {
		
	}
	
	//Accessors and Mutators
	public ArrayList<Sensor> getAllSensors() {
		ArrayList<Sensor> allSensors = new ArrayList<Sensor>();
		allSensors.addAll(activeSensors);
		allSensors.addAll(passiveSensors);
		return allSensors;
	}
	
	public ArrayList<Entity> getAllEntities() {
		ArrayList<Entity> allEntities = new ArrayList<Entity>();
		allEntities.addAll(activeSensors);
		allEntities.addAll(passiveSensors);
		allEntities.addAll(entities);
		return allEntities;
	}
	
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

	public ArrayList<Position> getWalls() {
		return walls;
	}

	public void setWalls(ArrayList<Position> walls) {
		this.walls = walls;
	}

	public double getTileSideLength() {
		return tileSideLength;
	}

	public void setTileSideLength(double tileSideLength) {
		this.tileSideLength = tileSideLength;
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

	public ArrayList<SensorActive> getActiveSensors() {
		return activeSensors;
	}

	public void setActiveSensors(ArrayList<SensorActive> activeSensors) {
		this.activeSensors = activeSensors;
	}

	public ArrayList<SensorPassive> getPassiveSensors() {
		return passiveSensors;
	}

	public void setPassiveSensors(ArrayList<SensorPassive> passiveSensors) {
		this.passiveSensors = passiveSensors;
	}
	
	
	
}
