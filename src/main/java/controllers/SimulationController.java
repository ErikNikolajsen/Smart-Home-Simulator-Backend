package controllers;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/simulation/")
@CrossOrigin
public class SimulationController {
	
	
	/* TODO
	 * Add possible whitespaces before and after parameters.
	 * Check if coordinates are possible
	 * Check if sensors exist
	 */
	@PostMapping("/input")
	public String input(@RequestBody String inputString) {
		/*
		 * Test whole input at once
		 * 
		if (inputString.matches("((\\s*goto\\(\\d+,\\d+\\)\\s*;\\s*)|(\\s*interact\\(\\w+\\)\\s*;\\s*)|(\\s*wait\\(\\d+\\)\\s*;\\s*))+")) { 
			return "consumed"; //Input is correct
		} else {
			return "error"; //Input contains error
		}
		*/
		
		
		String[] inputArray = inputString.split(";"); // inserts statements into an array
		for (int i = 0; i < inputArray.length; i++) { 
			if (inputArray[i].matches("(\\s*goto\\(\\s*\\d+\\s*,\\s*\\d+\\s*\\)\\s*)|(\\s*interact\\(\\s*\\w+\\s*\\)\\s*)|(\\s*wait\\(\\s*\\d+\\s*\\)\\s*)")) { 
				inputArray[i] = inputArray[i].replaceAll("\\s",""); // removes whitespace and adds statement to inputArray
			} else {
				return "ERROR: syntax error in statement "+(i+1)+": "+inputArray[i]; // returns error-message
			}
		}
		return "consumed";
		
		
		/*
		String gotoPattern = "\\s*goto\\(\\s*\\(d+)\\s*,\\s*\\(d+)\\s*\\)\\s*";
		String interactPattern = "\\s*interact\\(\\s*\\(w+)\\s*\\)\\s*";
		String waitPattern = "\\s*wait\\(\\s*\\d+\\s*\\)\\s*";
		
		String[] inputArray = inputString.split(";"); // inserts statements into an array
		for (int i = 0; i < inputArray.length; i++) { 
			if (inputArray[i].matches(gotoPattern)) { 
				if (.isWithin(inputArray[i].replaceAll(gotoPattern, "$1"), inputArray[i].replaceAll(gotoPattern, "$2"))) {
					inputArray[i] = inputArray[i].replaceAll("\\s",""); // removes whitespace
				} else {
					return "ERROR: coordinates are out of bounds in statement "+(i+1)+": "+inputArray[i]; // returns error-message
				}
			} else if (inputArray[i].matches(interactPattern)) {
				
			} else if (inputArray[i].matches(waitPattern)) {
				
			} else {
				return "ERROR: syntax error in statement "+(i+1)+": "+inputArray[i]; // returns error-message
			}
		}
		return "consumed";
		*/
	}
	

    
}
