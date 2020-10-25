import java.awt.Graphics;

public interface Shape {
	void draw(Graphics g);
	boolean addLevel();
	boolean removeLevel();
	int countShapes();
	void update(int value);
}
