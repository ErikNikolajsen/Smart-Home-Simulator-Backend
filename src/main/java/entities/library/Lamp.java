package entities.library;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import entities.SensorActive;
import entities.SensorPassive;
import main.Resources;

public class Lamp extends SensorPassive {

	// Default constructor
	public Lamp()  throws MqttPersistenceException, MqttException {
		state.put("triggered", true);
		state.put("proximity", null);
	}
	
	// Trigger behavior 
	public void updateState() throws MqttPersistenceException, MqttException {
		if (Resources.getSimulator().getClock().getHour() > 20 &&
			Resources.getSimulator().getClock().getHour() < 6 &&
			getTriggerArea().contains(Resources.getSimulator().getAgent().getPosition())) {
			state.put("triggered", true);
		} else {
			state.put("triggered", false);
		}
	}
}
