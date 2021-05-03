package entities;

import java.util.ArrayList;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import geo.Position;
import main.Resources;


@JsonTypeInfo(use = Id.CLASS,
include = JsonTypeInfo.As.PROPERTY,
property = "type")
public abstract class Sensor {
	private String name;
	private ArrayList<Position> positions;
	private ArrayList<Position> triggerArea;
	private boolean walkable;
	
	public Sensor(String name, ArrayList<Position> positions, ArrayList<Position> triggerArea/*, long triggerFrequency*/) {
		this.name = name;
		this.positions = positions;
		this.triggerArea = triggerArea;
	}
	
	public Sensor() {
	}
	
	public void output(String message) throws MqttPersistenceException, MqttException {
		//Resources.getLog().writeToFile(Resources.getSimulator().getClock().toString(), getName(), "true");
		System.out.println(Resources.getSimulator().getClock()+" : "+getName()+" : "+message);
		if (Resources.getSimulator().getMqttOutput() == true) {
			Resources.getMqtt().publish(Resources.getSimulator().getClock()+", "+getName()+", "+message);
		}
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
