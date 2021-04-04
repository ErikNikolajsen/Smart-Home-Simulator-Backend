package controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import geo.Tile;

@RestController
@RequestMapping("/api/system/")
@CrossOrigin
public class SystemController {
	
	@GetMapping("/ping")
	public @ResponseBody String ping() {
		return "pong";
	}
	// http://localhost:8080/api/v2/system/ping
	
	
	/*
	int i = 0;
	
	@GetMapping("/amount")
	public @ResponseBody int amount() {
		return i;
	}
	// http://localhost:8080/api/v2/system/amount
	
	@GetMapping("/increase2")
	public @ResponseBody int increase2() {
		i=i+2;
		return i;
	}
	// http://localhost:8080/api/v2/system/increase2
	
	@GetMapping("/returnIntParameter")
	public @ResponseBody int returnIntParameter(@RequestParam int n) {
		return n;
	}
	// http://localhost:8080/api/v2/system/returnIntParameter?n=100
	
	@GetMapping("/returnOptionalStringParameter")
	@ResponseBody
	public String returnOptionalStringParameter(@RequestParam(required = false) String id) { 
	    return "ID: " + id;
	}
	// http://localhost:8080/api/v2/system/returnOptionalStringParameter?id=abc
	// or
	// http://localhost:8080/api/v2/system/returnOptionalStringParameter
	
	@GetMapping("/returnOptionalStringParameterOrDefaultValue")
	@ResponseBody
	public String returnOptionalStringParameterOrDefaultValue(@RequestParam(defaultValue = "test") String id) {
	    return "ID: " + id;
	}
	// http://localhost:8080/api/v2/system/returnOptionalStringParameterOrDefaultValue?id=abc
	// or
	// http://localhost:8080/api/v2/system/returnOptionalStringParameterOrDefaultValue
	
	@GetMapping("/tile")
	public @ResponseBody Tile tile() {
		Tile x = new Tile(1,1,0);
		return x;
	}
	// http://localhost:8080/api/v2/system/tile
	
	
	@PostMapping("/increase")
	public int increase(@RequestBody int n) {
		i=i+n;
		return i;
	}
	*/
}

