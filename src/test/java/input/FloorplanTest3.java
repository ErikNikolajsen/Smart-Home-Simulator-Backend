package input;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import controllers.RoomConfigController;
import controllers.SimulationController;
import entities.Agent;
import entities.Entity;
import entities.Floorplan;
import entities.SensorActive;
import entities.SensorPassive;
import geo.Position;
import logic.Input;
import logic.Simulator;

class FloorplanTest3 {

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testTest() throws MqttPersistenceException, JsonProcessingException, InterruptedException, MqttException {
		RoomConfigController roomConfigController = new RoomConfigController();
		SimulationController  simulationController = new SimulationController();
		
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
		
		roomConfigController.postFloorplan(floorplan);
		
		Input input = new Input("goto(4,4);");
		
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
		//simulationController.runSimulation(simulator);
			
		assertEquals("consumed",simulationController.postSimulator(simulator));
	}

	@Test
	void testIsWithin() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testGetAllSensors() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testGetAllEntities() {
		fail("Not yet implemented"); // TODO
	}

}
