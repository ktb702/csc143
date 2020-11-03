import java.awt.Graphics;

public class HShape extends AbstractShape {

	private static int maxLevel = 5;
	
	private static int length = 7;
	
	private int h, w;
	
	private Point r1_start, r2_start, r3_start, r4_start, r5_start, r6_start, r7_start, p_hw;
	
	private Point r1_end, r2_end, r3_end, r4_end, r5_end, r6_end, r7_end;

	public HShape(int height, int width) {
		super(maxLevel, length);
		
		h = height - 200;
		w = width;
		
		p_hw = new Point(w / 3, h / 3);
		
		// The start points of the seven rectangles
		r1_start = new Point(w - w, h - h);
		r2_start = new Point(w - w, h / 3);
		r3_start = new Point(w - w, (h / 3) * 2);
		r4_start = new Point(w / 3, h / 3);
		r5_start = new Point((w / 3) * 2, h - h);
		r6_start = new Point((w / 3) * 2, h / 3);
		r7_start = new Point((w / 3) * 2, (h / 3) * 2);
		
		// The end points of the seven rectangles
		r1_end = new Point(w / 3, h / 3);
		r2_end = new Point(w / 3, (h / 3) * 2);
		r3_end = new Point(w / 3, h);
		r4_end = new Point((w / 3) * 2, (h / 3) * 2);
		r5_end = new Point(w, h / 3);
		r6_end = new Point(w, (h / 3) * 2);
		r7_end = new Point(w, h);
		
	}
	
	public HShape(Point p1, Point p2) {
		super(maxLevel, length);
		
		w = p2.x - p1.x;
		h = p2.y - p1.y;
		
		p_hw = new Point(w / 3, h / 3);
		
		// The start points of the seven rectangles (based on the two given points)
		r1_start = new Point(p1.x, p1.y);
		r2_start = new Point(p1.x, p1.y + ((p2.y - p1.y) / 3));
		r3_start = new Point(p1.x, p1.y + (((p2.y - p1.y) / 3)) * 2);
		r4_start = new Point(p1.x + (p2.x - p1.x) / 3, p1.y + ((p2.y - p1.y) / 3));
		r5_start = new Point(p1.x + ((p2.x - p1.x) / 3) * 2, p1.y);
		r6_start = new Point(p1.x + ((p2.x - p1.x) / 3) * 2, p1.y + ((p2.y - p1.y) / 3));
		r7_start = new Point(p1.x + ((p2.x - p1.x) / 3) * 2, p1.y + (((p2.y - p1.y) / 3)) * 2);
		
		// The end points of the seven rectangles (based on the two given points)
		r1_end = new Point(p1.x + (p2.x - p1.x) / 3, p1.y + ((p2.y - p1.y) / 3));
		r2_end = new Point(p1.x + (p2.x - p1.x) / 3, p1.y + (((p2.y - p1.y) / 3)) * 2);
		r3_end = new Point(p1.x + (p2.x - p1.x) / 3, p2.y);
		r4_end = new Point(p1.x + ((p2.x - p1.x) / 3) * 2, p1.y + (((p2.y - p1.y) / 3)) * 2);
		r5_end = new Point(p2.x, p1.y + (p2.y - p1.y) / 3);
		r6_end = new Point(p2.x, p1.y + ((p2.y - p1.y) / 3) * 2);
		r7_end = new Point(p2.x, p2.y);	
	}
	
	public void createChildren() {
    	
		children[0] = new HShape(r1_start, r1_end);
		children[1] = new HShape(r2_start, r2_end);
		children[2] = new HShape(r3_start, r3_end);
		children[3] = new HShape(r4_start, r4_end);
		children[4] = new HShape(r5_start, r5_end);
		children[5] = new HShape(r6_start, r6_end);
		children[6] = new HShape(r7_start, r7_end);	
    }
    
	
    public void draw(Graphics g) {
    	if (children[0] == null) {
    		g.setColor(Color.ORANGE);
    		g.fillRect(r1.x, r1.y, p_hw.x, p_hw.y);
    		g.fillRect(r2.x, r2.y, p_hw.x, p_hw.y);
        	g.fillRect(r3.x, r3.y, p_hw.x, p_hw.y);
        	g.fillRect(r4.x, r4.y, p_hw.x, p_hw.y);
        	g.fillRect(r5.x, r5.y, p_hw.x, p_hw.y);
        	g.fillRect(r6.x, r6.y, p_hw.x, p_hw.y);
        	g.fillRect(r7.x, r7.y, p_hw.x, p_hw.y);
		} else {		
        	for (int i = 0; i < children.length; i++) {
        		children[i].draw(g);
        	}
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
