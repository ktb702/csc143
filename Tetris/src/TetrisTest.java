import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import org.junit.jupiter.api.Test;

public class TetrisTest {

	@Test
	void testCheckRows() {
		Grid g = new Grid();
		for(int row; row < Grid.HEIGHT; row++) {
			for(int col; col < Grid.WIDTH; col++) {
				g.set(row, col, Color.MAGENTA);				
			}
		}
		
		g.checkRows(); //clear rows
		
		for(int row; row < Grid.HEIGHT; row++) {
			for(int col; col < Grid.WIDTH; col++) {
				assertFalse(g.isSet(row, col));			
			}
		}
		
	}
	
	//create a test to see if piece moves left
	
	//create a test to see if a piece moves right
	
	//create a test to see if the row clears when full
	
	public static void main(String[] args) {
		

	}

}
