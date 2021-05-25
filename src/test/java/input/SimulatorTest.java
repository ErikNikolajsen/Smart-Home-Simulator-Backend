package input;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import controllers.RoomConfigController;
import controllers.SimulationController;
import controllers.SystemController;
import entities.Agent;
import entities.Entity;
import entities.Floorplan;
import entities.SensorActive;
import entities.SensorPassive;
import entities.library.PresenceSensor;
import entities.library.Television;
import geo.Position;
import logic.Input;
import logic.Simulator;
import main.Resources;
import pathfinding.AStarGrid;

class SimulatorTest {
	
	
	SimulationController simulationController = new SimulationController();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Floorplan floorplan = new Floorplan(
				1,
				5,
				5,
				new Agent(new Position(0,0), 1.0),
				new ArrayList<Position>(Arrays.asList(
					new Position(0,1),
					new Position(0,3),
					new Position(1,1),
					new Position(2,3),
					new Position(3,3),
					new Position(4,3)
				)),
				new ArrayList<SensorActive>(Arrays.asList(
				)),
				new ArrayList<SensorPassive>(Arrays.asList(
				)),
				new ArrayList<Entity>(Arrays.asList(
				))
			);
		
		Resources.setFloorplan(floorplan);
		
		Television television = new Television();
		television.setName("television");
		television.setPhysicalArea(new ArrayList<Position>(Arrays.asList(new Position(3,2))));
		television.setInteractArea(new ArrayList<Position>(Arrays.asList(new Position(3,1))));
		television.setWalkable(false);
		Resources.getFloorplan().setActiveSensors(new ArrayList<SensorActive>(Arrays.asList(television)));
		
		PresenceSensor doorSensor = new PresenceSensor();
		doorSensor.setName("door");
		doorSensor.setPhysicalArea(new ArrayList<Position>(Arrays.asList(new Position(1,3))));
		doorSensor.setInteractArea(new ArrayList<Position>(Arrays.asList(new Position(1,3))));
		doorSensor.setWalkable(true);
		doorSensor.setTriggerFrequency(1000000000L);
		Resources.getFloorplan().setPassiveSensors(new ArrayList<SensorPassive>(Arrays.asList(doorSensor)));
		
		Entity vestibule = new Entity();
		vestibule.setName("vestibule");
		vestibule.setPhysicalArea(new ArrayList<Position>(Arrays.asList(new Position(0,0), new Position(1,0))));
		vestibule.setInteractArea(new ArrayList<Position>(Arrays.asList(new Position(0,0), new Position(1,0))));
		vestibule.setWalkable(true);
		Resources.getFloorplan().setEntities(new ArrayList<Entity>(Arrays.asList(vestibule)));
		
		Resources.setaStarGrid(new AStarGrid(Resources.getFloorplan()));
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		Resources.setaStarGrid(null);
		Resources.setFloorplan(null);
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		Resources.setInput(null);
		Resources.setLog(null);
		Resources.setMqtt(null);
		Resources.setRandom(null);
		Resources.setSimulator(null);
	}

	@Test
	void testSimulationSuccess() throws MqttPersistenceException, JsonProcessingException, InterruptedException, MqttException {
		
		Input input = new Input("goto(4,4);wait(5);goto(vestibule);interact(television,ON)");
		simulationController.postInput(input);
		
		Simulator simulator = new Simulator(
			LocalDateTime.parse("2020-01-01T00:00:00.000000000", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.nnnnnnnnn")),
			true,
			1.0,
			false,
			0,
			"broker.hivemq.com",
			"1883",
			"smarthome",
			7357L
		);
		simulationController.postSimulator(simulator);	
	}

	@Test
	void testSimulationSuccess2() throws MqttPersistenceException, JsonProcessingException, InterruptedException, MqttException {
		
		Input input = new Input("goto(1,3);");
		simulationController.postInput(input);
		
		Simulator simulator = new Simulator(
			LocalDateTime.parse("2020-01-01T00:00:00.000000000", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.nnnnnnnnn")),
			false,
			0.1,
			true,
			0,
			"broker.hivemq.com",
			"1883",
			"smarthome",
			null
		);
		simulationController.postSimulator(simulator);	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
