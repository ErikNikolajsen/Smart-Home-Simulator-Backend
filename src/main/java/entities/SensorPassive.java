package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import com.fasterxml.jackson.annotation.JsonIgnore;

import geo.Position;
import main.Resources;

public class SensorPassive extends Sensor {
	
	@JsonIgnore private LocalDateTime lastTriggerTime;
	private long triggerFrequency;
	
	protected HashMap<String, Object> state = new HashMap<String, Object>();

	public SensorPassive(String name, ArrayList<Position> positions, ArrayList<Position> triggerArea, LocalDateTime lastTriggerTime, long triggerFrequency) {
		super(name, positions, triggerArea);
		this.lastTriggerTime = lastTriggerTime;
		this.triggerFrequency = triggerFrequency;
	}

	public SensorPassive() {
	}

	public void trigger() throws MqttPersistenceException, MqttException {
		output("has been triggered!");
	}
	
	public void output(String message) throws MqttPersistenceException, MqttException {
		//Resources.getLog().writeToFile(Resources.getSimulator().getClock().toString(), getName(), "true");
		System.out.println(Resources.getSimulator().getClock()+" : "+getName()+" : "+message);
		if (Resources.getSimulator().getMqttOutput() == true) {
			Resources.getMqtt().publish(Resources.getSimulator().getClock()+", "+getName()+", "+message);
		}
	}
	
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
