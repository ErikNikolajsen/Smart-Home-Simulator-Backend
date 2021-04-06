package entities;

import java.util.ArrayList;
import java.util.UUID;

import geo.Position;

/* TODO
 * Make interact method
 * Make positions field
 * Make trigger field
 */

public class Sensor {
	
	private UUID id;
	private String name;
	private ArrayList<Position> positions;
	private ArrayList<Position> triggerArea;
	
	public Sensor(String name, ArrayList<Position> positions, ArrayList<Position> triggerArea) {
		this.id = UUID.randomUUID();
		this.name = name;
		this.positions = positions;
		this.triggerArea = triggerArea;
	}
	
	public Sensor() {
	}
	
	public void onInteraction() {
		System.out.println(name+" interaction");
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

	public UUID getId() {
		return id;
	};
	
}
