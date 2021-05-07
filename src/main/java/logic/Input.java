package logic;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.Resources;
import pathfinding2.NodeState;

public class Input {
	
	private String input;
	private String[] inputArray;
	
	private final static Pattern gotoPattern = Pattern.compile("\\s*goto\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)\\s*");
	private final static Pattern gotoEntityPattern = Pattern.compile("\\s*goto\\(\\s*(\\w+)\\s*\\)\\s*");
	//private final static Pattern interactPattern = Pattern.compile("\\s*interact\\(\\s*(\\w+)\\s*\\)\\s*"); // interactPattern that accepts sensorName
	private final static Pattern interactPattern = Pattern.compile("\\s*interact\\(\\s*(\\w+),(\\w+)\\s*\\)\\s*"); // interactPattern that accepts sensorName and command
	private final static Pattern waitPattern = Pattern.compile("\\s*wait\\(\\s*(\\d+)\\s*\\)\\s*"); //waitPattern that accepts integer
	//private final static Pattern waitPattern = Pattern.compile("\\s*wait\\(\\s*((\\d+)|(\\d*\\.\\d+)|(\\d+\\.\\d*))\\s*\\)\\s*"); //waitPattern that accepts decimal number
	private final static Pattern emptyPattern = Pattern.compile("\\s*"); //empty statement and whitespace at end of input string 
	
	private final static Pattern commentLinePattern = Pattern.compile("//.*");
	private final static Pattern commentBlockPattern = Pattern.compile("/\\*[\\s\\S]*\\*/"); // instead of [\\s\\S] one might use . with DOTALL flag enabled 
	
	
	private final static Pattern macroDefinePattern = Pattern.compile("\\s*let\\s+(\\w+)\\b(?<!\\bgoto|wait|interact)\\s*\\{([^}]*)\\}");
	private final static Pattern macroCallPattern = Pattern.compile("\\s*(\\w+)\\(\\s*\\)\\s*;");
	
	
	
	
	public Input(String input) {
		this.input = input;
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
	//Preprocessor
	public String test() {
	
	//Remove comments
	String processedInput = this.input;
	processedInput = commentLinePattern.matcher(processedInput).replaceAll("");
	processedInput = commentBlockPattern.matcher(processedInput).replaceAll("");
	/*
	//Represent macros internally
	HashMap<String, String> macros = new HashMap<String, String>();
	Matcher m = macroDefinePattern.matcher(processedInput);
	while (m.find()) {
	   macros.put(m.group(1), m.group(2));	   
	}
	processedInput = macroDefinePattern.matcher(processedInput).replaceAll("");

	//Expand macros
	for (HashMap.Entry<String, String> entry : macros.entrySet()) {
        processedInput = Pattern.compile("\\s*"+entry.getKey()+"\\(\\s*\\)\\s*;").matcher(processedInput).replaceAll(entry.getValue());
    }
	*/
	//Test input
	inputArray = processedInput.split(";");
	for (int i = 0; i < inputArray.length; i++) { 
		if (gotoPattern.matcher(inputArray[i]).matches()) { 
			// tests if coordinate are within grid boundaries
			if (!Resources.getaStarGrid().isWithin(Integer.parseInt(gotoPattern.matcher(inputArray[i]).replaceAll("$1")), Integer.parseInt(gotoPattern.matcher(inputArray[i]).replaceAll("$2")))) {
				return "ERROR: coordinate is out of bounds in statement "+(i+1)+": "+inputArray[i]; // returns error-message
			}
			// tests if coordinate is walkable
			if (Resources.getaStarGrid().getNodeState(Integer.parseInt(gotoPattern.matcher(inputArray[i]).replaceAll("$1")), Integer.parseInt(gotoPattern.matcher(inputArray[i]).replaceAll("$2"))) == NodeState.NOT_WALKABLE) {
				return "ERROR: target coordinate is not walkable in statement "+(i+1)+": "+inputArray[i]; // returns error-message
			}
			continue;
		}
		
		if (interactPattern.matcher(inputArray[i]).matches()) {
			// test if sensor exists in floorplan
			
			// test if command is applicable for the sensor
			continue;
		}
		
		if (waitPattern.matcher(inputArray[i]).matches()) {
			continue;
		}
		
		if (gotoEntityPattern.matcher(inputArray[i]).matches()) {
			// test if entity exists
			continue;
		}
		
		if (emptyPattern.matcher(inputArray[i]).matches()) {
			continue;
		}
		
		return "ERROR: syntax error in statement "+(i+1)+": "+inputArray[i]; // returns error-message
		
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

	public static Pattern getGotopattern() {
		return gotoPattern;
	}

	public static Pattern getInteractpattern() {
		return interactPattern;
	}

	public static Pattern getWaitpattern() {
		return waitPattern;
	}

	public static Pattern getGotoentitypattern() {
		return gotoEntityPattern;
	}
	
	
	
}
