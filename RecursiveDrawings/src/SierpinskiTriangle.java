import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class SierpinskiTriangle extends AbstractShape {

	Polygon sierpinskiTriangle;
	private int dimension = 800;
	private int height;
	private static int maxLevel = 10;
	private static int level = 3;

	public SierpinskiTriangle() {
		super(maxLevel, level);
		sierpinskiTriangle = new Polygon();

		height = (int) Math.round(dimension / 1.4);

		sierpinskiTriangle.addPoint(20, height);
		sierpinskiTriangle.addPoint(dimension / 2, 0);
		sierpinskiTriangle.addPoint(dimension - 20, height);
	}

	// triangles at level n are children of triangles at level n-1
	// each level adds 3 triangles.


	public void draw(Graphics g) {

		g.setColor(Color.green);
		g.drawPolygon(sierpinskiTriangle);
	}
	
	public void createChildren() {
		//fill the array of children 
		for(int i = 0; i<children.length; i++) {
			children[i] = new SierpinskiTriangle();
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