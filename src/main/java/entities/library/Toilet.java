package entities.library;

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import entities.SensorActive;

public class Toilet extends SensorActive {
	
	public Toilet() throws MqttPersistenceException, MqttException {
	}

	public ArrayList<String> defineCommands() {
		return new ArrayList<String>(Arrays.asList(
				"FLUSH"
				
		));
	}
	
	public void defineDefaultState() {
		state.put("flush_amount", 0);
		state.put("water_usage", 0); // in Liters
	}
	
	// Sensor behavior
	public void updateState(String command) throws MqttPersistenceException, MqttException {
		// Set power status
		if (command.equals("FLUSH")) {
			state.put("flush_amount", (Integer) state.get("flush_amount") + 1);
			state.put("water_usage", (Double) state.get("water_usage") + 3.3);
		}
		/*
		// Set channel
		} else if ((command.equals("CHANNEL1") || command.equals("CHANNEL2") || command.equals("CHANNEL3") || command.equals("CHANNEL4")) && state.get("power").equals("ON")) {
			state.put("channel", command);
		// Turn volume down
		} else if (command.equals("VOLUME_DOWN") && state.get("power").equals("ON") && (Integer) state.get("volume")>0) {
			state.put("volume",(Integer) state.get("volume") - 1);
		// Turn volume up
		} else if (command.equals("VOLUME_UP") && state.get("power").equals("ON") && (Integer) state.get("volume")<10) {
			state.put("volume",(Integer) state.get("volume") + 1);
		}
		*/
	}
}
