package controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import entities.*;
import entities.library.*;
import utils.*;
import logic.*;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		
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
		Model.setLog(new Log("test"));
		Model.getLog().createFile();
		Model.setSimulator(new Simulator());
		Model.getSimulator().startSimulator();
		
		
		/*
		LocalDateTime datetime = LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0); 
		System.out.println(datetime);
		datetime = datetime.plusNanos(16000000);
		System.out.println(datetime);
		*/
		
	}

}
