package main;

import logic.Simulator;
import utils.Log;

public class Model {
	private static Log log;
	private static Simulator simulator;
	

	//Accessors and Mutators
	public static Log getLog() {
		return log;
	}
	public static void setLog(Log log) {
		Model.log = log;
	}
	public static Simulator getSimulator() {
		return simulator;
	}
	public static void setSimulator(Simulator simulator) {
		Model.simulator = simulator;
	}
	
	
	
	
}

