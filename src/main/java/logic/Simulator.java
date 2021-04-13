package logic;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	private int tick = 0; // OLD
	private long nsPerTick = 30000000; //UPS == 1000000000 / NS_PER_TICK // OLD
	private LocalDateTime datetime = LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0); 
	long triggerFrequency = 300000000;
	
	private Input input;
	private Floorplan floorplan;
	private AStarGrid grid;
	
	private Agent agent;

	/**
	 * @param nsPerTick
	 * @param datetime
	 * @param input
	 * @param floorplan
	 * @param grid
	 */
	public Simulator(Input input, Floorplan floorplan, AStarGrid grid) { //long nsPerTick, LocalDateTime datetime, 
		//this.nsPerTick = nsPerTick;
		//this.datetime = datetime;
		this.input = input;
		this.floorplan = floorplan;
		this.grid = grid;
		
		this.agent = floorplan.getAgent();
		
	}
	
	// next-event time progression discrete-event simulation
	public void startSimulator() {
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
				long waitTime = Long.parseLong(statement.replaceAll(waitPattern, "$1")) * 1000000000;
				waitInstructions(waitTime);
			
			// INTERACT
			} else if (statement.matches(interactPattern)) {
				String sensorName = statement.replaceAll(gotoPattern, "$1");
				interactInstructions(sensorName);
			}
		}
	}

	private void gotoInstructions(Position gotoPosition) {
		List<AStarNode> path;
		path = grid.getPath(
				agent.getPosition().getX(), 
				agent.getPosition().getY(), 
				gotoPosition.getX(), 
				gotoPosition.getY());
		
		for (AStarNode node : path) {
			double distance = agent.getPosition().distance(new Position(node.getX(),node.getY()));
			long time = (long) ((distance / agent.getSpeed()) * 1000000000);
			
			for (Sensor sensor : grid.getNode(agent.getPosition().getX(), agent.getPosition().getY()).getPassiveTriggers()) {
				int triggerAmount = (int) (time / triggerFrequency); // trigger amount in the time slice where agent moves from A to B
				if (time % triggerFrequency == 0) { // avoid edge-case where sensor in tile A is trigger, agent moves from A to B, and a sensor in tile B is triggered - all at the same time.
					triggerAmount--;
				}
				
				for (int i = 0; i <= triggerAmount; i++) {
					System.out.println(datetime.plusNanos(i*triggerFrequency)+" : "+sensor.getName()+" has been triggered!");
				}
			}
			
			datetime = datetime.plusNanos(time); //updates datetime
			agent.setPosition(node.getX(), node.getY()); // moves agent
			
			System.out.println(datetime+" : "+agent.getPosition().toString()); // print time & position
		}
	}
	
	
	private void waitInstructions(long waitTime) {
		for (Sensor sensor : grid.getNode(agent.getPosition().getX(), agent.getPosition().getY()).getPassiveTriggers()) { // for all passive sensors in the tile where the agent is present
			int triggerAmount = (int) (waitTime / triggerFrequency); // trigger amount in the time slice where agent waits
			if (waitTime % triggerFrequency == 0) { // avoid edge-case where sensor in tile A is trigger, agent moves from A to B, and a sensor in tile B is triggered - all at the same time.
				triggerAmount--;
			}
			
			for (int i = 0; i <= triggerAmount; i++) {
				System.out.println(datetime.plusNanos(i*triggerFrequency)+" : "+sensor.getName()+" has been triggered!");
			}
			
		}
		datetime = datetime.plusNanos(waitTime); //updates datetime
		System.out.println(datetime+" : "+agent.getPosition().toString()); // print time & position
	}
	
	private void interactInstructions(String sensorName) {
		
	}

	private void updateTime(long nanos) throws InterruptedException {
		datetime.plusNanos(nanos);
		TimeUnit.NANOSECONDS.sleep(nanos);
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
						
						System.out.println(datetime+" : "+agent.getPosition().toString());
						
						for (Sensor sensor : node.getPassiveTriggers()) {
							System.out.println(datetime+" : "+sensor.getName()+" has been triggered!");
						}
						
						
						
						
						
						datetime = datetime.plusNanos(nsPerTick); //updates datetime
						passedTime = passedTime + nsPerTick;
						
						try {
							TimeUnit.NANOSECONDS.sleep(start + nsPerTick - System.nanoTime());
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} //thread sleeps for real-time rendering
						
						//System.out.println(start + nsPerTick - System.nanoTime()); //computation time left per loop 
					}
					datetime = datetime.minusNanos(passedTime-time);
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
		datetime = datetime.plusNanos(nsPerTick); //updates datetime
		
		processInput();
		update();
		render();
		
		//TimeUnit.NANOSECONDS.sleep(start + nsPerTick - System.nanoTime()); //thread sleeps for real-time rendering
		System.out.println(start + nsPerTick - System.nanoTime()); //computation time left per loop 
		System.out.println(datetime);
		testSensor.onInteraction();
		//System.out.print("E");
		
	}

	}
	
	public void startRenderingSimulator() throws InterruptedException {
		//Pre-simulation operations
		Sensor testSensor = new Sensor("Light_1", new ArrayList<Position>(Arrays.asList(new Position(3,2))), new ArrayList<Position>(Arrays.asList(new Position(2,2))));
		
		while (tick<60) {
			long start = System.nanoTime();
			datetime = datetime.plusNanos(nsPerTick); //updates datetime
			processInput();
			update();
			render();
			TimeUnit.NANOSECONDS.sleep(start + nsPerTick - System.nanoTime()); //thread sleeps for real-time rendering
			
			System.out.println(start + nsPerTick - System.nanoTime()); //computation time left per loop 
			System.out.println(datetime);
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

	public LocalDateTime getDatetime() {
		return datetime;
	}
	
	
}
