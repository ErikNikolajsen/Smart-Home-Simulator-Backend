package entities.library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import entities.SensorActive;

public class Television extends SensorActive {
	
	public Television() throws MqttPersistenceException, MqttException {
	}

	public ArrayList<String> defineCommands() {
		return new ArrayList<String>(Arrays.asList(
			"OFF",
			"CHANNEL1",
			"CHANNEL2",
			"CHANNEL3",
			"CHANNEL4",
			"VOLUME_DOWN",
			"VOLUME_UP"
		));
	}
	
	public void defineDefaultState() {
		state.put("channel", "OFF");
		state.put("volume", 50);
	}
	
	// Sensor behavior
	public void updateState(String command) throws MqttPersistenceException, MqttException {
		// Set channel or turn off TV
		if (command.equals("CHANNEL1") || command.equals("CHANNEL2") || command.equals("CHANNEL3") || command.equals("CHANNEL4") || command.equals("OFF")) {
			state.put("channel", command);
		// Turn volume down
		} else if (command.equals("VOLUME_DOWN") && !state.get("channel").equals("OFF") && (Integer) state.get("volume")>0) {
			state.put("volume",(Integer) state.get("volume") - 5);
		// Turn volume up
		} else if (command.equals("VOLUME_UP") && !state.get("channel").equals("OFF") && (Integer) state.get("volume")<100) {
			state.put("volume",(Integer) state.get("volume") + 5);
		}
	}
}
