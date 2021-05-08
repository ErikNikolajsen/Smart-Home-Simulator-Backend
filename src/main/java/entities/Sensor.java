package entities;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import geo.Position;
import main.Resources;


@JsonTypeInfo(use = Id.CLASS,
include = JsonTypeInfo.As.PROPERTY,
property = "type")
public abstract class Sensor extends Entity{
	
	protected HashMap<String, Object> state = new HashMap<String, Object>();
	ObjectMapper mapper = new ObjectMapper();
	
	public Sensor(String name, ArrayList<Position> positions, ArrayList<Position> triggerArea, Boolean walkable) {
		super(name, positions, triggerArea, walkable);
	}
	
	public Sensor() {
	}
	
	public void output() throws MqttPersistenceException, MqttException, JsonProcessingException {
		//Resources.getLog().writeToFile(Resources.getSimulator().getClock().toString(), getName(), "true");
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
