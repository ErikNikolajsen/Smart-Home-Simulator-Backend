package entities.library;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import entities.SensorActive;
import geo.Position;
import main.Resources;

public class SmartTV extends SensorActive {

	// Default constructor
	public SmartTV()  throws MqttPersistenceException, MqttException {
		state.put("channel", Command.OFF);
		state.put("volume", 50);
	}
	
	// Possible commands for the sensor
	public enum Command {
		OFF,
		CHANNEL1,
		CHANNEL2,
		CHANNEL3,
		CHANNEL4,
		VOLUME_DOWN,
		VOLUME_UP
	}
	
	// Trigger behavior 
	public void trigger2(String commandString) throws MqttPersistenceException, MqttException {
		Command command = Command.valueOf(commandString);
		// Turn TV off
		if (command == Command.OFF) {
			state.put("channel", command);
		// Turn volume down
		} else if (command == Command.VOLUME_DOWN && state.get("channel") != Command.OFF && (Integer) state.get("volume")>0) {
			state.put("volume",(Integer) state.get("volume") - 5);
		// Turn volume up
		} else if (command == Command.VOLUME_UP && state.get("channel") != Command.OFF && (Integer) state.get("volume")<100) {
			state.put("volume",(Integer) state.get("volume") + 5);
		// Set channel
		} else if (command == Command.CHANNEL1 || command == Command.CHANNEL2 || command == Command.CHANNEL3 || command == Command.CHANNEL4) {
			state.put("channel", command);
		}
	}
}
