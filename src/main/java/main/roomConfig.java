package main;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import geo.Tile;

@RestController
@RequestMapping("/api/v2/roomConfig/")
@CrossOrigin
public class roomConfig {
	
	
	Tile array[] = { 	new Tile(0,4,0), new Tile(1,4,0), new Tile(2,4,0), new Tile(3,4,0), new Tile(4,4,0), 
						new Tile(0,3,0), new Tile(1,3,0), new Tile(2,3,0), new Tile(3,3,0), new Tile(4,3,0), 
						new Tile(0,2,0), new Tile(1,2,0), new Tile(2,2,0), new Tile(3,2,0), new Tile(4,2,0), 
						new Tile(0,1,0), new Tile(1,1,0), new Tile(2,1,0), new Tile(3,1,0), new Tile(4,1,0), 
						new Tile(0,0,0), new Tile(1,0,0), new Tile(2,0,0), new Tile(3,0,0), new Tile(4,0,0)		};
	
	ArrayList<Tile> testList = new ArrayList<Tile>( Arrays.asList( 	
			new Tile(0,4,0), new Tile(1,4,0), new Tile(2,4,0), new Tile(3,4,0), new Tile(4,4,0), 
			new Tile(0,3,0), new Tile(1,3,1), new Tile(2,3,1), new Tile(3,3,1), new Tile(4,3,0), 
			new Tile(0,2,0), new Tile(1,2,0), new Tile(2,2,0), new Tile(3,2,1), new Tile(4,2,0), 
			new Tile(0,1,0), new Tile(1,1,0), new Tile(2,1,0), new Tile(3,1,1), new Tile(4,1,0), 
			new Tile(0,0,0), new Tile(1,0,0), new Tile(2,0,0), new Tile(3,0,0), new Tile(4,0,0)		));
	
	@GetMapping("/test")
	public @ResponseBody ArrayList<Tile> test() {
		return this.testList;
	}
	
	
	ArrayList<Tile> floorplan;
	
	@PostMapping("/floorplan")
	public void floorplan(@RequestBody ArrayList<Tile> tiles) {
		this.floorplan = tiles;
	}
	
	@GetMapping("/floorplanGet")
	public @ResponseBody ArrayList<Tile> floorplanGet() {
		return this.floorplan;
	}

}
