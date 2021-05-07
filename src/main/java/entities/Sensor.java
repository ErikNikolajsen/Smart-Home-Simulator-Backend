package entities;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	protected HashMap<String, Object> state = new HashMap<String, Object>();
	ObjectMapper mapper = new ObjectMapper();
	
	public Sensor(String name, ArrayList<Position> positions, ArrayList<Position> triggerArea, Boolean walkable) {
		this.name = name;
		this.positions = positions;
		this.triggerArea = triggerArea;
		this.walkable = walkable;
	}
	
	public Sensor() {
	}
	
	public void output() throws MqttPersistenceException, MqttException, JsonProcessingException {
		//Resources.getLog().writeToFile(Resources.getSimulator().getClock().toString(), getName(), "true");
		Output output = new Output(Resources.getSimulator().getClock(),getClass().getSimpleName(),getName(),state);
		String json = mapper.writeValueAsString(output);
	    //System.out.println(json);
		
		System.out.println(Resources.getSimulator().getClock().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.nnnnnnnnn")).toString()+" : "+getClass().getSimpleName()+" : "+getName()+" : "+state.toString()); //human readable output for console
		//System.out.println("{\"time\":\""+Resources.getSimulator().getClock().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.nnnnnnnnn")).toString()+"\",\"type\":\""+getClass().getSimpleName()+"\",\"name\":\""+getName()+"\",\"state\":"+mapper.writeValueAsString(state)+"}"); // JSON format for MQTT output
		if (Resources.getSimulator().getMqttOutput() == true) {
			Resources.getMqtt().publish(json);
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
