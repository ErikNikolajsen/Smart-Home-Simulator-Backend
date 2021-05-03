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

	public SensorActive(String name, ArrayList<Position> positions, ArrayList<Position> triggerArea) {
		super(name, positions, triggerArea);
	}

	public SensorActive() throws MqttPersistenceException, MqttException {
	}
	
	public abstract void trigger2(String command) throws MqttPersistenceException, MqttException;
	
	public void trigger(String command) throws MqttPersistenceException, MqttException, JsonProcessingException {
		trigger2(command);
		output();
	}
	
	public void output() throws MqttPersistenceException, MqttException, JsonProcessingException {
		Output output = new Output(Resources.getSimulator().getClock(),getName(),state);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(output);
	    System.out.println(json);
	    
	    //System.out.println(Resources.getSimulator().getClock().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.nnnnnnnnn")).toString()+" : "+getName()+" : "+state.toString());
		//System.out.println("{\"time\":\""+Resources.getSimulator().getClock().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.nnnnnnnnn")).toString()+"\",\"sensor\":\""+getName()+"\",\"state\":"+mapper.writeValueAsString(state)+"}"); // JSON format
		if (Resources.getSimulator().getMqttOutput() == true) {
			Resources.getMqtt().publish(json);
		}
	}
	
	
	
	private class Output {
		private LocalDateTime time;
		private String sensor;
		private HashMap<String, Object> state;
		
		public Output(LocalDateTime time, String sensor, HashMap<String, Object> state) {
			this.time = time;
			this.sensor = sensor;
			this.state = state;
		}
		
		public Output() {
			
		}

		public String getTime() {
			return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.nnnnnnnnn")).toString();
		}

		public String getSensor() {
			return sensor;
		}

		public HashMap<String, Object> getState() {
			return state;
		}
		
	}


	
	
	
	
}
