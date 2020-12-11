import java.io.PrintStream;
import java.util.PriorityQueue;
import java.util.Scanner;

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
		
		/**
		*Loop through the count array and put the ascii values and frequencies into the queue
		*/
		for (int i = 1; i < count.length; i++) {
			if (count[i] > 0) {
				HuffmanNode node = new HuffmanNode(i, count[i]);
				pQ.offer(node);
			}
		}
		
		/** Fictitious end of file character. 
		 * Value will be one higher than the highest value of chars in the frequency array passed to the constructor. 
		 * Manually add this to the priority queue, and will have a frequency of 1.
		 */
		HuffmanNode endOfFile = new HuffmanNode(count.length, 1);
		pQ.offer(endOfFile);
		
		/**
		 * Loop through the queue to combine nodes and build the tree
		 */
		while (pQ.size() >= 2) {
			HuffmanNode node1 = pQ.poll();
			HuffmanNode node2 = pQ.poll();
			pQ.offer( new HuffmanNode(-1, node1.frequency + node2.frequency, node1, node2));
		}
		// Set overallRoot to the top of the tree
		overallRoot = pQ.poll();	
	}
	
	
	/**
	 * Writes the current tree to the given output stream in standard format.
	 * @param output
	 */
	public void write(PrintStream output) {
		write(output, overallRoot, "");
	}
	/**
	 * A helper function for recursion
	 * 
	 * @param output
	 * @param root
	 * @param code
	 */
	private void write(PrintStream output, HuffmanNode root, String code) {
		// base case: if the current root does not have any children and is not null
		if (root != null && !root.hasLeft() && !root.hasRight()) {
			output.println(root.ascii + System.lineSeparator() + code);
		} 
		// recursive step: go to the left and right children of the current root until the base case is found 
		// (left first)
		else { 
			write(output, root.left, code + "0");
			write(output, root.right, code + "1");
		}
	}
	
	
	/**
	 * Constructs a Huffman tree from the Scanner.  Assumes the Scanner contains a tree description in standard format.
	 * 
	 * @param input
	 */
	public HuffmanTree(Scanner input) {
		while (input.hasNextLine()) {
			// scan for the input ascii value and code
			int ascii = Integer.parseInt(input.nextLine());
			String code = input.nextLine();
			// create the tree and set to overallRoot
			overallRoot = treeBuilding(overallRoot, ascii, code); 
		}
	}
	/**
	 * A helper function to create the Huffman tree from the Scanner
	 * 
	 * @param root
	 * @param ascii
	 * @param code
	 * @return root
	 */
	private HuffmanNode treeBuilding(HuffmanNode root, int ascii, String code) {
		// if there is nothing in the tree yet, build the root
		if (root == null) {
			root = new HuffmanNode(-1, 0);
		} 
		// base case: create the left and right children of the tree
		if (code.length() == 1) {
			if (code.equals("0")) {
				root.left = new HuffmanNode(ascii, 0);
			} else {
				root.right = new HuffmanNode(ascii, 0);
			}
		} 
		// recursive step: examine the code and traverse down the tree
		else {
			if (code.charAt(0) == '0') {
				root.left = treeBuilding(root.left, ascii, code.substring(1));
			} else {
				root.right = treeBuilding(root.right, ascii, code.substring(1));
			}
		}
		return root;
	}
	

	/**
	 * Reads bits from the given input stream and writes the corresponding characters to the output. 
	 * Stops reading when it encounters a character with value equal to eof.  This is a pseudo-eof character, 
	 * so it should not be written to the output file.  Assumes the input stream contains a legal encoding 
	 * of characters for this tree’s Huffman code.
	 * 
	 * @param input
	 * @param output
	 * @param charMax
	 */
	public void decode (BitInputStream input, PrintStream output, int charMax) {
		int ascii = readBit(input); // read the bits from the input for the ascii value 
		// only proceed if the ascii value does not exceed the value of charMax
		// (not at the end of the file)
		while (ascii < charMax) {
			output.write(ascii); // write the ascii value to the output file
			ascii = readBit(input); // read the next ascii value
		}
	}
	/**
	 * Reads the bits from the input file until it reaches the leaf node
	 * 
	 * @param input  
	 * @return ascii value of the leaf
	 */
	private int readBit(BitInputStream input) {
		HuffmanNode node = overallRoot;
		
		// while the leaf has not been reached, keep traversing the tree to the left or right
		// depends on the bits read
		while (node.ascii < 0) {
			int bit = input.readBit(); // read the bit
			// traverse to the left if bit == 0
			if (bit == 0) {
				node = node.left;
			} 
			// traverse to the right if bit == 1
			else {
				node = node.right;
			}
		}
		// return the ascii value of the leaf (no longer < 0 (-1))
		return node.ascii;
	}
	   
	
	   
	/**
	 * The inner HuffmanNode class
	 * 
	 * @author CSC 143
	 */
	private class HuffmanNode implements Comparable <HuffmanNode> {
		public int frequency; // the counts of this character
		public int ascii; // the ascii value of this character
		public HuffmanNode left, right; // left and right children of the tree
			
		// constructors
		public HuffmanNode(int ascii, int frequency) {
			this(ascii, frequency, null, null);
		}	
		public HuffmanNode(int ascii, int frequency, HuffmanNode n1, HuffmanNode n2) {
			this.ascii = ascii;
			this.frequency = frequency;
			this.left = n1;
			this.right = n2;
		}
		
		/**
		 * Returns < 0 if the frequency of this node is < frequency of h
		 * Returns 0 if the frequency of this node is == frequency of h
		 * Returns > 0 if the frequency of this node is > frequency of h
		 */
		public int compareTo(HuffmanNode h) {
			return this.frequency - h.frequency;
		}	
		/**
		 * @return true if this node has a left children
		 */
		public boolean hasLeft() {
			return this.left != null;
		}
		/**	
		 * @return true if this node has a right children
		 */
		public boolean hasRight() {
			return this.right != null;
		}
	}	
}
