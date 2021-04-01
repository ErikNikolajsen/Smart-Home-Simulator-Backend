package main;

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
			if (inputArray[i].matches("(\\s*goto\\(\\d+,\\d+\\)\\s*)|(\\s*interact\\(\\w+\\)\\s*)|(\\s*wait\\(\\d+\\)\\s*)")) { 
				inputArray[i] = inputArray[i].replaceAll("\\s",""); // removes whitespace
			} else {
				return "Error in statement "+(i+1)+": "+inputArray[i]; // returns error-message
			}
		}
		return "consumed";
	}
	

    
}
