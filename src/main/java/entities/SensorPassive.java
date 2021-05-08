package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import geo.Position;
import main.Resources;

public abstract class SensorPassive extends Sensor {
	
	@JsonIgnore private LocalDateTime lastTriggerTime;
	private long triggerFrequency;

	public SensorPassive(String name, ArrayList<Position> physicalArea, ArrayList<Position> interactArea, LocalDateTime lastTriggerTime, long triggerFrequency, Boolean walkable) {
		super(name, physicalArea, interactArea, walkable);
		this.lastTriggerTime = lastTriggerTime;
		this.triggerFrequency = triggerFrequency;
	}

	public SensorPassive() {
	}
	
	public abstract boolean updateStateAndAssessTriggerConditions();
	
	
	
	//Accessors and Mutators
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
