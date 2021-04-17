package logic;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import entities.Agent;
import entities.Floorplan;
import entities.Sensor;
import geo.Position;
import main.MqttPaho;
import main.Resources;
import pathfinding2.AStarGrid;
import pathfinding2.AStarNode;

public class Simulator {
	
	//private int tick = 0; // OLD
	//private long nsPerTick = 30000000; //UPS == 1000000000 / NS_PER_TICK // OLD
	private LocalDateTime clock = LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0); 
	//long triggerFrequency = 300000000; // OLD
	boolean instantSimulation = false;
	double timeFactor = 1.0; // how many real-time seconds should a virtual seconds take 
	
	
	private Input input;
	private Floorplan floorplan;
	private AStarGrid grid;
	
	private Agent agent;

	/**
	 * @param nsPerTick
	 * @param clock
	 * @param input
	 * @param floorplan
	 * @param grid
	 */
	public Simulator(Input input, Floorplan floorplan, AStarGrid grid) { //long nsPerTick, LocalDateTime clock, 
		//this.nsPerTick = nsPerTick;
		//this.clock = clock;
		//this.realtime;
		this.input = input;
		this.floorplan = floorplan;
		this.grid = grid;
		
		this.agent = floorplan.getAgent();
		
	}
	
	// next-event time progression discrete-event simulation
	public void startSimulator() throws InterruptedException {
		String[] statementArray = input.getInputArray();
		String gotoPattern = input.getGotopattern();
		String interactPattern = input.getInteractpattern();
		String waitPattern = input.getWaitpattern();
		
		System.out.println("*** Simulation has started ***"); //test
		
		for (String statement : statementArray) {
			System.out.println("* "+statement+":"); //test
			
			// GOTO
			if (statement.matches(gotoPattern)) {
				Position gotoPosition = new Position(
					Integer.parseInt(statement.replaceAll(gotoPattern, "$1")),
					Integer.parseInt(statement.replaceAll(gotoPattern, "$2"))
				);
				gotoInstructions(gotoPosition);
			
			// WAIT
			} else if (statement.matches(waitPattern)) {
				long waitTime = Long.parseLong(statement.replaceAll(waitPattern, "$1")) * 1000000000; // converts seconds to nanoseconds
				waitInstructions(waitTime);
			
			// INTERACT
			} else if (statement.matches(interactPattern)) {
				String sensorName = statement.replaceAll(gotoPattern, "$1");
				interactInstructions(sensorName);
			}
		}
		
		System.out.println("*** Simulation has ended ***"); //test
	}

	private void gotoInstructions(Position gotoPosition) throws InterruptedException {
		List<AStarNode> path;
		path = grid.getPath(
				agent.getPosition().getX(), 
				agent.getPosition().getY(), 
				gotoPosition.getX(), 
				gotoPosition.getY());
		
		for (AStarNode node : path) {
			double distance = agent.getPosition().distance(new Position(node.getX(),node.getY()));
			long time = (long) ((distance / agent.getSpeed()) * 1000000000);
			
			triggerPassiveSensors(time);
			
			agent.setPosition(node.getX(), node.getY()); // moves agent
			
			System.out.println(clock+" : "+agent.getPosition().toString()); // print time & position
		}
	}

	private void waitInstructions(long waitTime) throws InterruptedException {
		triggerPassiveSensors(waitTime);
		System.out.println(clock+" : "+agent.getPosition().toString()); // print time & position
	}
	
	private void interactInstructions(String sensorName) {
		
	}

	private void updateTime(long nanos) throws InterruptedException {
		clock = clock.plusNanos(nanos);
		
		if (instantSimulation == false) {
			TimeUnit.NANOSECONDS.sleep( (long) Math.round(nanos * timeFactor));
		}
		
	}
	
	private void triggerPassiveSensors(long time) throws InterruptedException {
		LocalDateTime newTileTime = clock.plusNanos(time);
		ArrayList<TriggerEvent> eventList = new ArrayList<TriggerEvent>();
		for (Sensor sensor : grid.getNode(agent.getPosition().getX(), agent.getPosition().getY()).getPassiveTriggers()) { // for all passive sensors in the tile where the agent is present
			long i = 0;
			if (sensor.getLastTriggerTime() != null && sensor.getLastTriggerTime().until(clock,ChronoUnit.NANOS) < sensor.getTriggerFrequency()) {
				i = -sensor.getLastTriggerTime().until(clock,ChronoUnit.NANOS) + sensor.getTriggerFrequency();
			}
			for (; i < time; i = i + sensor.getTriggerFrequency()) {
				eventList.add(new TriggerEvent(sensor,clock.plusNanos(i)));
				sensor.setLastTriggerTime(clock.plusNanos(i));
			}
		}
		eventList.sort(Comparator.comparing(TriggerEvent::getDateTime));
		for (TriggerEvent triggerEvent : eventList) {
			updateTime(clock.until(triggerEvent.getDateTime(),ChronoUnit.NANOS));
			System.out.println(clock+" : "+triggerEvent.getSensor().getName()+" has been triggered!");
		}
		updateTime(clock.until(newTileTime,ChronoUnit.NANOS));
	}
		
	
	/*
	// fixed-increment time progression discrete-event simulation
	public void startSimulator() {
		String[] statementArray = input.getInputArray();
		String gotoPattern = input.getGotopattern();
		Agent agent = floorplan.getAgent();
		
		List<AStarNode> path;
		double speed = floorplan.getAgent().getSpeed();
		
		System.out.println("Simulator has started"); //test
		
		for (String statement : statementArray) {
			System.out.println(statement); //test
			if (statement.matches(gotoPattern)) {
				Position gotoPosition = new Position(
					Integer.parseInt(statement.replaceAll(gotoPattern, "$1")),
					Integer.parseInt(statement.replaceAll(gotoPattern, "$2"))
				);
				path = grid.getPath(
						agent.getPosition().getX(), 
						agent.getPosition().getY(), 
						gotoPosition.getX(), 
						gotoPosition.getY());
				
				for (AStarNode node : path) {
					double distance = agent.getPosition().distance(new Position(node.getX(),node.getY()));
					long time = (long) ((distance / speed) * 1000000000);
					
					long passedTime = 0; 
					//Simulation Logic-Loop
					while (passedTime<time) {
						long start = System.nanoTime();
						
						System.out.println(clock+" : "+agent.getPosition().toString());
						
						for (Sensor sensor : node.getPassiveTriggers()) {
							System.out.println(clock+" : "+sensor.getName()+" has been triggered!");
						}
						
						
						
						
						
						clock = clock.plusNanos(nsPerTick); //updates clock
						passedTime = passedTime + nsPerTick;
						
						try {
							TimeUnit.NANOSECONDS.sleep(start + nsPerTick - System.nanoTime());
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} //thread sleeps for real-time rendering
						
						//System.out.println(start + nsPerTick - System.nanoTime()); //computation time left per loop 
					}
					clock = clock.minusNanos(passedTime-time);
					agent.setPosition(node.getX(), node.getY());
				}
				
				
			}
			
			
		}
	}
	*/
	
	

	/*
	public void startSimulator() {

	//Pre-simulation operations
	Sensor testSensor = new Sensor("Light_1", new ArrayList<Position>(Arrays.asList(new Position(3,2))), new ArrayList<Position>(Arrays.asList(new Position(2,2))));
	
	//Simulation Logic-Loop
	while (tick<60) {
		tick++;
		long start = System.nanoTime();
		clock = clock.plusNanos(nsPerTick); //updates clock
		
		processInput();
		update();
		render();
		
		//TimeUnit.NANOSECONDS.sleep(start + nsPerTick - System.nanoTime()); //thread sleeps for real-time rendering
		System.out.println(start + nsPerTick - System.nanoTime()); //computation time left per loop 
		System.out.println(clock);
		testSensor.onInteraction();
		//System.out.print("E");
		
	}

	}
	
	public void startRenderingSimulator() throws InterruptedException {
		//Pre-simulation operations
		Sensor testSensor = new Sensor("Light_1", new ArrayList<Position>(Arrays.asList(new Position(3,2))), new ArrayList<Position>(Arrays.asList(new Position(2,2))));
		
		while (tick<60) {
			long start = System.nanoTime();
			clock = clock.plusNanos(nsPerTick); //updates clock
			processInput();
			update();
			render();
			TimeUnit.NANOSECONDS.sleep(start + nsPerTick - System.nanoTime()); //thread sleeps for real-time rendering
			
			System.out.println(start + nsPerTick - System.nanoTime()); //computation time left per loop 
			System.out.println(clock);
			testSensor.onInteraction();
			System.out.print("E");
			
			tick++;
		}
	}
	*/

	private void processInput() {
		
	}

	private void render() {
		// TODO Auto-generated method stub
		
	}

	private void update() {
		// TODO Auto-generated method stub
		
	}

	public void setClock(LocalDateTime clock) {
		this.clock = clock;
	}

	public void setInstantSimulation(boolean instantSimulation) {
		this.instantSimulation = instantSimulation;
	}

	
	
	
}

















// Old way of triggering passive sensors

/*
for (Sensor sensor : grid.getNode(agent.getPosition().getX(), agent.getPosition().getY()).getPassiveTriggers()) {
	int triggerAmount = (int) (time / triggerFrequency); // trigger amount in the time slice where agent moves from A to B
	if (time % triggerFrequency == 0) { // avoid edge-case where sensor in tile A is trigger, agent moves from A to B, and a sensor in tile B is triggered - all at the same time.
		triggerAmount--;
	}
	
	for (int i = 0; i <= triggerAmount; i++) {
		System.out.println(clock.plusNanos(i*triggerFrequency)+" : "+sensor.getName()+" has been triggered!");
	}
}

clock = clock.plusNanos(time); //updates clock
*/