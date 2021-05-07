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
	
	protected HashMap<String, Object> state = new HashMap<String, Object>();
	ObjectMapper mapper = new ObjectMapper();

	public SensorActive(String name, ArrayList<Position> positions, ArrayList<Position> triggerArea, Boolean walkable) {
		super(name, positions, triggerArea, walkable);
	}

	public SensorActive() throws MqttPersistenceException, MqttException {
	}
	
	public abstract void updateState(String command) throws MqttPersistenceException, MqttException;
	
	//Outputs sensor reading if the interaction leads to a different sensor state
	public void interact(String command) throws MqttPersistenceException, MqttException, JsonProcessingException {
		HashMap<String, Object> initialState = new HashMap<>(state);
		updateState(command);
		if (!state.equals(initialState)) {
			output();
		}
	}
	
	public void output() throws MqttPersistenceException, MqttException, JsonProcessingException {
		Output output = new Output(Resources.getSimulator().getClock(),getClass().getSimpleName(),getName(),state);
		String json = mapper.writeValueAsString(output);
	    //System.out.println(json);
	    
	    System.out.println(Resources.getSimulator().getClock().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.nnnnnnnnn")).toString()+" : "+getClass().getSimpleName()+" : "+getName()+" : "+state.toString()); //human readable output for console
		//System.out.println("{\"time\":\""+Resources.getSimulator().getClock().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.nnnnnnnnn")).toString()+"\",\"type\":\""+getClass().getSimpleName()+"\",\"name\":\""+getName()+"\",\"state\":"+mapper.writeValueAsString(state)+"}"); // JSON format for MQTT output
		if (Resources.getSimulator().getMqttOutput() == true) {
			Resources.getMqtt().publish(json);
		}
	}

	
}
