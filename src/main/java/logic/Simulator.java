package logic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import entities.Sensor;
import geo.Position;

public class Simulator {
	
	private long nsPerTick = 16000000; //UPS == 1000000000 / NS_PER_TICK
	private int tick = 0;
	private LocalDateTime datetime = LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0); 
	/*
	public void startSimulator() throws InterruptedException {
		//Pre-simulation operations
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(new Position(3,2));
		ArrayList<Position> triggerArea = new ArrayList<Position>();
		triggerArea.add(new Position(2,2));
		triggerArea.add(new Position(3,1));
		triggerArea.add(new Position(4,2));
		Sensor light1 = new Sensor("light1", positions, triggerArea);
		
		//Simulation Logic-Loop
		while (tick<60) {
			long start = System.nanoTime();
			datetime = datetime.plusNanos(nsPerTick); //updates datetime
			
			processInput();
			update();
			render();
			
			//TimeUnit.NANOSECONDS.sleep(start + nsPerTick - System.nanoTime()); //thread sleeps for real-time rendering
			//System.out.println(start + nsPerTick - System.nanoTime()); //computation time left per loop 
			//System.out.println(datetime);
			testSensor.onInteraction();
			//System.out.print("E");
			tick++;
		}
	}
	*/
	
	public void startSimulator() throws InterruptedException {
		//Pre-simulation operations
		Sensor testSensor = new Sensor("Light_1", new ArrayList<Position>(Arrays.asList(new Position(3,2))), new ArrayList<Position>(Arrays.asList(new Position(2,2))));
		
		//Simulation Logic-Loop
		while (tick<60) {
			long start = System.nanoTime();
			datetime = datetime.plusNanos(nsPerTick); //updates datetime
			
			processInput();
			update();
			render();
			
			//TimeUnit.NANOSECONDS.sleep(start + nsPerTick - System.nanoTime()); //thread sleeps for real-time rendering
			System.out.println(start + nsPerTick - System.nanoTime()); //computation time left per loop 
			System.out.println(datetime);
			testSensor.onInteraction();
			//System.out.print("E");
			tick++;
		}
	}
	
	public void startRenderingSimulator() throws InterruptedException {
		while (tick<60) {
			long start = System.nanoTime();
			datetime = datetime.plusNanos(nsPerTick);
			processInput();
			update();
			render();
			TimeUnit.NANOSECONDS.sleep(start + nsPerTick - System.nanoTime());
			System.out.print("E");
			tick++;
		}
	}
	

	private void processInput() {
		
		
		
	}

	private void render() {
		// TODO Auto-generated method stub
		
	}

	private void update() {
		// TODO Auto-generated method stub
		
	}

	public LocalDateTime getDatetime() {
		return datetime;
	}
	
	
}
