import java.io.PrintStream;

public class HuffmanTree {

	/**
	 * HuffmanTree constructor
	 * Constructs a HuffmanTree using the given array of frequencies where
	 *   count[i] is the number of occurrences of the character with ASCII value i.
	 * 
	 * @param count - integer array that contains the frequency 
	 *    of each character in a file. Has exactly 256 values. 
	 */
	public HuffmanTree(int[] count) {
		//use count.length instead of depending on the array being 256 values.
		//use a priority queue to build up the tree.
		//first, add a leaf node(HuffmanNode?) for each character that has a frequency greater than 0,
		//   added increasing order (char 0, char 1, etc)
		
		//use PriorityQueue<E> class that implements the Queue<E> interface to build the tree.
		//offer method used to "enqueue" and remove method to "dequeue". 
		//Also has isEmpty() and size() method available.
		
		//turn these leaf nodes into a single tree
		//while not a single tree - remove 2 values from the priority queue and combine to make a new branch node,
		//  which we put back into the queue. continue combining until you get one tree (HuffmanTree).
			
		//write code to create a fictitious end of file character. 
		//Value will be one higher than the highest value of chars in the frequency array passed to the constructor. 
		//manually add this to the priority queue, and will have a frequency of 1.
	}
	
	/**
	 * Writes the current tree to the given output stream in standard format.
	 * @param output
	 */
	void write(PrintStream output) {
		//each character should produce 2 lines of code:
		//1st line: ASCII value of the character. 2nd line: code(0s and 1s) or the char with this ASCII value
		
		//write in traversal order (order that the standard traversal of the tree would visit them).
		

	}
	
}
