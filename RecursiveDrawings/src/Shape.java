import java.awt.Graphics;

public interface Shape {
	void draw(Graphics g); // Draws this shape
	
	public boolean addLevel(); // Adds a level to this shape. Returns true if the operation was
						       // successful or false if the maximum level has been reached.
	public boolean removeLevel(); // Removes a level from this shape. Returns true if the 
								  // operation was successful or false if the shape was at level 1.
	public int countShapes(); //Returns the total number of shapes of this shape 
							  // (e.g. 57 for the H shape at level 2).
	public void update(int value); // Modifies this shape in an interesting way given a value in 
								   // the range 0-100. 
}
