package main;

import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/roomConfig/")
@CrossOrigin
public class roomConfig {
	/*
	@PostMapping("/floorplan")
	public String plg2bpmn(@RequestBody Process4Web process) throws IOException {
		return exporter(process.getPlgProcess(), new BPMNExporter());
	}

	
	@PostMapping("/sensorsDefined")
	public String plg2bpmn(@RequestBody Process4Web process) throws IOException {
		return exporter(process.getPlgProcess(), new BPMNExporter());
	}
	*/

}
