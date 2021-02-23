package controllers;

import entities.*;
import entities.library.*;
import utils.*;
import logic.*;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		Agent testAgent = new Agent("Agent",0,0);
		System.out.println(testAgent.getPosition().toString());
		Lamp testSensor = new Lamp("Lamp Sensor", false ,1,1);
		System.out.println(testSensor.getPosition().toString());
		
		System.out.println(testAgent.getPosition().distance(testSensor.getPosition()));

		Log.setFileName("test");
		Log.createFile();
		Log.writeToFile("2020-02-11 13:14:00.099", "Thermometer", "21.3");
		Log.writeToFile("2020-02-11 13:14:01.098", "Kitchen", "True");
		testSensor.onInteraction();
		testSensor.onInteraction();
		
		Simulator world1 = new Simulator();
		world1.startSimulator();
		
	}

}
