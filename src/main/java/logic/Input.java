package logic;

public class Input {
	
	String input;
	String[] inputArray;
	
	final String gotoPattern = "\\s*goto\\(\\s*\\(d+)\\s*,\\s*\\(d+)\\s*\\)\\s*";
	final String interactPattern = "\\s*interact\\(\\s*\\(w+)\\s*\\)\\s*";
	final String waitPattern = "\\s*wait\\(\\s*\\d+\\s*\\)\\s*";

	public Input(String[] inputArray) {
		super();
		this.inputArray = inputArray;
	}
	
	
	
}
