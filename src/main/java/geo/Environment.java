package geo;

import java.util.ArrayList;

import entities.Agent;
import entities.Sensor;

public class Environment {
	// Map dimensions
	int tileSize; // x and y dimensions of a square tile in centimeters
	int xMax, yMax; // map dimensions defined as a rectangle of tiles
	int[][] tiles = new int[10][10];
	
	// Entitites
	Agent agent;
	ArrayList<Sensor> sensorList = new ArrayList<Sensor>();
	
	public Environment(int x, int y) {
		
		tiles = new int[10][10];
	}
	
}
