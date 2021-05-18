package entities.library;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import entities.SensorActive;
import entities.SensorPassive;
import main.Resources;

public class FloorSensorGlobal extends SensorPassive {

	// Default constructor
	public FloorSensorGlobal()  throws MqttPersistenceException, MqttException {
	}
	
	public void defineDefaultState() {
		state.put("pressurePosition", "N/A");
	}
	
	// Trigger behavior 
	public boolean updateStateAndReturnOutputAssessment() {
		state.put("pressurePosition", Resources.getSimulator().getAgent().getPosition());
		return true;

	}
}
