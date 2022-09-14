package objects;

// This class provides a template for creating "Person" objects
public class Person extends Node {
	private String name;   // Name of person
	private int id;   // ID of person
	
	/**
	 * Initializes the values for a "Person" object
	 */
	public Person() {
		super();
		name = "";
		id = -1;
	}
	
	/**
	 * Initializes the values for a "Person" object
	 * @param name The name of this Person
	 * @param id The ID of this Person
	 */
	public Person (String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}
	
	/**
	 * Set up the name for this Person
	 * @param name The name of this Person
	 */
	public void setName(String name) {
		this.name = name.trim();
	}
	
	/**
	 * Set up the ID for this Person
	 * @param id The ID of this Person
	 */
	public void setID (int id) {
		this.id = id;
	}
	
	/**
	 * Get the name of this Person
	 * @return The name of this Person
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the ID of this Person
	 * @return The ID of this Person
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Randomly generate an ID number
	 * @return A randomly generated ID number
	 */
	public static int generateID() {
		String str = "";
		
		// ID's are 9 comprised of 9 numbers
		for (int i = 0; i < 9; i ++) {
			str += String.valueOf((int) (Math.random() * 10));
		}
		
		return(Integer.parseInt(str));
	}
}