package entities;

import java.util.ArrayList;
import java.util.UUID;
import java.awt.Point;

/* TODO
 * Make interact method
 * Make positions field
 * Make trigger field
 */

public class Sensor {
	
	private UUID id;
	private String name;
	private ArrayList<Point> positions;
	private ArrayList<Point> triggerArea;
	
	public Sensor(String name, ArrayList<Point> positions, ArrayList<Point> triggerArea) {
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

	public ArrayList<Point> getPositions() {
		return positions;
	}

	public void setPositions(ArrayList<Point> positions) {
		this.positions = positions;
	}

	public ArrayList<Point> getTriggerArea() {
		return triggerArea;
	}

	public void setTriggerArea(ArrayList<Point> triggerArea) {
		this.triggerArea = triggerArea;
	}

	public UUID getId() {
		return id;
	};
	
}
