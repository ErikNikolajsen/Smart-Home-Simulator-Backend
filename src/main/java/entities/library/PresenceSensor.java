package entities.library;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import entities.SensorPassive;
import main.Resources;

public class PresenceSensor extends SensorPassive {

	// Default constructor
	public PresenceSensor()  throws MqttPersistenceException, MqttException {
		
	}
	
	public void defineDefaultState() {
		state.put("triggered", false);
	}
	
	// Trigger behavior 
	public boolean updateStateAndReturnOutputAssessment() {
		if (getInteractArea().contains(Resources.getSimulator().getAgent().getPosition())) {
			state.put("triggered", true);
			return true;
		} else {
			state.put("triggered", false);
			return false;
		}
	}
}
