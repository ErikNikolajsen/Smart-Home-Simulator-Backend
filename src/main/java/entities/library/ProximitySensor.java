package entities.library;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import entities.SensorPassive;
import geo.Position;
import main.Resources;

public class ProximitySensor extends SensorPassive {

	// Default constructor
	public ProximitySensor()  throws MqttPersistenceException, MqttException {
	}
	
	public void defineDefaultState() {
		state.put("triggered", false);
		state.put("proximity", "N/A");
	}
	
	// Trigger behavior 
	public boolean updateStateAndReturnOutputAssessment() {
		if (getInteractArea().contains(Resources.getSimulator().getAgent().getPosition())) {
			state.put("triggered", true);
			
			double x = 0;
			double y = 0;
			for (Position position : getPhysicalArea()) {
				x = x + position.getX();
				y = y + position.getY();
			}
			x = x / getPhysicalArea().size();
			y = y / getPhysicalArea().size();
			Double proximity = Math.hypot(Resources.getSimulator().getAgent().getPosition().getX() - x, Resources.getSimulator().getAgent().getPosition().getY() - y);
			
			
			state.put("proximity", proximity);
			return true;
		} else {
			state.put("triggered", false);
			state.put("proximity", "N/A");
			return false;
		}
	}
}
