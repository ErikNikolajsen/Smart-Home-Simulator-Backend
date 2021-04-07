package main;

import java.awt.Point;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import entities.*;
import entities.library.*;
import geo.Position;
import utils.*;
import logic.*;
import pathfinding2.AStarGrid;

@SpringBootApplication @ComponentScan(basePackages = {"controllers"} )
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
		
		/*
		LocalDateTime datetime = LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0); 
		System.out.println(datetime);
		datetime = datetime.plusNanos(16000000);
		System.out.println(datetime);
		*/
		
		
		//Resources.setLog(new Log("test"));
		//Resources.getLog().createFile();
		//Resources.setSimulator(new Simulator());
		//Resources.getSimulator().startSimulator();
		
		
		SpringApplication.run(Main.class, args);
		
	}

}
