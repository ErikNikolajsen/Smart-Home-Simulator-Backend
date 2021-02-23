package entities.library;

import entities.*;

public class Lamp extends Sensor {
	
	Boolean value; // lamp on -> true, lamp off -> false
	
	public Lamp(String name, int x, int y) {
		super(name, x, y);
	}
	
	public void onInteraction() {
		if (value == false) {
			
		} else if (value == true) {
			
		}
		//Log.writeToFile("test", "??-??-?? ??.??.???", name, getName());
	}
}