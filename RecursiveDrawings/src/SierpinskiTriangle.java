
public class SierpinskiTriangle extends AbstractShape {
 
	Polygon sierpinskiTriangle;
	private int dimension = 800;
	private int height;
   
    public SierpinskiTriangle() {

    	sierpinskiTriangle = new Polygon();

        height = (int) Math.round(dimension / 1.4);
        
        sierpinskiTriangle.addPoint(20, height);
        sierpinskiTriangle.addPoint(dimension / 2, 0);
        sierpinskiTriangle.addPoint(dimension - 20, height);
        

    }
		
		// TODO Auto-generated constructor stub
	}
	
	public void draw(Graphics g) {
		
		g.setColor(Color.green);
		g.drawPolygon(sierpinskiTriangle);
	}


}
