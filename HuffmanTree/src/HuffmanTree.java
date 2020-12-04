import java.io.PrintStream;

public class HuffmanTree {

	private HuffmanNode overallRoot;
	
	/**
	 * HuffmanTree constructor
	 * Constructs a HuffmanTree using the given array of frequencies where
	 *   count[i] is the number of occurrences of the character with ASCII value i.
	 * 
	 * @param count - integer array that contains the frequency 
	 *    of each character in a file. Has exactly 256 values. 
	 */
	public HuffmanTree(int[] count) {
		PriorityQueue <HuffmanNode> pQ = new PriorityQueue <HuffmanNode> ();
		
		for (int i = 1; i < count.length; i++) {
			if (count[i] > 0) {
				HuffmanNode node = null;
				node.frequency = count[i];
				node.ascii = i;
				pQ.offer(node);
			}
		}
		
		while (pQ.size() >= 2) {
			HuffmanNode node1 = pQ.poll();
			HuffmanNode node2 = pQ.poll();
			pQ.offer(new HuffmanNode(node1, node2));
		}
	}
	
	
	
	// inner class to describe a Huffman Node
	private class HuffmanNode implements Comparable <HuffmanNode> {
		public int frequency;
		public int ascii;
		public HuffmanNode left, right;
		
		public HuffmanNode(HuffmanNode n1, HuffmanNode n2) {
			this.left = n1;
			this.right = n2;
			this.frequency = n1.frequency + n2.frequency;
		}
		
		/**
		 * Returns < 0 if the frequency of this node is < frequency of h
		 * Returns 0 if the frequency of this node is == frequency of h
		 * Returns > 0 if the frequency of this node is > frequency of h
		 */
		public int compareTo(HuffmanNode h) {
			return this.frequency - h.frequency;
		}
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
