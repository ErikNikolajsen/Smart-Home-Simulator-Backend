package logic;

import main.Resources;
import pathfinding2.NodeState;

public class Input {
	
	private String input;
	private String[] inputArray;
	private boolean tested = false;
	
	private final static String gotoPattern = "\\s*goto\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)\\s*";
	private final static String interactPattern = "\\s*interact\\(\\s*(\\w+)\\s*\\)\\s*";
	private final static String waitPattern = "\\s*wait\\(\\s*(\\d+)\\s*\\)\\s*";

	public Input(String input) {
		this.input = input;
		//this.inputArray = input.split(";"); // splits statements into array elements;
	}
	
	/* Tests:
	 * Syntax
	 * coordinates are within grid boundaries
	 * coordinates are WALKABLE
	 * interact IDs exist and is interactable
	 * 
	 */
	public String test() {
	inputArray = this.input.split(";");
	for (int i = 0; i < inputArray.length; i++) { 
		if (inputArray[i].matches(gotoPattern)) { 
			// tests if coordinate are within grid boundaries
			if (!Resources.getaStarGrid().isWithin(Integer.parseInt(inputArray[i].replaceAll(gotoPattern, "$1")), Integer.parseInt(inputArray[i].replaceAll(gotoPattern, "$2")))) {
				return "ERROR: coordinate is out of bounds in statement "+(i+1)+": "+inputArray[i]; // returns error-message
			}
			// tests if coordinate is walkable
			if (Resources.getaStarGrid().getNodeState(Integer.parseInt(inputArray[i].replaceAll(gotoPattern, "$1")), Integer.parseInt(inputArray[i].replaceAll(gotoPattern, "$2"))) == NodeState.NOT_WALKABLE) {
				return "ERROR: target coordinate is not walkable in statement "+(i+1)+": "+inputArray[i]; // returns error-message
			}
			inputArray[i] = inputArray[i].replaceAll("\\s",""); // removes whitespace
		} else if (inputArray[i].matches(interactPattern)) {
			inputArray[i] = inputArray[i].replaceAll("\\s",""); // removes whitespace
		} else if (inputArray[i].matches(waitPattern)) {
			inputArray[i] = inputArray[i].replaceAll("\\s",""); // removes whitespace
		} else {
			return "ERROR: syntax error in statement "+(i+1)+": "+inputArray[i]; // returns error-message
		}
	}
	
	
	
	
	
	this.tested = true;
	return "consumed";
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String[] getInputArray() {
		return inputArray;
	}

	public static String getGotopattern() {
		return gotoPattern;
	}

	public static String getInteractpattern() {
		return interactPattern;
	}

	public static String getWaitpattern() {
		return waitPattern;
	}
	
	
	
}
