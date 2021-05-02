package entities;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import main.Resources;

public class SmartTV extends SensorActive {
	
	// Sensor state consisting of the following variables
	private SensorCommands channel;
	private int volume = 50;
	
	public SmartTV()  throws MqttPersistenceException, MqttException {
	}
	
	// Possible commands for the sensor
	enum SensorCommands {
		OFF,
		CHANNEL1,
		CHANNEL2,
		CHANNEL3,
		CHANNEL4,
		VOLUME_DOWN,
		VOLUME_UP
	}
	
	// Trigger behavior
	public void trigger(String commandString) throws MqttPersistenceException, MqttException {
		SensorCommands command = SensorCommands.valueOf(commandString);
		if (command == SensorCommands.OFF) {
			channel = command;
			output(channel.toString());
			return;
		} else if (command == SensorCommands.VOLUME_DOWN && channel != SensorCommands.OFF && volume>0) {
			volume = volume - 5;
		} else if (command == SensorCommands.VOLUME_UP && channel != SensorCommands.OFF && volume<100) {
			volume = volume + 5;
		} else if (command == SensorCommands.CHANNEL1 || command == SensorCommands.CHANNEL2 || command == SensorCommands.CHANNEL3 || command == SensorCommands.CHANNEL4) {
			channel = command;
		}
		output("Channel: "+channel+", Volume: "+volume);
		
	}


}
