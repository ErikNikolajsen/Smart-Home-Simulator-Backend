package entities;

import java.util.ArrayList;

import geo.Position;

public class Entity {
	private String name;
	private ArrayList<Position> positions;
	private ArrayList<Position> triggerArea;
	private boolean walkable;
	
	//Constructors
	public Entity(String name, ArrayList<Position> positions, ArrayList<Position> triggerArea, Boolean walkable) {
		this.name = name;
		this.positions = positions;
		this.triggerArea = triggerArea;
		this.walkable = walkable;
	}
	
	public Entity() {
	}
	
	//Accessors and Mutators
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public ArrayList<Position> getPositions() {
			return positions;
		}

		public void setPositions(ArrayList<Position> positions) {
			this.positions = positions;
		}

		public ArrayList<Position> getTriggerArea() {
			return triggerArea;
		}

		public void setTriggerArea(ArrayList<Position> triggerArea) {
			this.triggerArea = triggerArea;
		}

		public boolean getWalkable() {
			return walkable;
		}

		public void setWalkable(boolean walkable) {
			this.walkable = walkable;
		}
	
	
}


