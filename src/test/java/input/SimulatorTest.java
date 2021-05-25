package input;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.Agent;
import entities.Entity;
import entities.Floorplan;
import entities.SensorActive;
import entities.SensorPassive;
import geo.Position;
import logic.Input;
import logic.Simulator;
import main.Resources;

class SimulatorTest {

	@BeforeEach
	void setUp() throws Exception {
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

		
		Input input = new Input("goto(4,4);");
		
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
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		fail("Not yet implemented"); // TODO
	}

}
