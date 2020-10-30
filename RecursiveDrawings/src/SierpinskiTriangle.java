import java.awt.Graphics;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Point;

public class SierpinskiTriangle extends AbstractShape {
	
	Polygon sierpinskiTriangle;
	private static int maxLevel = 10;
	private static int length = 3;
	
	 private int height = (int) Math.round(800 / 1.35);        
     
     Point peak = new Point(800 / 2, 0);
     Point leg_1 = new Point(800, height);
     Point leg_2 = new Point(0, height);
	


	public SierpinskiTriangle(Point p1, Point p2, Point p3) {
		super(maxLevel, length);
		// TODO Auto-generated constructor stub
    
        // Triangle's peak
//        sierpinskiTriangle.addPoint(peak.x, peak.y);
//        // Legs
//        sierpinskiTriangle.addPoint(leg_1.x, leg_1.y);
//        sierpinskiTriangle.addPoint(leg_2.x, leg_2.y);
//        
        sierpinskiTriangle = new Polygon(new int[] {p1.x, p2.x, p3.x}, new int[] {p1.y, p2.y, p3.y}, 3);
        

    }

	/**
	 * Draws this triangle
	 */
	public void draw(Graphics g) {
		
    	g.setColor(Color.green);
        g.drawPolygon(sierpinskiTriangle);
        
        if (children[0] != null) {
        	for (int i = 0; i < children.length; i++) {
        		children[i].draw(g);
        		//g.drawPolygon(new int[] {mp1.x, mp2.x, mp3.x}, new int[] {mp1.y, mp2.y, mp3.y}, 3);
        	}
        }
    }
    
    /**
     * Creates children for this shape
     */
	@Override
	public void createChildren() {
		
		Point mp1 = midPoint(peak,leg_1);
		Point mp2 = midPoint(peak, leg_2);
		Point mp3 = midPoint(leg_1, leg_2);
		
		children[0] = new SierpinskiTriangle(mp1, mp3, leg_1);
		children[1] = new SierpinskiTriangle(mp2, mp3, leg_2);
		children[2] = new SierpinskiTriangle(mp1, mp2, peak);
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
