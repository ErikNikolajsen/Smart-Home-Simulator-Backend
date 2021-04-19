package entities;

import java.util.ArrayList;
import java.util.UUID;

import geo.Position;

public class SensorChild extends Sensor {
	long sensorChildVariable;

	/**
	 * @param triggerFrequency
	 */
	public SensorChild(String name, ArrayList<Position> positions, ArrayList<Position> triggerArea, long triggerFrequency, long sensorChildVariable) {
		super(name, positions, triggerArea, triggerFrequency);
		this.sensorChildVariable = sensorChildVariable;
	}
	
	public SensorChild() {;
	}

	public long getSensorChildVariable() {
		return sensorChildVariable;
	}

	public void setSensorChildVariable(long sensorChildVariable) {
		this.sensorChildVariable = sensorChildVariable;
	}
	
	
}
