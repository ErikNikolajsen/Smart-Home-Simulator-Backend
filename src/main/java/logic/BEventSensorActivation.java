package logic;

import java.time.LocalDateTime;

import entities.SensorActive;

public class BEventSensorActivation extends BEvent {
	
	private SensorActive sensor
	
	;
	private String command;

	public BEventSensorActivation(LocalDateTime eventDateTime, SensorActive sensor, String command) {
		super(eventDateTime);
		this.sensor = sensor;
		this.command = command;
	}

	public BEventSensorActivation() {
	}

	public SensorActive getSensor() {
		return sensor;
	}

	public void setSensor(SensorActive sensor) {
		this.sensor = sensor;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}
	
}
