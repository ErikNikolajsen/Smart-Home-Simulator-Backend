package archive;

import java.awt.Point;
import java.util.ArrayList;

import entities.Agent;
import entities.Sensor;
import lombok.Getter;

public class Grid {
	
	//private int tileSize = 100; // x and y dimensions of a square tile in centimeters
	//Tile[][] tiles;
	@Getter private int width, height; // map dimensions defined as a rectangle of tiles
	@Getter private ArrayList<Tile> tiles;
	
	// Entitites 
	/*
	Agent agent;
	ArrayList<Sensor> sensorList = new ArrayList<Sensor>();
	*/
	
	public Grid(int widht, int height, ArrayList<Tile> tiles) {
		this.width = widht;
		this.height = height;
		this.tiles = tiles;
	}
	
	// return tile object with specific position
	public Tile find(Point position){
        for(Tile t : tiles){
            if(t.getPosition() == position)
                return t;
        }
        return null;
    }
}
