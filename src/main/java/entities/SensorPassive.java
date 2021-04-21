package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

import geo.Position;

public class SensorPassive extends Sensor {
	
	@JsonIgnore private LocalDateTime lastTriggerTime;
	private long triggerFrequency;

	public SensorPassive(String name, ArrayList<Position> positions, ArrayList<Position> triggerArea, LocalDateTime lastTriggerTime, long triggerFrequency) {
		super(name, positions, triggerArea);
		this.lastTriggerTime = lastTriggerTime;
		this.triggerFrequency = triggerFrequency;
	}

	public SensorPassive() {
	}


	
	//Accessors and Mutators
	public LocalDateTime getLastTriggerTime() {
		return lastTriggerTime;
	}

	public void setLastTriggerTime(LocalDateTime lastTriggerTime) {
		this.lastTriggerTime = lastTriggerTime;
	}

	public long getTriggerFrequency() {
		return triggerFrequency;
	}

	public void setTriggerFrequency(long triggerFrequency) {
		this.triggerFrequency = triggerFrequency;
	};
}
