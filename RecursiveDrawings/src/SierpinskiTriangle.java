import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class SierpinskiTriangle extends AbstractShape {

	Polygon sierpinskiTriangle;

	private static int maxLevel = 10;

	private static int length = 3;

	private Point p1, p2, p3;

	private int dimension;

	// Creates first triangle
	public SierpinskiTriangle(int dimension) {
		super(maxLevel, length);

		this.dimension = dimension;

		int height = (int) Math.round(dimension / 1.35);

		p1 = new Point(dimension / 2, 0);
		p2 = new Point(dimension, height);
		p3 = new Point(0, dimension);

		sierpinskiTriangle = new Polygon(new int[] { p1.x, p2.x, p3.x }, new int[] { p1.y, p2.y, p3.y }, 3);
	}

	// Creates other triangles
	public SierpinskiTriangle(Point new_p1, Point new_p2, Point new_p3) {
		super(maxLevel, length);

		p1 = new_p1;
		p2 = new_p2;
		p3 = new_p3;

		sierpinskiTriangle = new Polygon(new int[] { p1.x, p2.x, p3.x }, new int[] { p1.y, p2.y, p3.y }, 3);
	}

	/**
	 * Draws this triangle
	 */
	public void draw(Graphics g) {

		if (children[0] == null) {
			g.setColor(Color.green);
			g.drawPolygon(sierpinskiTriangle);
		} else {
			for (int i = 0; i < children.length; i++) {
				children[i].draw(g);
			}
		}
	}

	/**
	 * Creates children for this shape
	 */
	public void createChildren() {

		Point mp1 = midPoint(p1, p2);
		Point mp2 = midPoint(p2, p3);
		Point mp3 = midPoint(p3, p1);

		children[0] = new SierpinskiTriangle(p2, mp1, mp2);
		children[1] = new SierpinskiTriangle(p1, mp1, mp3);
		children[2] = new SierpinskiTriangle(mp3, mp2, p3);
	}

	public static Point midPoint(Point p1, Point p2) {
		return new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
	}

	/**
	 * EXTRA CREDIT Modifies this shape in an interesting way given a value in the
	 * range 0-100. Initially the slider should be set to 50.
	 * 
	 * @param int value - range between 0-100
	 */
	@Override
	public void update(int value) {
		// base case slider = 50
		if (value == 50 || value == 0 || value == 100) {
			return;
		} else if(value > 50){
			//create new rotated points
//			int p1x = (int) (p1.x *Math.cos(3.14) - p1.y*Math.sin(3.14));
//			int p1y = (int) (p1.x *Math.cos(3.14) + p1.y*Math.sin(3.14));
//			
//			int p2x = (int) (p2.x *Math.cos(3.14) - p2.y*Math.sin(3.14));
//			int p2y = (int) (p2.x *Math.cos(3.14) + p2.y*Math.sin(3.14));
//			
//			int p3x = (int) (p3.x *Math.cos(3.14) - p3.y*Math.sin(3.14));
//			int p3y = (int) (p3.x *Math.cos(3.14) + p3.y*Math.sin(3.14));
			
			//rotates 90 clockwise
			Point newp1 = new Point(p1.y, -p1.x);
			Point newp2 = new Point(p2.y, -p2.x);
			Point newp3 = new Point(p3.y, -p3.x);
			
			//as long as there are children, rotate the child triangles
			if(children[0] != null) {
				for (int i = 0; i < children.length; i++) {
					children[0] = new SierpinskiTriangle(p2, newp1, newp2);
					children[1] = new SierpinskiTriangle(p1, newp1, newp3);
					children[2] = new SierpinskiTriangle(newp3, newp2, p3);
				}
			}
			
			value = value + 5;
			//update(value);
		} else if(value < 50){
			
			//rotates 90 degrees counterclockwise
			Point newp1 = new Point(-p1.y, p1.x);
			Point newp2 = new Point(-p2.y, p2.x);
			Point newp3 = new Point(-p3.y, p3.x);

			children[0] = new SierpinskiTriangle(p2, newp1, newp2);
			children[1] = new SierpinskiTriangle(p1, newp1, newp3);
			children[2] = new SierpinskiTriangle(newp3, newp2, p3);
			
			value = value - 5;
			//update(value);
		}

	}

}