import java.awt.Graphics;

public class HShape extends AbstractShape {
	
	Polygon HShape;
	private Point r1, r2, r3, r4, r5, r6, r7, p_hw;
	private static int maxLevel = 5;
	private static int level = 7;

	public HShape(int height, int width) {
		super(maxLevel, length);
		
		h = height - 200;
		w = width;
		
		p_hw = new Point(w / 3, h / 3);
		
		// first rectangle
		r1 = new Point(w - w, h - h);
		// second rectangle
		r2 = new Point(w - w, h / 3);
		// third rectangle
		r3 = new Point(w - w, (h / 3) * 2);
		// fourth rectangle
		r4 = new Point(w / 3, h / 3);
		// fifth rectangle
		r5 = new Point((w / 3) * 2, h - h);
		// sixth rectangle
		r6 = new Point((w / 3) * 2, h / 3);
		// seventh rectangle
		r7 = new Point((w / 3) * 2, (h / 3) * 2);
		
	}
	
	public HShape(Point p_1, Point p_2) {
		super(maxLevel, length);
		
		w = p_2.x - p_1.x;
		h = p_2.y - p_1.y;
		
		r1 = new Point(p_1.x, p_1.y);
		
	}
	
	public void createChildren() {
    	
		children[0] = new HShape(r1, r4);
		children[1] = new HShape(r2, new Point(w / 3, h));
		children[2] = new HShape(r1, r4);
		children[3] = new HShape(r1, r4);
		children[4] = new HShape(r1, r4);
		children[5] = new HShape(r1, r4);
		children[6] = new HShape(r1, r4);
//		children[1] = new HShape(r2, new Point(w / 3, (h / 3) * 2));
//		children[2] = new HShape(r3, new Point(w / 3, h));
//		children[3] = new HShape(r4, r7);
//		children[4] = new HShape(r5, new Point(w, h / 3));
//		children[5] = new HShape(r6, new Point(w, (h / 3) * 2));
//		children[6] = new HShape(r7, new Point(w, h));
    	
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
