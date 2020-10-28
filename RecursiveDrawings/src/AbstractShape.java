import java.awt.Color;
import java.awt.Graphics;

public abstract class AbstractShape implements Shape {
	protected int level;
	protected int maxLevel;
	protected AbstractShape[] children;
	protected Color color;

	public AbstractShape(int max, int length) {
		maxLevel = max;
		children = new AbstractShape[length];
		level = 1; //initialize to the first level
	}

	/**
	 * Adds a level to this shape. 
	 * 
	 * returns true if the operation was successful or false if the maximum level has been reached. 
	 */
	public boolean addLevel() {
		//base case
		if (maxLevel == level) {
			//we can only add levels as long as the max level has not been reached
			return false;
		} else if(children[0] == null) {
				createChildren();
				return true;
		} else { //recursion
			// loop over the children and ask each one to add children to their shapes
			for(int i = 0; i < children.length; i++) {
				children[i].addLevel();
			}
			return true;
		}
	}

	/**
	 * Removes a level from this shape.
	 * 
	 * Returns true if the operation was successful or false if the shape was at level 1.
	 */
	public boolean removeLevel() {
		//base case: no grandchildren
		if (children[0] != null && children[0].children[0] == null) {
			return false;
		} else { //recursion
			// loop over the children and ask each one to remove children from their shapes
			for(int i = 0; i < children.length; i++) {
				children[i].removeLevel();
			}
			return true;
		}
	}

	/**
	 * This method is called from FractalDisplay every time the mouse is right clicked on a shape
	 * 
	 * returns the total number of shapes of this shape at the current level.
	 */
	public int countShapes() {
		if (level == 1) {
			return 1;
		}
		// only works for sierpinski triangle now
		else {
			level--;
			return 1 + children.length * countShapes();
		}
	}

	/**
	 * This method will be implemented in the concrete shape classes and will fill the array of children. 
	 */
	public abstract void createChildren();

}
