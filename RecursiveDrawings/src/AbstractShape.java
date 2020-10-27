import java.awt.Color;
import java.awt.Graphics;

public abstract class AbstractShape implements Shape {
	protected int level;
	protected int maxLevel;
	protected AbstractShape[] children;
	protected Color color;
	
	// The fields may be initialized by the AbstractShape constructor with the values
	// received from the super() calls in the constructors of the concrete shape classes.
	// For instance, the SierpinskiTriangle constructor may call the AbstractShape constructor with
	// the a max level value of 10 and a children array length of 3
	// Alternatively the fields may be initialized in the concrete class constructors (they are visible by
	// the concrete classes since they are declared protected) 
	
	public AbstractShape() {
		
		
	}
    	public void draw(Graphics g) {
        // TODO Auto-generated method stub

    	}
	
	public boolean addLevel() {
		if (children == null) {
			return true;
		}
		else {
			// need code to add a level
			return addLevel();
		}
	}
	
	public boolean removeLevel() {
		if (children == null) {
			return false;
		} else {
			// need code to remove a level
			return true;
		}
	}
	
	public int countShapes() {
		if (level == 1) {
			return 1;
		}
		// only works for sierpinski triangle now
		else {
			level --;
			return 1 + 3 * countShapes();
		}
	}
	
	public void update(int value) {
		
	}
	
	public abstract void createChildren();
	
	
	
	
}
