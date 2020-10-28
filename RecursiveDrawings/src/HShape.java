import java.awt.Graphics;

public class HShape extends AbstractShape {

	private static int maxLevel = 5;
	private static int level = 7;

	public HShape() {
		super(maxLevel, level);
		// TODO Auto-generated constructor stub
		// H's at level n are children of H's at level n-1
		// each level adds 7 H's.
	}

	public void draw(Graphics g) {

	}

	public void createChildren() {
		//fill the array of children 
		for(int i = 0; i<children.length; i++) {
			children[i] = new HShape();
		}
	}
	
	/**
	 * EXTRA CREDIT
	 * Modifies this shape in an interesting way given a value in the range 0-100.
	 * Initially the slider should be set to 50.
	 * 
	 * @param int value - range between 0-100
	 */
	public void update(int value) {

	}
}
