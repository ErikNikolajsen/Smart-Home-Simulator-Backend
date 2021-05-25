package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entities.Entity;
import entities.SensorActive;
import main.Resources;
import pathfinding.NodeState;

public class Input {
	
	private String input;
	private String[] inputArray;
	
	private final Pattern gotoPattern = Pattern.compile("\\s*goto\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)\\s*"); //gotoPattern that accepts coordinates of a tile that is within the boundaries of the specified grid and is walkable
	private final Pattern gotoEntityPattern = Pattern.compile("\\s*goto\\(\\s*(\\w+)\\s*\\)\\s*"); //gotoEntityPattern that accepts name of Entity or SensorActive
	private final Pattern interactPattern = Pattern.compile("\\s*interact\\(\\s*(\\w+),(\\w+)\\s*\\)\\s*"); //interactPattern that accepts sensorName and command
	private final Pattern waitPattern = Pattern.compile("\\s*wait\\(\\s*(\\d+)\\s*\\)\\s*"); //waitPattern that accepts integer
	private final Pattern emptyPattern = Pattern.compile("\\s*"); //empty statement and whitespace at end of input string 
	
	private final Pattern commentLinePattern = Pattern.compile("//.*");
	private final Pattern commentBlockPattern = Pattern.compile("/\\*[\\s\\S]*\\*/");
	
	private final Pattern macroDefinePattern = Pattern.compile("\\s*let\\s+(\\w+)\\b(?<!\\bgoto|wait|interact)\\s*\\{([^}]*)\\}");
	private final Pattern macroCallPattern = Pattern.compile("\\s*(\\w+)\\(\\s*\\)\\s*;");
	
	public Input(String input) {
		this.input = input;
	}
	
	public Input() {	
	}
	
	//Preprocessor
	public String test() {
	
	//Remove comments
	String processedInput = this.input;
	processedInput = commentLinePattern.matcher(processedInput).replaceAll("");
	processedInput = commentBlockPattern.matcher(processedInput).replaceAll("");
	
	
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
	
	
	//Test input
	inputArray = processedInput.split(";");
	for (int i = 0; i < inputArray.length; i++) { 
		if (gotoPattern.matcher(inputArray[i]).matches()) { 
			// parse input
			int x = Integer.parseInt(gotoPattern.matcher(inputArray[i]).replaceAll("$1"));
			int y = Integer.parseInt(gotoPattern.matcher(inputArray[i]).replaceAll("$2"));
			
			// tests if coordinate are within grid boundaries
			if (!Resources.getaStarGrid().isWithin(x, y)) {
				return "ERROR: coordinate is out of bounds in statement "+(i+1)+": "+inputArray[i]; // returns error-message
			}
			// tests if coordinate is walkable
			if (Resources.getaStarGrid().getNodeState(x, y) == NodeState.NOT_WALKABLE) {
				return "ERROR: target coordinate is not walkable in statement "+(i+1)+": "+inputArray[i]; // returns error-message
			}
			continue;
		}
		
		if (interactPattern.matcher(inputArray[i]).matches()) {
			// parse input
			String sensorNameInput = interactPattern.matcher(inputArray[i]).replaceAll("$1");
			String commandInput = interactPattern.matcher(inputArray[i]).replaceAll("$2");
			
			SensorActive sensorInput = null;
			for (SensorActive activeSensor : Resources.getFloorplan().getActiveSensors()) {
				if (activeSensor.getName().equals(sensorNameInput)) {
					sensorInput = activeSensor;
					break;
				}
			}
			
			// test if active sensor name exists in the floorplan
			if (sensorInput == null) {
				return "ERROR: No active sensor exists with the name specified in statement "+(i+1)+": "+inputArray[i]; // returns error-message
			}
			// test if command is applicable for the sensor
			if (!sensorInput.getCommands().contains(commandInput)) {
				return "ERROR: The specified type of sensor does not accept the command in statement "+(i+1)+": "+inputArray[i]; // returns error-message
			}
			continue;
		}
		
		if (waitPattern.matcher(inputArray[i]).matches()) {
			continue;
		}
		
		if (gotoEntityPattern.matcher(inputArray[i]).matches()) {
			// parse input
			String entityNameInput = gotoEntityPattern.matcher(inputArray[i]).replaceAll("$1");
			Entity entityInput = null;
			ArrayList<Entity> gotoableEntities = new ArrayList<Entity>();
			gotoableEntities.addAll(Resources.getFloorplan().getEntities());
			gotoableEntities.addAll(Resources.getFloorplan().getActiveSensors());
			for (Entity entity : gotoableEntities) {
				if (entity.getName().equals(entityNameInput)) {
					entityInput = entity;
					break;
				}
			}
			
			// test if entity exists
			if (entityInput == null) {
				return "ERROR: No entity or active-sensor exists with the name specified in statement "+(i+1)+": "+inputArray[i]; // returns error-message
			}
			
			continue;
		}
		
		if (emptyPattern.matcher(inputArray[i]).matches()) {
			continue;
		}
		
		return "ERROR: syntax error in statement "+(i+1)+": "+inputArray[i]; // returns error-message
		
	}

	return "consumed";
	}
	
	//Accessors and Mutators
	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String[] getInputArray() {
		return inputArray;
	}

	public Pattern getGotopattern() {
		return gotoPattern;
	}

	public Pattern getInteractpattern() {
		return interactPattern;
	}

	public Pattern getWaitpattern() {
		return waitPattern;
	}

	public Pattern getGotoentitypattern() {
		return gotoEntityPattern;
	}
	
	
	
}
