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
		if (children[0] == null) {
			createChildren();
			level++;
			return true;
		} else if (level == maxLevel) {
			System.out.println("return false from addLevel");
			return false;
		}else {
			level++;
			for (int i = 0; i < children.length; i++) {
				children[i].addLevel(); 	
			}
			System.out.println("level after add" + level);
			return true;
		}
	}

	
	/**
	 * Removes a level from this shape.
	 * 
	 * Returns true if the operation was successful or false if the 
	 * shape was at level 1.
	 */
	public boolean removeLevel() {
		if (children[0] != null) {
			if (children[0].children[0] == null) {
				for (int i = 0; i < children.length; i++) {
					children[i] = null;
				}
				level--;
				System.out.println("level after remove" + level);
				return true;
			} 
			for (int i = 0; i < children.length; i++) {
				children[i].removeLevel();
			}
			level--;
			System.out.println("level after remove" + level);
			return true;
		} else {
			System.out.println("remove level = false");
			return false;
		}
	}
	
	/**
	 * This method is called from FractalDisplay every time the mouse is right clicked on a shape
	 * 
	 * returns the total number of shapes of this shape at the current level.
	 */
	public int countShapes() {
		int count = 0;
		if (children[0] == null) {
			return 1;
		}
		else {
			for (int i = 0; i < children.length; i++) {
				count = 1 + children.length * children[i].countShapes();			
			}
		} return count;
	}

	/**
	 * This method will be implemented in the concrete shape classes and will fill the array of children. 
	 */
	public abstract void createChildren();

}
