package controllers;

import java.lang.System.Logger;

import entities.*;
import utils.*;
import logic.*;

public class Main {
	
	
	
	public static void main(String[] args) throws InterruptedException {
		Entity testAgent = new Entity("Agent",0,0);
		System.out.println(testAgent.getPosition().toString());
		Entity testSensor = new Entity("Sensor",1,1);
		System.out.println(testSensor.getPosition().toString());
		if (testAgent.getPosition().equals(testSensor.getPosition())) {
			System.out.println("Touch!");
		}
		System.out.println(testAgent.getPosition().distance(testSensor.getPosition()));
		//printGrid(testAgent, testSensor);
		//move(testAgent,"left");
		//printGrid(testAgent, testSensor);
		Log.createFile("test");
		Log.writeToFile("test","2020-02-11 13:14:00.099", "Thermometer", "21.3");
		Log.writeToFile("test","2020-02-11 13:14:01.098", "Kitchen", "True");
		Simulator world1 = new Simulator();
		world1.startSimulator();
		
	}
	
	public static void printGrid(Entity agent, Entity sensor) {
		for (int i = 10; i >= -10; i--) {
			for (int j = -10; j <= 10; j++) {
				if (agent.getPosition().getX() == j && agent.getPosition().getY() == i) {
					System.out.print("A");
				} else if (sensor.getPosition().getX() == j && sensor.getPosition().getY() == i) {
					System.out.print("S");
				} else {
					System.out.print("O");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void move(Entity ent, String direction) {
		if (direction.equals("left")) {
			ent.getPosition().setLocation(ent.getPosition().x-1,ent.getPosition().y);
		}
	}


}
