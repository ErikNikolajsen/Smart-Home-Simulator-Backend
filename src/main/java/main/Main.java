package main;

import java.awt.Point;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.reflections.Reflections;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.type.filter.AssignableTypeFilter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//import com.hivemq.client.mqtt.MqttClient;
//import com.hivemq.client.mqtt.datatypes.MqttQos;
//import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient;

import entities.*;
import entities.library.*;
import geo.Position;
import utils.*;
import logic.*;
import pathfinding2.AStarGrid;

@SpringBootApplication @ComponentScan(basePackages = {"controllers"} )
public class Main {
	public static void main(String[] args) throws InterruptedException, MqttException, JsonProcessingException, ClassNotFoundException {
		
		/*
		Agent testAgent = new Agent("Agent",0,0);
		System.out.println(testAgent.getPosition().toString());
		Lamp testSensor = new Lamp("Lamp Sensor", false ,1,1);
		System.out.println(testSensor.getPosition().toString());
		
		System.out.println(testAgent.getPosition().distance(testSensor.getPosition()));
  
  
  
  
		
		Log.writeToFile("2020-02-11 13:14:00.099", "Thermometer", "21.3");
		Log.writeToFile("2020-02-11 13:14:01.098", "Kitchen", "True");
		testSensor.onInteraction();
		testSensor.onInteraction();
		*/
		
		/*
		LocalDateTime datetime = LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0); 
		System.out.println(datetime);
		datetime = datetime.plusNanos(16000000);
		System.out.println(datetime);
		*/
		
		
		
		/*
		Resources.setLog(new Log("test"));
		Resources.getLog().createFile();
		Resources.setSimulator(new Simulator());
		Resources.getSimulator().startSimulator();
		*/
		
		/*
		// Paho hivemq
		
		MqttClient client = new MqttClient( 
			    "tcp://broker.mqttdashboard.com:1883", //URI 
			    MqttClient.generateClientId(), //ClientId 
			    new MemoryPersistence()); //Persistence
		
		client.connect();
		
		client.publish( 
			    "my/topic", // topic 
			    "Hello PAHO!".getBytes(), // payload 
			    2, // QoS 
			    false); // retained? 
		
		client.disconnect();
		*/
		/*
		// Paho baeldung
		
		String publisherId = UUID.randomUUID().toString();
		IMqttClient publisher = new MqttClient("tcp://iot.eclipse.org:1883",publisherId);
		*/
		
		/*
		//HiveMQ 2 works
		Mqtt3AsyncClient client = MqttClient.builder()
		        .useMqttVersion3()
		        .identifier(UUID.randomUUID().toString())
		        .serverHost("broker.hivemq.com")
		        .serverPort(1883)
		        .buildAsync();
		
		
		client.connect()
        .whenComplete((connAck, throwable) -> {
            if (throwable != null) {
            	System.out.println("Client connect error: "+throwable);
            } else {
            	System.out.println("Client connected succcessfully");
            }
        });
		
		TimeUnit.NANOSECONDS.sleep(1000000000);
		
		String inputString = "Hello World!";
		byte[] myPayload = inputString.getBytes();
		
		
		client.publishWith()
        .topic("my/topic")
        .payload(myPayload)
        .qos(MqttQos.EXACTLY_ONCE)
        .send()
        .whenComplete((mqtt3Publish, throwable) -> {
            if (throwable != null) {
                // Handle failure to publish
            	System.out.println("Publish error: "+throwable);
            } else {
                // Handle successful publish, e.g. logging or incrementing a metric
            	System.out.println("Published successfully");
            }
        });
		System.out.println("end of the line");
		*/
		
		
		/*
		// MqttPaho class
		MqttPaho mqtt = new MqttPaho();
		mqtt.connect();
		mqtt.subscribe("my/topic");
		mqtt.publish("my/topic","payload1");
		mqtt.publish("my/topic","payload2");
		mqtt.publish("my/topic","payload3");
		TimeUnit.NANOSECONDS.sleep(1000000000);
		mqtt.disconnectMqtt();
		*/
		
		/*
		// MqttHive class
		MqttHive mqtt = new MqttHive();
		mqtt.connect();
		mqtt.subscribe("my/topic");
		TimeUnit.NANOSECONDS.sleep(1000000000);
		mqtt.publish("my/topic","asdfasd");
		mqtt.disconnect();
		*/
		
		/*
		ArrayList<Sensor> sensorList = new ArrayList<Sensor>();
		sensorList.add(new SensorChild("Light_1", new ArrayList<Position>(Arrays.asList(new Position(3,2))), new ArrayList<Position>(Arrays.asList(new Position(2,2))),300000000,1337));
		sensorList.add(new Sensor("Light_1", new ArrayList<Position>(Arrays.asList(new Position(3,2))), new ArrayList<Position>(Arrays.asList(new Position(2,2))),300000000));
		System.out.println(sensorList.toString());
		*/
		/*
		Random rand = new Random();
		System.out.println(rand.nextInt(5));
		*/
		/*
		double wait10hours = 86400.2;
		long secondInNanos = 1000000000;
		long size = (long)(secondInNanos * wait10hours);
		System.out.println(size);
		
		System.out.println(Long.parseLong("86400.2"));
		*/
		
		/*
		// Output
		HashMap<String, Object> capitalCities = new HashMap<String, Object>();
		capitalCities.put("England", "London");
		capitalCities.put("Germany", "Berlin");
	    capitalCities.put("Norway", "Oslo");
	    
	    LocalDateTime datetime = LocalDateTime.parse("2020-01-01T16:11:34.500000000"); 

	    String name = "John Doe";
	    Output output = new Output(datetime, name, capitalCities);
	    
	    String json = new ObjectMapper().writeValueAsString(output);
	    System.out.println(json);
	    */
		/*
	    ArrayList<Position> p = new ArrayList<Position>();
	    for (int i = 0; i < 1000000; i++) {
	    	p.add(new Position(1+i,2+i));
	    }
	    
	    
	    
	    Position target = new Position(1000000,1100001);
		long start = System.nanoTime();
		p.contains(target);
		long time = System.nanoTime() - start;
		
		System.out.println(time);
		*/
		/*
		Reflections reflections = new Reflections("com.mycompany");    
		Set<Class<? extends SensorActive>> classes = reflections.getSubTypesOf(SensorActive.class);
		System.out.println(classes.toString());
		*/
		/*
		ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
		provider.addIncludeFilter(new AssignableTypeFilter(Sensor.class));

		ArrayList<String> classesSensor = new ArrayList<String>();
		
		// scan in org.example.package
		Set<BeanDefinition> components = provider.findCandidateComponents("entities.library");
		for (BeanDefinition component : components)
		{
		    Class cls = Class.forName(component.getBeanClassName());
		    // use class cls found
		    classesSensor.add(cls.getName());
		}
		System.out.println(classesSensor);
		 */
		SpringApplication.run(Main.class, args);
		
	  
	}

}





















