package main;

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
	
	Tile x;
	
	@PostMapping("/floorplan")
	public void floorplan(@RequestBody Tile tile) {
		this.x = tile;
	}
	
	@GetMapping("/floorplanGet")
	public @ResponseBody Tile floorplanGet() {
		return this.x;
	}

	/*
	@PostMapping("/sensorsDefined")
	public String plg2bpmn(@RequestBody Process4Web process) throws IOException {
		return exporter(process.getPlgProcess(), new BPMNExporter());
	}
	*/

}
