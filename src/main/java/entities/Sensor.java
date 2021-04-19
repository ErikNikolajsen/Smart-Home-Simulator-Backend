package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import geo.Position;
import main.Resources;


@JsonTypeInfo(use = Id.CLASS,
include = JsonTypeInfo.As.PROPERTY,
property = "type")
public class Sensor {
	
	@JsonIgnore private UUID id;
	private String name;
	private ArrayList<Position> positions;
	private ArrayList<Position> triggerArea;
	private boolean walkable;
	@JsonIgnore private LocalDateTime lastTriggerTime;
	private long triggerFrequency;
	
	public Sensor(String name, ArrayList<Position> positions, ArrayList<Position> triggerArea, long triggerFrequency) {
		this.id = UUID.randomUUID();
		this.name = name;
		this.positions = positions;
		this.triggerArea = triggerArea;
		this.triggerFrequency = triggerFrequency;
	}
	
	public Sensor() {
		this.id = UUID.randomUUID();
	}
	
	public void onInteraction() {
		Resources.getLog().writeToFile(Resources.getSimulator().getClock().toString(), getName(), "true");
		//System.out.println(name+": interaction");	
		System.out.println(Resources.getSimulator().getClock()+" : "+getName()+" has been triggered!");
		
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
	}

	public boolean isWalkable() {
		return walkable;
	}

	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}

	public LocalDateTime getLastTriggerTime() {
		return lastTriggerTime;
	}

	public void setLastTriggerTime(LocalDateTime lastTriggerTime) {
		this.lastTriggerTime = lastTriggerTime;
	}

	public long getTriggerFrequency() {
		return triggerFrequency;
	}

	public void setTriggerFrequency(long triggerFrequency) {
		this.triggerFrequency = triggerFrequency;
	};
	
	
}
