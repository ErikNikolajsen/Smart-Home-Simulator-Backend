package entities.library;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import entities.SensorPassive;
import main.Resources;

public class FloorSensor extends SensorPassive {

	// Default constructor
	public FloorSensor()  throws MqttPersistenceException, MqttException {
	}
	
	public void defineDefaultState() {
		state.put("pressurePosition", "N/A");
	}
	
	// Trigger behavior 
	public boolean updateStateAndReturnOutputAssessment() {
		if (getInteractArea().contains(Resources.getSimulator().getAgent().getPosition())) {
			state.put("pressurePosition", Resources.getSimulator().getAgent().getPosition());
			return true;
		} else {
			state.put("pressurePosition", "N/A");
			return false;
		}
		

	}
}
