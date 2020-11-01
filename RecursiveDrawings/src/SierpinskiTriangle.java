import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Point;

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
       
        	sierpinskiTriangle = new Polygon(new int[] {p1.x, p2.x, p3.x}, new int[] {p1.y, p2.y, p3.y}, 3);
    }
	

	// Creates other triangles
	public SierpinskiTriangle(Point new_p1, Point new_p2, Point new_p3) {
		super(maxLevel, length);
		
		p1 = new_p1;
		p2 = new_p2;
		p3 = new_p3;
		
        	sierpinskiTriangle = new Polygon(new int[] {p1.x, p2.x, p3.x}, new int[] {p1.y, p2.y, p3.y}, 3);
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
		
		Point mp1 = midPoint(p1,p2);
		Point mp2 = midPoint(p2, p3);
		Point mp3 = midPoint(p3, p1);
		
		children[0] = new SierpinskiTriangle(p2, mp1, mp2);
		children[1] = new SierpinskiTriangle(p1, mp1, mp3);
		children[2] = new SierpinskiTriangle(mp3, mp2, p3);
	}
	
	public  static Point midPoint(Point p1, Point p2) {
		return new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
	}

	/**
	 * EXTRA CREDIT
	 * Modifies this shape in an interesting way given a value in the range 0-100.
	 * Initially the slider should be set to 50.
	 * 
	 * @param int value - range between 0-100
	 */
	@Override
	public void update(int value) {
		// TODO Auto-generated method stub
		
	}

}
