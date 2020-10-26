import java.awt.Graphics;

public interface Shape {
	public void draw(Graphics g);
	public boolean addLevel();
	public boolean removeLevel();
	public int countShapes();
	public void update(int value);
}
