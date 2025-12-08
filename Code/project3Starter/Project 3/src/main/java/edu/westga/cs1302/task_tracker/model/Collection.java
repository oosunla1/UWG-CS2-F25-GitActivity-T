package edu.westga.cs1302.task_tracker.model;

/** Stores basic information for a Collection
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class Collection {

	private final String name;
	
	/** Create a new Collection with the provided information.
	 * 
	 * @precondition name != null && !name.isEmpty()
	 * 
	 * @param name the name of the collection
	 */
	public Collection(String name) {
		if (name == null) {
			throw new IllegalArgumentException("name must not be null");
		}
		if (name.isEmpty()) {
			throw new IllegalArgumentException("name must not be empty");
		}
		this.name = name;
	}
	
	/** Return the name of the collection
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the name of the collection
	 */
	public String getName() {
		return this.name;
	}

	/** Returns the name of the collection to represent the collection as a String
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the name of the collection
	 */
	@Override
	public String toString() {
		return this.name;
	}
}
