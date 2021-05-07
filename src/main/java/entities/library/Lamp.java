package entities.library;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import entities.SensorActive;
import entities.SensorPassive;
import main.Resources;

public class Lamp extends SensorPassive {

	// Default constructor
	public Lamp()  throws MqttPersistenceException, MqttException {
		state.put("triggered", false);
	}
	
	// Trigger behavior 
	public boolean updateStateAndAssessTriggerConditions() {
		if (getTriggerArea().contains(Resources.getSimulator().getAgent().getPosition())) {
			state.put("triggered", true);
			return true;
		} else {
			state.put("triggered", false);
			return false;
		}
	}
}
