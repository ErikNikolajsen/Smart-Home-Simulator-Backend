package logic;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import entities.library.Lamp;

public class Simulator {
	
	private long nsPerTick = 16000000; //UPS == 1000000000 / NS_PER_TICK
	private int tick = 0;
	private LocalDateTime datetime = LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0); 
	
	public void startSimulator() throws InterruptedException {
		//Pre-simulation operations
		Lamp testSensor = new Lamp("Lamp Sensor", false ,1 ,1);
		
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
