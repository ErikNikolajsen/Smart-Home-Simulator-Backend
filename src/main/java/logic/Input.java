package logic;



import main.Resources;
import pathfinding2.NodeState;

public class Input {
	
	private String input;
	private String[] inputArray;
	
	private final static String gotoPattern = "\\s*goto\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)\\s*";
	private final static String interactPattern = "\\s*interact\\(\\s*(\\w+)\\s*\\)\\s*"; // interactPattern that accepts sensorName
	//private final static String interactPattern = "\\s*interact\\(\\s*(\\w+),(\\w+)\\s*\\)\\s*"; // interactPattern that accepts sensorName and command
	private final static String waitPattern = "\\s*wait\\(\\s*(\\d+)\\s*\\)\\s*"; //waitPattern that accepts integer
	//private final static String waitPattern = "\\s*wait\\(\\s*((\\d+)|(\\d*\\.\\d+)|(\\d+\\.\\d*))\\s*\\)\\s*"; //waitPattern that accepts decimal number
	//private final static String emptyPattern = "\\s*"; //empty statement and whitespace at end of input string 
	
	private final static String macroPattern = "\\s*let\\s+(\\w+)\\{(.*)\\}\\s*";
	
	private final static String commentLinePattern = "//.*";
	private final static String commentBlockPattern = "/\\*[\\s\\S]*\\*/"; // instead of [\\s\\S] one might use . with DOTALL flag enabled 
	
	
	public Input(String input) {
		this.input = input;
		//this.inputArray = input.split(";"); // splits statements into array elements;
	}
	
	
	public Input() {	
	}
	
	/* Tests:
	 * Syntax
	 * coordinates are within grid boundaries
	 * coordinates are WALKABLE
	 * interact IDs exist and is interactable
	 * 
	 */
	public String test() {
	
		
	String inputSansComments = this.input.replaceAll(commentLinePattern, "").replaceAll(commentBlockPattern, "");
	inputArray = inputSansComments.split(";");
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
