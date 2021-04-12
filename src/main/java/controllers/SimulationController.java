package controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import logic.Input;
import logic.Simulator;
import main.Resources;
import pathfinding2.AStarGrid;
import utils.Log;


@RestController
@RequestMapping("/api/simulation/")
@CrossOrigin
public class SimulationController {
	
	@PostMapping("/input") // requires the prior instantiation of a Floorplan object
	public String input(@RequestBody String inputString) {
		Resources.setInput(new Input(inputString));
		return Resources.getInput().test();
	}
	
	@GetMapping("/simulator")
	public @ResponseBody void runSimulation() {
		Resources.setLog(new Log("test"));
		Resources.getLog().createFile();
		Resources.setSimulator(new Simulator(
				Resources.getInput(),
				Resources.getFloorplan(),
				Resources.getaStarGrid()
				));
		Resources.getSimulator().startSimulator();
	}
	
	
    
}



















/*

// test whole input at once
if (inputString.matches("((\\s*goto\\(\\d+,\\d+\\)\\s*;\\s*)|(\\s*interact\\(\\w+\\)\\s*;\\s*)|(\\s*wait\\(\\d+\\)\\s*;\\s*))+")) { 
	return "consumed"; //Input is correct
} else {
	return "error"; //Input contains error
}

// test input per statement and return statement element with error
String[] inputArray = inputString.split(";"); // inserts statements into an array
for (int i = 0; i < inputArray.length; i++) { 
	if (inputArray[i].matches("(\\s*goto\\(\\s*\\d+\\s*,\\s*\\d+\\s*\\)\\s*)|(\\s*interact\\(\\s*\\w+\\s*\\)\\s*)|(\\s*wait\\(\\s*\\d+\\s*\\)\\s*)")) { 
		inputArray[i] = inputArray[i].replaceAll("\\s",""); // removes whitespace and adds statement to inputArray
	} else {
		return "ERROR: syntax error in statement "+(i+1)+": "+inputArray[i]; // returns error-message
	}
}
return "consumed";
*/