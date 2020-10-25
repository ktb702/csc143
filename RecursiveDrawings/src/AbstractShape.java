import java.awt.Color;
import java.awt.Graphics;

public class AbstractShape implements Shape {
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
	
	/** 
	 * 
	 */
	public AbstractShape() {
		
	}
	
	/**
	 * 
	 */
	void draw(Graphics g) {
		
	}
	
	/**
	 * 
	 */
	boolean addLevel() {
		
	}
	
	/**
	 * 
	 */
	boolean removeLevel() {
		
	}
	
	/**
	 * 
	 */
	int countShapes() {
		
	}
	
	/**
	 * 
	 */
	void update(int value) {
		
	}
}
