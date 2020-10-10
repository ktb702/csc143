import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import org.junit.jupiter.api.Test;

public class TetrisTest {

	//tests for scenarios not possible when you play the game 
	//one test to check the removal of a row
	//another test to check the movement of the piece (does canMove work correctly, does the piece move to the correct place)?
	
	@Test
	void testCheckRows() {
		Grid g = new Grid();
		//nested for loops will completely fill up the grid 
		for (int row=0; row < Grid.HEIGHT; row++) {
			for(int col=0; col < Grid.WIDTH; col++) {
				if(row != 10 || col == 5 || col == 6) { //sets everything except row 10, cols 5 and 6 (2 squares)
					g.set(row,  col,  Color.MAGENTA);
				}
			}
		}
		g.checkRows();
		//after calling that function the grid should be empty
		//check that the row is empty
		for (int row=0; row < Grid.HEIGHT; row++) {
			for(int col=0; col < Grid.WIDTH; col++) {
				if (row < Grid.HEIGHT -1 || col != 5 || col != 6) {
					assertFalse(g.isSet(row, col)); //isSet returns true if there is a square set on the grid at that location
				}
			}
		}
	}
	
//	@Test
//	void testCheckMoveLeft{
//		//
//	}
	
	public static void main(String[] args) {
		

	}

}
