package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import controllers.RoomConfigController;
import controllers.SimulationController;
import entities.Floorplan;
import logic.Simulator;

@SpringBootApplication @ComponentScan(basePackages = {"controllers"} )
public class Main {
	
	private static boolean websocketOutput;
	
	public static void main(String[] args) throws InterruptedException, MqttException, ClassNotFoundException, IOException {
		
        if (args.length == 0) {
        	websocketOutput = true;
			SpringApplication.run(Main.class, args);
			System.out.println(" ___    _____    ____  _                 _       _             \r\n"
					+ "|_ _|__|_   _|  / ___|(_)_ __ ___  _   _| | __ _| |_ ___  _ __ \r\n"
					+ " | |/ _ \\| |____\\___ \\| | '_ ` _ \\| | | | |/ _` | __/ _ \\| '__|\r\n"
					+ " | | (_) | |_____|__) | | | | | | | |_| | | (_| | || (_) | |   \r\n"
					+ "|___\\___/|_|    |____/|_|_| |_| |_|\\__,_|_|\\__,_|\\__\\___/|_|  \r\n"
					+ "============================================================\r\n"
					+ "                                                    (v1.0.0)");
        } else {
        	websocketOutput = false;
            RoomConfigController roomConfigController = new RoomConfigController();
            SimulationController simulationController = new SimulationController();
            
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            
            roomConfigController.postFloorplan(mapper.readValue(Paths.get(args[0]).toFile(), Floorplan.class));
            simulationController.postInput(Files.readString(Path.of(args[1])));
            simulationController.postSimulator(mapper.readValue(Paths.get(args[2]).toFile(), Simulator.class));
            System.exit(0);
        }
    }

	public static boolean isWebsocketOutput() {
		return websocketOutput;
	}
}





















