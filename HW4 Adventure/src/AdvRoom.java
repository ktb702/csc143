/*
 * File: AdvRoom.java
 * ------------------
 * This file defines a class that models a single room in the
 * Adventure game.
 */

import java.io.*;
import java.util.*;

/* Class: AdvRoom */
/**
 * This class defines a single room in the Adventure game. A room is
 * characterized by the following properties:
 * 
 * <ul>
 * <li>A room number, which must be greater than zero
 * <li>Its name, which is a one-line string identifying the room
 * <li>Its description, which is a multiline array describing the room
 * <li>A list of objects contained in the room
 * <li>A flag indicating whether the room has been visited
 * <li>A motion table specifying the exits and where they lead </li>
 * 
 * The external format of the room data file is described in the assignment
 * handout. The comments on the methods exported by this class show how to use
 * the initialized data structure.
 */

public class AdvRoom extends AdvRoomStub {
	
	 // room number
	private int roomNumber;
	// name of this room
	private String name;
	// description of this room
	private String[] description;
	// list of objects contain in this room
	private ArrayList <AdvObject> objects = new ArrayList <AdvObject>();
	// indicate if the room has been visited
	private boolean isVisited;
	// motion table specifying the exits and where they lead
	private AdvMotionTableEntry[] motionTable;
	
	

	/* Method: getRoomNumber() */
	/**
	 * Returns the room number.
	 * 
	 * @usage int roomNumber = room.getRoomNumber();
	 * @return The room number
	 */
	public int getRoomNumber() {
		return roomNumber;
//		return super.getRoomNumber(); // Replace with your code
	}

	/* Method: getName() */
	/**
	 * Returns the room name, which is its one-line description.
	 * 
	 * @usage String name = room.getName();
	 * @return The room name
	 */
	public String getName() {
		return name;
//		return super.getName(); // Replace with your code
	}

	/* Method: getDescription() */
	/**
	 * Returns an array of strings that correspond to the long description of
	 * the room (including the list of the objects in the room).
	 * 
	 * @usage String[] description = room.getDescription();
	 * @return An array of strings giving the long description of the room
	 */
	public String[] getDescription() {
//		return super.getDescription(); // Replace with your code

		int i = 0;
		String[] newDescription = new String[description.length + objects.size()];
		
		//foreach room description, add it to the new description
		for (String s : description) {
			newDescription[i++] = s;
		}
		//foreach object, add it to the description of the room
		for (AdvObject o : objects) {
			newDescription[i++] = "There is a " + o.getDescription() + " here.";
		}
		return newDescription;
	}

	/* Method: addObject(obj) */
	/**
	 * Adds an object to the list of objects in the room.
	 * 
	 * @usage room.addObject(obj);
	 * @param The
	 *            AdvObject to be added
	 */
	public void addObject(AdvObject obj) {
		objects.add(obj);
//		super.addObject(obj);
	}

	/* Method: removeObject(obj) */
	/**
	 * Removes an object from the list of objects in the room.
	 * 
	 * @usage room.removeObject(obj);
	 * @param The
	 *            AdvObject to be removed
	 */
	public void removeObject(AdvObject obj) {
		objects.remove(obj);
//		super.removeObject(obj);
	}

	/* Method: containsObject(obj) */
	/**
	 * Checks whether the specified object is in the room.
	 * 
	 * @usage if (room.containsObject(obj)) . . .
	 * @param The
	 *            AdvObject being tested
	 * @return true if the object is in the room, and false otherwise
	 */
	public boolean containsObject(AdvObject obj) {
		return objects.contains(obj);
//		return super.containsObject(obj);
	}

	/* Method: getObjectCount() */
	/**
	 * Returns the number of objects in the room.
	 * 
	 * @usage int nObjects = room.getObjectCount();
	 * @return The number of objects in the room
	 */
	public int getObjectCount() {
		return objects.size();
//		return super.getObjectCount();
	}

	/* Method: getObject(index) */
	/**
	 * Returns the specified element from the list of objects in the room.
	 * 
	 * @usage AdvObject obj = room.getObject(index);
	 * @return The AdvObject at the specified index position
	 */
	public AdvObject getObject(int index) {
		return objects.get(index);
//		return super.getObject(index);
	}

	/* Method: setVisited(flag) */
	/**
	 * Sets the flag indicating that this room has been visited according to the
	 * value of the parameter. Calling setVisited(true) means that the room has
	 * been visited; calling setVisited(false) restores its initial unvisited
	 * state.
	 * 
	 * @usage room.setVisited(flag);
	 * @param flag
	 *            The new state of the "visited" flag
	 */
	public void setVisited(boolean flag) {
		isVisited = flag;
//		super.setVisited(flag); // Replace with your code
	}

	/* Method: hasBeenVisited() */
	/**
	 * Returns true if the room has previously been visited.
	 * 
	 * @usage if (room.hasBeenVisited()) . . .
	 * @return true if the room has been visited; false otherwise
	 */
	public boolean hasBeenVisited() {
		return isVisited;
//		return super.hasBeenVisited(); // Replace with your code
	}

	/* Method: getMotionTable() */
	/**
	 * Returns the motion table associated with this room, which is an array of
	 * directions, room numbers, and enabling objects stored in a
	 * AdvMotionTableEntry.
	 * 
	 * @usage AdvMotionTableEntry[] motionTable = room.getMotionTable();
	 * @return The array of motion table entries associated with this room
	 */
	public AdvMotionTableEntry[] getMotionTable() {
		return motionTable;
//		return super.getMotionTable(); // Replace with your code
	}

	/* Method: readFromFile(rd) */
	/**
	 * Reads the data for this room from the Scanner scan, which must have been
	 * opened by the caller. This method returns a room if the room
	 * initialization is successful; if there are no more rooms to read,
	 * readFromFile returns null.
	 * 
	 * @usage AdvRoom room = AdvRoom.readFromFile(scan);
	 * @param scan
	 *            A scanner open on the rooms data file
	 * @return a room if successfully read; null if at end of file
	 */
	public static AdvRoom readFromFile(Scanner scan) {
//		return AdvRoomStub.readFromFile(scan); // Replace with your code
		
		AdvRoom room = new AdvRoom();
		
		// read the data for the room number
		room.roomNumber = scan.nextInt();
		// read the newline after the int
		scan.nextLine();
		// read the data for the name of the room
		room.name = scan.nextLine();
		
		// read the data for the description of this room
		ArrayList <String> d = new ArrayList <String>();
		String line;
		while (!(line = scan.nextLine()).trim().equals("-----")) {
			d.add(line);
		}
		room.description = d.toArray(new String[d.size()]);
		
		// read the data for the motion table of this room 
		ArrayList <AdvMotionTableEntry> list = new ArrayList <AdvMotionTableEntry>();
		while (scan.hasNextLine() && (line = scan.nextLine()).trim().length() > 0) {
			String[] parts = line.split("\\s+|/");
			String object = (parts.length == 3) ? parts[2] : null;
			AdvMotionTableEntry entry = new AdvMotionTableEntry(parts[0], Integer.parseInt(parts[1]), object);
			list.add(entry);
		}
		room.motionTable = list.toArray(new AdvMotionTableEntry[list.size()]);
		
		return room;
	}

	/* Private instance variables */
	// Add your own instance variables here
}
