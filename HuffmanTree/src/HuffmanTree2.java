import java.io.*;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanTree2 {

	private HuffmanNode overallRoot; // root node of the HuffmanTree

	/**
	 * HuffmanTree constructor Constructs a HuffmanTree using the given array of
	 * frequencies where count[i] is the number of occurrences of the character with
	 * ASCII value i.
	 * 
	 * @param count - integer array that contains the frequency of each character in
	 *              a file. Has exactly 256 values.
	 */
	public HuffmanTree2(int[] count) {
		// create the priority queue
		PriorityQueue<HuffmanNode> pQ = new PriorityQueue<HuffmanNode>();

		// create a HuffmanNode for each character that has a frequency greater than 0
		for (int i = 1; i < count.length; i++) {
			if (count[i] > 0) {
//				HuffmanNode node = new HuffmanNode(i, count[i]);
				pQ.offer(new HuffmanNode(i, count[i]));
			}
		}

		// write code to create a fictitious end of file character.
		// Value will be one higher than the highest value of chars in the frequency
		// array passed to the constructor.
		// manually add this to the priority queue, and will have a frequency of 1.
		HuffmanNode eof = new HuffmanNode(count.length, 1);
		pQ.offer(eof);

		// turn these leaf nodes into a single tree
		// while not a single tree - remove 2 values from the priority queue and combine
		// to make a new branch node,
		// which we put back into the queue. continue combining until you get one tree
		// (HuffmanTree).
		while (pQ.size() >= 2) {
			HuffmanNode node1 = pQ.poll(); // left
			HuffmanNode node2 = pQ.poll(); // right
			pQ.offer(new HuffmanNode(-1, node1.frequency + node2.frequency, node1, node2)); // add combined node to the
																							// queue
		}

		// now that we have gone through pQ and there is only one left, make that the
		// overallRoot
		overallRoot = pQ.poll();
	}

	/**
	 * Constructs a HuffmanTree from a given input stream. Assumes that the standard
	 * bit representation has been used for the tree.
	 * 
	 * @param input
	 */
	public HuffmanTree2(BitInputStream input) {
		overallRoot = treeBuilding(input);
	}

	/**
	 * A helper function to create the Huffman tree from the Scanner
	 * 
	 * @param root
	 * @param ascii
	 * @param code
	 * @return root
	 */
	private HuffmanNode treeBuilding(BitInputStream input) {

		int bit = input.readBit();
		HuffmanNode node = new HuffmanNode(-1,0);
//		if (root == null) { //base case
//			root = new HuffmanNode(-1, 0);
//		} else 
		if (bit == 0) { // if it's a branch node
			node.left = treeBuilding(input);
			node.right = treeBuilding(input);
		} else { // it's a leaf node
			node.ascii = read9(input);
		}
		return node;
	}

	/**
	 * Assigns codes for each character of the tree. Assumes the array has null
	 * values before the method is called. Fills in a String for each character in
	 * the tree indicating its code.
	 * 
	 * @param codes
	 */
	void assign(String[] codes) {
		assignHelper(codes, overallRoot, "");
		// for each index that has an ascii char, replace it with the huffman code
	}

	/**
	 * Helper method to assign the codes for each character of the tree.
	 */
	void assignHelper(String[] codes, HuffmanNode root, String path) {
		if (root != null) {
			if (!root.hasLeft() && !root.hasRight()) { // if it's a leaf node
				codes[root.ascii] = path;
			} else { // it's a branch node
				assignHelper(codes, root.left, path + "0");
				assignHelper(codes, root.left, path + "1");
			}
		}
	}

	/**
	 * Writes the current tree to the output stream using the standard bit
	 * representation.
	 * 
	 * @param out
	 */
	void writeHeader(BitOutputStream out) {
		writeHelper(overallRoot, out);
	}

	/**
	 * helper method for write header
	 */
	void writeHelper(HuffmanNode root, BitOutputStream out) {
		// if it's a leaf node
		if (root != null && !root.hasLeft() && !root.hasRight()) {
			write9(out, 1);
		} else { // else it's a branch node
			write9(out, 0);
			writeHelper(root.left, out);
			writeHelper(root.right, out);
		}

	}

	/**
	 * Reads bits from the given input stream and writes the corresponding
	 * characters to the output. Stops reading when it encounters a character with
	 * value equal to eof. This is a pseudo-eof character, so it should not be
	 * written to the output file.
	 * 
	 * Assumes input stream contains a legal encoding of characters for this tree's
	 * Huffman code.
	 * 
	 * @param input
	 * @param output
	 * @param eof
	 * @return
	 */
	public void decode(BitInputStream input, PrintStream output, int charMax) {
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
		while (node.ascii != -1) {
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
		// return the ascii value of the leaf (no longer = -1)
		return node.ascii;
	}

	// pre : 0 <= n < 512
	// post: writes a 9-bit representation of n to the given output stream
	private void write9(BitOutputStream output, int n) {
		for (int i = 0; i < 9; i++) {
			output.writeBit(n % 2);
			n /= 2;
		}
	}

	// pre : an integer n has been encoded using write9 or its equivalent
	// post: reads 9 bits to reconstruct the original integer
	private int read9(BitInputStream input) {
		int multiplier = 1;
		int sum = 0;

		for (int i = 0; i < 9; i++) {
			sum += multiplier * input.readBit();
			multiplier *= 2;
		}
		return sum;

	}

	// inner class to describe a Huffman Node
	private class HuffmanNode implements Comparable<HuffmanNode> {
		public int frequency;
		public int ascii;
		public HuffmanNode left, right;

		public HuffmanNode(int ascii, int frequency) {
			this(ascii, frequency, null, null);
		}

		public HuffmanNode(int ascii, int frequency, HuffmanNode n1, HuffmanNode n2) {
			this.left = n1;
			this.right = n2;
			this.frequency = frequency; // n1.frequency + n2.frequency;
			this.ascii = ascii;
		}

		/**
		 * Returns < 0 if the frequency of this node is < frequency of h Returns 0 if
		 * the frequency of this node is == frequency of h Returns > 0 if the frequency
		 * of this node is > frequency of h
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
