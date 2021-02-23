package entities.library;

import entities.*;
import utils.*;

public class Lamp extends Sensor {
	
	Boolean value; // true == lamp on, false == lamp off
	
	public Lamp(String name, Boolean initialValue, int x, int y) {
		super(name, x, y);
		value = initialValue;
	}
	
	public void onInteraction() {
		if (value == false) {
			value = true;
			Log.writeToFile("??-??-?? ??.??.???", getName(), "true");
		} else if (value == true) {
			value = false;
			Log.writeToFile("??-??-?? ??.??.???", getName(), "false");
		}
		
	}
}