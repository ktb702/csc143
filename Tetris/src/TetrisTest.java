import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import org.junit.jupiter.api.Test;

public class TetrisTest {

	// tests for scenarios not possible when you play the game
	// one test to check the removal of a row
	// another test to check the movement of the piece (does canMove work correctly,
	// does the piece move to the correct place)?

	@Test
	void testCheckMoveLeft() {
		Grid g = new Grid();
		// set a L-shaped piece in the bottom left hand corner
		LShape piece = new LShape(17, 0, g);

		// test if the piece canMove left
		boolean result = piece.canMove(Direction.LEFT);
		assertFalse(result);
	}

	@Test
	void testCheckMoveRight() {
		Grid g = new Grid();
		// set a L-shaped piece in the bottom left hand corner
		LShape piece = new LShape(15, 5, g);

		// test if the piece canMove left
		boolean result = piece.canMove(Direction.RIGHT);
		assertTrue(result);
	}

	@Test
	void testCheckRows() {
		Grid g = new Grid();
		// nested for loops will completely fill up the grid
		for (int row = 0; row < Grid.HEIGHT; row++) {
			for (int col = 0; col < Grid.WIDTH; col++) {
				g.set(row, col, Color.MAGENTA);

			}
		}
		g.checkRows();
//		assertFalse(g.isSet(1, 0));
//		assertFalse(g.isSet(18, 0));
		// after calling that function the grid should be empty
		// check that the row is empty
		for (int row = 0; row < Grid.HEIGHT; row++) {
			for (int col = 0; col < Grid.WIDTH; col++) {
				
					assertFalse(g.isSet(row, col)); // isSet returns true if there is a square set on the grid at that
													// location
					System.out.println(g.get(row, col));
	
			}
		}
	}

}
