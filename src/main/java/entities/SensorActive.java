package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

import geo.Position;

public class SensorActive extends Sensor {
	
	public SensorActive(String name, ArrayList<Position> positions, ArrayList<Position> triggerArea, LocalDateTime lastTriggerTime, long triggerFrequency) {
		super(name, positions, triggerArea);
	}

	public SensorActive() {
	}

}
