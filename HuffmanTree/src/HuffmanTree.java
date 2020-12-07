import java.io.*;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanTree {

	private HuffmanNode overallRoot; //root node of the HuffmanTree
	
	/**
	 * HuffmanTree constructor
	 * Constructs a HuffmanTree using the given array of frequencies where
	 *   count[i] is the number of occurrences of the character with ASCII value i.
	 * Used by MakeCode.java
	 * @param count - integer array that contains the frequency 
	 *    of each character in a file. Has exactly 256 values. 
	 */
	public HuffmanTree(int[] count) {
		//create the priority queue
		PriorityQueue<HuffmanNode> pQ = new PriorityQueue<HuffmanNode>();
		
		//create a HuffmanNode for each character that has a frequency greater than 0
		for (int i = 1; i < count.length; i++) {
			if (count[i] > 0) {
//				HuffmanNode node = null;
//				node.frequency = count[i];
//				node.ascii = i;
				HuffmanNode node = new HuffmanNode(i, count[i]);
				pQ.offer(node);
			}
		}
		
		//turn these leaf nodes into a single tree
		//while not a single tree - remove 2 values from the priority queue and combine to make a new branch node,
		//  which we put back into the queue. continue combining until you get one tree (HuffmanTree).
		while (pQ.size() >= 2) {
			HuffmanNode node1 = pQ.poll(); //should be remove instead of poll?
			HuffmanNode node2 = pQ.poll();
			pQ.offer(new HuffmanNode(-1, node1.frequency + node2.frequency, node1, node2)); //add combined node to the queue
		}
			
		//write code to create a fictitious end of file character. 
		//Value will be one higher than the highest value of chars in the frequency array passed to the constructor. 
		//manually add this to the priority queue, and will have a frequency of 1.
		HuffmanNode eof = new HuffmanNode(count.length, 1);
		pQ.offer(eof);
		
		//now that we have gone through pQ and there is only one left, make that the overallRoot
		overallRoot = pQ.poll(); //not sure if we should use poll or remove?
	}
	
	/**
	 * Constructs a HuffmanTree from a scanner.
	 * Assumes the Scanner contains a tree description in standard format.
	 * Used by Decode.java
	 * @param input
	 */
	public HuffmanTree(Scanner input) {
		while (input.hasNextLine()) {
			// scan for the input ascii value and code
			int ascii = input.nextInt();
			input.nextLine();
			String code = input.nextLine();
			
			treeBuilding(overallRoot, ascii, code);
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
		if (root == null) {
			root = new HuffmanNode(-1, 0);
		} else if (code.length() == 1) {
			if (code.equals("0")) {
				root.left = new HuffmanNode(ascii, 0);
			} else {
				root.right = new HuffmanNode(ascii, 0);
			}
		} else {
			if (code.charAt(0) == '0') {
				treeBuilding(root.left, ascii, code.substring(1));
			} else {
				treeBuilding(root.right, ascii, code.substring(1));
			}
		}
		return root;
	}
	
	/**
	 * Reads bits from the given input stream and writes the corresponding characters to the output. 
	 * Stops reading when it encounters a character with value equal to eof. This is a pseudo-eof character,
	 * so it should not be written to the output file. 
	 * 
	 * Assumes input stream contains a legal encoding of characters for this tree's Huffman code.
	 * @param input
	 * @param output
	 * @param eof
	 * @return 
	 */
	public void decode(BitInputStream input, PrintStream output, int eof) {
		
	}
	
	/**
	 * Writes the current tree to the given output stream in standard format.
	 * @param output
	 */
	void write(PrintStream output) {
// 		if(overallRoot != null) {
			toFile(overallRoot, output, "");
		//}
	}
	
	/**
	 * helper function to write output to file
	 * 	each character should produce 2 lines of code in traversal order:
	 *1st line: ASCII value of the character. 2nd line: code(0s and 1s) or the char with this ASCII value
	 * @param overallRoot
	 * @param output
	 * @param ascii
	 */
	void toFile(HuffmanNode root, PrintStream output, String code) {
		//base case - we are at the top of the tree
		 if (root != null && !root.hasLeft() && !root.hasRight()) { 
			 output.println(root.ascii);
			 output.println(code);
	      } else { //otherwise traverse the tree and update the code
			 toFile(root.left, output, code + "0");
			 toFile(root.right, output, code + "1"); 
	      }
	}

	
	// inner class to describe a Huffman Node
	private class HuffmanNode implements Comparable <HuffmanNode> {
		public int frequency;
		public int ascii;
		public HuffmanNode left, right;
		
		public HuffmanNode(int ascii, int frequency) {
			this(ascii, frequency, null, null);
		}
		
		public HuffmanNode(int ascii, int frequency, HuffmanNode n1, HuffmanNode n2) {
			this.left = n1;
			this.right = n2;
			this.frequency = frequency; //n1.frequency + n2.frequency;
			this.ascii = ascii;
		}
		
		/**
		 * Returns < 0 if the frequency of this node is < frequency of h
		 * Returns 0 if the frequency of this node is == frequency of h
		 * Returns > 0 if the frequency of this node is > frequency of h
		 */
		public int compareTo(HuffmanNode h) {
			return this.frequency - h.frequency;
		}
		
		public boolean hasLeft() {
			return this.left != null;
		}
		
		public boolean hasRight() {
			return this.right != null;
		}
	}
}
