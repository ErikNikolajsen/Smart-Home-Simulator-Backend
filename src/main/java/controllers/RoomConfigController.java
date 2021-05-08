package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Agent;
import entities.Floorplan;
import entities.Sensor;
import entities.SensorPassive;
import entities.Entity;
import geo.Grid;
import geo.Position;
import geo.Tile;
import main.Resources;
import pathfinding2.AStarGrid;
import pathfinding2.AStarNode;
import pathfinding2.NodeState;

@RestController
@RequestMapping("/api/roomConfig/")
@CrossOrigin
public class RoomConfigController {

	@PostMapping("/grid")
	public void instantiateGrid(@RequestBody AStarGrid aStarGrid) {
		Resources.setaStarGrid(aStarGrid);
	}
	
	@GetMapping("/grid")
	public @ResponseBody AStarGrid getGrid() {
		return Resources.getaStarGrid();
	}
	
	//get path tester
	@GetMapping("/getPath")
	public @ResponseBody List<AStarNode> getPath(@RequestParam int startx, int starty, int targetx, int targety ) {
		return Resources.getaStarGrid().getPath(startx, starty, targetx, targety, new ArrayList<Position>());
	}
	// http://localhost:8080/api/roomConfig/getPath?startx=0&starty=0&targetx=4&targety=4

	
	
	
	/*
	//Floorplan get test
	@GetMapping("/floorplanTest")
	public @ResponseBody Floorplan getFloorplanTest() {
		Floorplan floorplan = new Floorplan(
				100,
				5, 
				5, 
				new Agent(new Position(0,0),1), 
				new ArrayList<Sensor>(Arrays.asList(
						new SensorPassive("Light_1", new ArrayList<Position>(Arrays.asList(new Position(3,2))), new ArrayList<Position>(Arrays.asList(new Position(2,2))), 300000000),
						new SensorChild("Light_1", new ArrayList<Position>(Arrays.asList(new Position(3,2))), new ArrayList<Position>(Arrays.asList(new Position(2,2))),300000000,1337)
						)), 
				new ArrayList<Position>(Arrays.asList(new Position(1,1), new Position(2,2))));
		return floorplan; 
	}
	*/
	
	@PostMapping("/floorplan")
	public void postFloorplan(@RequestBody Floorplan floorplan) {
		Resources.setFloorplan(floorplan);
		Resources.setaStarGrid(new AStarGrid(Resources.getFloorplan()));
		
		System.out.println(Resources.getFloorplan().getSensors().toString());
	}
	
	@GetMapping("/floorplan")
	public @ResponseBody Floorplan getFloorplan() {
		return Resources.getFloorplan(); 
	}

}












































/*
ArrayList<Tile> testList = new ArrayList<Tile>( Arrays.asList( 	
		new Tile(0,4,0), new Tile(1,4,0), new Tile(2,4,0), new Tile(3,4,0), new Tile(4,4,0), 
		new Tile(0,3,0), new Tile(1,3,1), new Tile(2,3,1), new Tile(3,3,1), new Tile(4,3,0), 
		new Tile(0,2,0), new Tile(1,2,0), new Tile(2,2,0), new Tile(3,2,1), new Tile(4,2,0), 
		new Tile(0,1,0), new Tile(1,1,0), new Tile(2,1,0), new Tile(3,1,1), new Tile(4,1,0), 
		new Tile(0,0,0), new Tile(1,0,0), new Tile(2,0,0), new Tile(3,0,0), new Tile(4,0,0)		));

@GetMapping("/test")
public @ResponseBody Grid test() {
	return new Grid(5,5,testList); 
}

// pathfinding2
@GetMapping("/test2")
public @ResponseBody List<AStarNode> test2() {
	AStarGrid grid1 = new AStarGrid(5,5);
	
	grid1.setNodeState(0, 1, NodeState.NOT_WALKABLE);
	grid1.setNodeState(1, 1, NodeState.NOT_WALKABLE);
	grid1.setNodeState(0, 3, NodeState.NOT_WALKABLE);
	grid1.setNodeState(2, 3, NodeState.NOT_WALKABLE);
	grid1.setNodeState(3, 3, NodeState.NOT_WALKABLE);
	grid1.setNodeState(4, 3, NodeState.NOT_WALKABLE);
	
	return grid1.getPath(0, 0, 4, 4);
}


// get a predefined grid tester
@GetMapping("/getGridTest")
public @ResponseBody AStarGrid getGridTest() {
	AStarGrid testGrid = new AStarGrid(5,5);
	return testGrid; 
}


AStarGrid grid2;

// instantiates a grid of @width and @height with tiles being WALKABLE by default.
@PostMapping("/instantiateGrid")
public void instantiateGrid(@RequestBody int width, int height) {
	this.grid2 = new AStarGrid(width,height);
}

@PostMapping("/insertCollisionObjects")
public void insertCollisionObjects(@RequestBody ArrayList<int[]> positions) {
	for (int i = 0; i < positions.size(); i++) {
		this.grid2.setNodeState(positions.get(i)[0], positions.get(i)[1], NodeState.NOT_WALKABLE);
	}
}
*/
