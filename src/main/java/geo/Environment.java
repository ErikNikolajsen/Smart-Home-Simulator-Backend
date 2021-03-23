package geo;

import java.util.ArrayList;

import entities.Agent;
import entities.Sensor;

public class Environment {
	// Map dimensions
	int tileSize = 100; // x and y dimensions of a square tile in centimeters
	int xMax, yMax; // map dimensions defined as a rectangle of tiles
	Tile[][] tiles;
	
	// Entitites
	Agent agent;
	ArrayList<Sensor> sensorList = new ArrayList<Sensor>();
	
	public Environment(int xMax, int yMax) {
		tiles = new Tile[xMax][yMax];
	}
	
}
