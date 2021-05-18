package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import geo.Position;
import main.Resources;

public abstract class SensorActive extends Sensor {
	
	// Properties
	protected ArrayList<String> commands;
	
	// Constructors
	public SensorActive(String name, ArrayList<Position> physicalArea, ArrayList<Position> interactArea, Boolean walkable) {
		super(name, physicalArea, interactArea, walkable);
	}

	public SensorActive() throws MqttPersistenceException, MqttException {
		commands = defineCommands();
		defineDefaultState();
	}
	
	// Methods 
	// Outputs sensor reading if the interaction leads to a different sensor state
	public void interact(String command) throws MqttPersistenceException, MqttException, JsonProcessingException {
		HashMap<String, Object> initialState = new HashMap<>(state);
		updateState(command);
		if (!state.equals(initialState)) {
			output();
		}
	}
	
	// Abstract methods that must be implemented by active sensors
	public abstract void updateState(String command) throws MqttPersistenceException, MqttException;
	
	public abstract ArrayList<String> defineCommands();
	
	public abstract void defineDefaultState();
	
	// Accessors
	public ArrayList<String> getCommands() {
		return commands;
	}
	
	
}
