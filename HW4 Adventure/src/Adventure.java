/*
 * File: Adventure.java
 * --------------------
 * This program plays the Adventure game from Assignment #4.
 */

import java.io.*;
import java.util.*;

/* Class: Adventure */
/**
 * This class is the main program class for the Adventure game.
 */

public class Adventure extends AdventureStub {
	

	/**
	 * This method is used only to test the program
	 */
	public static void setScanner(Scanner theScanner) {
		scan = theScanner;
		// Delete the following line when done
		AdventureStub.setScanner(theScanner);		
	}
	

	/**
	 * Runs the adventure program
	 */
	public static void main(String[] args) {
		//AdventureStub.main(args); // Replace with your code
		
		Adventure adventure = new Adventure();
		System.out.print("What will be your adventure today? ");
		String gameName = scan.nextLine().trim();
		gameName = gameName.substring(0, 1).toUpperCase() + gameName.substring(1);
		
		// Reads the room file
		String roomFile = gameName + "Rooms.txt";
		
		try {
			Scanner scanner = new Scanner(new File(roomFile));
			
			while (scanner.hasNextInt()) {
				AdvRoom a = AdvRoom.readFromFile(scanner);
				adventure.rooms.put(a.getRoomNumber(), a);
			}
		} catch (IOException e) {
			System.out.println("The room file " + roomFile + " could not be read.");
			return;
		}	
		
		// Reads the objects file
		String objectFile = gameName + "Objects.txt";
		
		if (!gameName.equals("Tiny")) {
			try {
				Scanner scanner = new Scanner(new File(objectFile));
				
				while (scanner.hasNextLine()) {
					AdvObject a = AdvObject.readFromFile(scanner);
					if (a != null) {
						AdvRoom ad = adventure.rooms.get(a.getInitialLocation());
						ad.addObject(a);
					}
				}
			} catch (IOException e) {
				System.out.println("The object file " + objectFile + " could not be read.");
				return;
			}
		
		
			// Reads the synonyms file
			String synonymFile = gameName + "Synonyms.txt";
			
			try {
				Scanner scanner = new Scanner(new File(synonymFile));
				
				String line;
				while (scanner.hasNextLine() && (line = scanner.nextLine().trim()).length() > 0) {
					String[] parts = line.split("=");
					adventure.synonyms.put(parts[0], parts[1]);
				}
			} catch (IOException e) {
				System.out.println("The synonyms file " + synonymFile + " could not be read.");
				return;
			}
		}
		adventure.run();
	}
	
	
	public void run() {
	
		// Start with the first room
		currentRoom = rooms.get(rooms.firstKey());
		// Description of the first room
		String[] descriptionArray = currentRoom.getDescription();
		for (int i = 0; i < descriptionArray.length; i++) {
			System.out.println(descriptionArray[i]);
		}	
		// Set the visit status of this room
	    currentRoom.setVisited(true);
	    
		while (true) {
			if (quit) {
				break;
			}
			System.out.print("> ");
			// Get the command
			String command = scan.nextLine().trim().toUpperCase();
			String[] parts = command.split("\\s+");
			// There is a command
			if (parts.length > 0) {
				AdvCommand cmd = null;
				AdvObject obj = null; // this is why take command returns null
				
				String com = parts[0].toUpperCase();
				// Look up for synonyms of the commands
				if (com.length() == 1 || synonyms.containsKey(com)) {
					com = synonyms.get(com);
				} else if (!synonyms.containsValue(com)) {
					System.out.println("Unavailable command.");
				} else {
					// When the command has 2 parts (command and object)
					if (parts.length > 1) {	
						
					}
					switch (com) {
					case "TAKE":
						// take command
						cmd = AdvCommand.TAKE;
						break;
					case "DROP":
						// drop command
						cmd = AdvCommand.DROP;
						break;
					case "HELP":
						cmd = AdvCommand.HELP;
						break;
					case "LOOK":
						cmd = AdvCommand.LOOK;
						break;
					case "INVENTORY":
						cmd = AdvCommand.INVENTORY;
						break;
					case "QUIT":
						cmd = AdvCommand.QUIT;
						break;
					default: // any motion command
						cmd = new AdvMotionCommand(com);
						break;
					}
					// execute the command
					cmd.execute(this, obj);
				}
			}
		}
	}
	

	/* Method: executeMotionCommand(direction) */
	/**
	 * Executes a motion command. This method is called from the
	 * AdvMotionCommand class to move to a new room.
	 * 
	 * @param direction
	 *            The string indicating the direction of motion
	 */
	public void executeMotionCommand(String direction) {
		int destinationRoom = 0;
		AdvMotionTableEntry[] table = currentRoom.getMotionTable();
		for (AdvMotionTableEntry t : table) {
			if (t.getDirection().equals(direction)) {
				destinationRoom = t.getDestinationRoom();
				currentRoom = rooms.get(destinationRoom);
			}
		}
		String[] descriptionArray = currentRoom.getDescription();
		for (int i = 0; i < descriptionArray.length; i++) {
			System.out.println(descriptionArray[i]);
		}		
		//super.executeMotionCommand(direction); // Replace with your code
	}
	

	/* Method: executeQuitCommand() */
	/**
	 * Implements the QUIT command. This command should ask the user to confirm
	 * the quit request and, if so, should exit from the play method. If not,
	 * the program should continue as usual.
	 */
	public void executeQuitCommand() {
		System.out.print("Are you sure (Y/N)? ");
		String answer = scan.nextLine().trim().toUpperCase();
		if (answer.equals("Y")) {
			quit = true;
		} else if (answer.equals("N")){
			quit = false;
		}
		//super.executeQuitCommand(); // Replace with your code
	}

	
	/* Method: executeHelpCommand() */
	/**
	 * Implements the HELP command. Your code must include some help text for
	 * the user.
	 */
	public void executeHelpCommand() {
		System.out.println("List of all possible commands: ");
		//super.executeHelpCommand(); // Replace with your code
	}

	
	/* Method: executeLookCommand() */
	/**
	 * Implements the LOOK command. This method should give the full description
	 * of the room and its contents.
	 */
	public void executeLookCommand() {
		String[] descriptionArray = currentRoom.getDescription();
		for (int i = 0; i < descriptionArray.length; i++) {
			System.out.println(descriptionArray[i]);
		}		
		//super.executeLookCommand(); // Replace with your code
	}

	
	/* Method: executeInventoryCommand() */
	/**
	 * Implements the INVENTORY command. This method should display a list of
	 * what the user is carrying.
	 */
	public void executeInventoryCommand() {
		if (inventory.isEmpty()) {
			System.out.println("You are empty-handed.");
		}
		for (AdvObject o : inventory) {
			System.out.println(o.getName() + ": " + o.getDescription());
		}
		//super.executeInventoryCommand(); // Replace with your code 
	}  

	
	/* Method: executeTakeCommand(obj) */
	/**
	 * Implements the TAKE command. This method should check that the object is
	 * in the room and deliver a suitable message if not.
	 * 
	 * @param obj
	 *            The AdvObject you want to take
	 */
	public void executeTakeCommand(AdvObject obj) {
		if (currentRoom.containsObject(obj)) {
			inventory.add(obj);
			currentRoom.removeObject(obj);
			System.out.println("Taken.");
		} else {
			System.out.println("I don't see any " + obj + ".");
		}
		//super.executeTakeCommand(obj); // Replace with your code
	}

	/* Method: executeDropCommand(obj) */
	/**
	 * Implements the DROP command. This method should check that the user is
	 * carrying the object and deliver a suitable message if not.
	 * 
	 * @param obj
	 *            The AdvObject you want to drop
	 */
	public void executeDropCommand(AdvObject obj) {
		if (inventory.contains(obj)) {
			inventory.remove(obj);
			currentRoom.addObject(obj);
			System.out.println("Dropped.");
		} else {
			System.out.println("You don't have any " + obj.getName() + " to drop.");
		}
		//super.executeDropCommand(obj); // Replace with your code
	}

	/* Private instance variables */
	
	// map of rooms
	private SortedMap <Integer, AdvRoom> rooms = new TreeMap <Integer, AdvRoom>();
	// list of objects contain in the player's inventory
	private ArrayList <AdvObject> inventory = new ArrayList <AdvObject>();
	// map of synonyms for this room
	private Map <String, String> synonyms = new HashMap <String, String>();
	// the current room
	private AdvRoom currentRoom;
	// Use this scanner for any console input
	private static Scanner scan = new Scanner(System.in);
	
	private boolean quit;
	
}
