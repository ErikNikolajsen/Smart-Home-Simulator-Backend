package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import geo.Position;
import main.Resources;

public abstract class SensorActive extends Sensor {
	
	public SensorActive(String name, ArrayList<Position> positions, ArrayList<Position> triggerArea, LocalDateTime lastTriggerTime, long triggerFrequency) {
		super(name, positions, triggerArea);
	}

	public SensorActive() throws MqttPersistenceException, MqttException {
	}
	
	public abstract void trigger(String command) throws MqttPersistenceException, MqttException;
	
	

}
