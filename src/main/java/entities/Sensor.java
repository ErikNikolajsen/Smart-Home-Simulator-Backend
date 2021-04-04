package entities;

import utils.Log;

/* TODO
 * Make interact method
 * 
 */

public abstract class Sensor extends Entity {
	
	public Sensor(String name, int x, int y) {
		super(name, x, y);
	}
	
	public abstract void onInteraction();
}
