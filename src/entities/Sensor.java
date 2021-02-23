package entities;

import utils.Log;

/* TODO
 * Make same name trigger methods for values of type boolean, int, float, string, etc.
 */

public abstract class Sensor extends Entity {
	
	public Sensor(String name, int x, int y) {
		super(name, x, y);
	}
	
	public abstract void onInteraction();
}
