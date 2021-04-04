package main;

import logic.Input;
import logic.Simulator;
import pathfinding2.AStarGrid;
import utils.Log;

public class Resources {
	private static Log log;
	private static Simulator simulator;
	private static Input input;
	private static AStarGrid aStarGrid;
	

	//Accessors and Mutators
	public static Log getLog() {
		return log;
	}
	public static void setLog(Log log) {
		Resources.log = log;
	}
	public static Simulator getSimulator() {
		return simulator;
	}
	public static void setSimulator(Simulator simulator) {
		Resources.simulator = simulator;
	}
	public static Input getInput() {
		return input;
	}
	public static void setInput(Input input) {
		Resources.input = input;
	}
	public static AStarGrid getaStarGrid() {
		return aStarGrid;
	}
	public static void setaStarGrid(AStarGrid aStarGrid) {
		Resources.aStarGrid = aStarGrid;
	}
	
	
	
	
	
}

