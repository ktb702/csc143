import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;


public class MyShape extends AbstractShape {
	
	private static int max = 10;
	
	private static int length = 4;
	
	Polygon kochCurve;
	
	int x1, y1, x2, y2;
	
	int height, width;

	// Creates the first line
	public MyShape(int height, int width) {
		super(max , length);
		
		this.height = height;
		this.width = width;
		
		x1 = 0;
		y1 = this.height / 2;
		x2 = this.width;
		y2 = this.height / 2;
		
		kochCurve = new Polygon(new int[] {x1, x2}, new int[] {y1, y2}, 2);
	}
	
	
	// Creates other lines
	public MyShape(int new_x1, int new_y1, int new_x2, int new_y2) {
		super(max, length);
		
		x1 = new_x1;
		y1 = new_y1;
		x2 = new_x2;
		y2 = new_y2;
		
		kochCurve = new Polygon(new int[] {x1, x2}, new int[] {y1, y2}, 2);
	}
	
	public void draw(Graphics g) {
		
		if (children[0] == null) {
			g.setColor(Color.PINK);
			g.drawPolygon(kochCurve);
		} else {
			for (int i = 0; i < children.length; i++) {
        			children[i].draw(g);
        		}
        	}
	}

	
	public void createChildren() {

		int px = (x1 + x2) / 2;
		int py = (y1 + y2) / 2;

		double x3 = (2 * x1 + x2) / 3;
		double y3 = (2 * y1 + y2) / 3;

		double x4 = (x1 + x2 * 2) / 3;
		double y4 = (y1 + y2 * 2) / 3;
		
		double x5 = px + Math.sqrt(3) * (y4 - py);
		double y5 = py - Math.sqrt(3) * (x4 - px);
				
		children[0] = new MyShape(x1, y1, (int) x3, (int) y3);
		children[1] = new MyShape((int) x3, (int) y3, (int) x5, (int) y5);
		children[2] = new MyShape((int) x5, (int) y5, (int) x4, (int) y4);
		children[3] = new MyShape((int) x4, (int) y4, x2, y2);	
	}
	
	
	@Override
	public int countShapes() {
		int count = 0;
		if (children[0] == null) {
			return 1;
		}
		else {
			for (int i = 0; i < children.length; i++) {
				count = children.length * children[i].countShapes();			
			}
		} return count;
	}
	
	public void update(int value) {
				// TODO Auto-generated method stub
	}	
}
