package entities;

import java.util.ArrayList;

public class Floorplan {
	
	private int width;
	private int height;
	private Agent agent;
	private ArrayList<Sensor> sensors;
	private ArrayList<Wall> walls;
	
	/**
	 * @param width
	 * @param height
	 * @param agent
	 * @param sensors
	 * @param walls
	 */
	public Floorplan(int width, int height, Agent agent, ArrayList<Sensor> sensors, ArrayList<Wall> walls) {
		this.width = width;
		this.height = height;
		this.agent = agent;
		this.sensors = sensors;
		this.walls = walls;
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

	public ArrayList<Wall> getWalls() {
		return walls;
	}

	public void setWalls(ArrayList<Wall> walls) {
		this.walls = walls;
	}
	
	
}
