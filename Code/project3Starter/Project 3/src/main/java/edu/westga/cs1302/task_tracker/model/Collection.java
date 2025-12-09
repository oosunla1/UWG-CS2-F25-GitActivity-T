package edu.westga.cs1302.task_tracker.model;

import java.util.ArrayList;
import java.util.List;

/** Stores basic information for a Collection
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class Collection {

	private final String name;
	private final List<Comic> comics;
	
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
		this.comics = new ArrayList<>();
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
	
	/** Get the comics in the selected collection
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the comics in the selected collection
	 */
	public List<Comic> getComics() {
        return this.comics;
    }
	
	/** Adds a comic to this collection.
	 * 
	 * @precondition comic != null
	 * @postcondition  comic is added to the list of comics in this collection.
	 * 
	 * @param comic the Comic object to add
	 * @throws IllegalArgumentException if comic is null
	 */
	public void addComic(Comic comic) {
        if (comic == null) {
            throw new IllegalArgumentException("Comic must not be null");
        }
        this.comics.add(comic);
    }
	
	/** Removes a comic to this collection.
	 * 
	 * @precondition none
	 * @postcondition  comic is removed from list of comics, if it exists.
	 * 
	 * @param comic the Comic object to remove
	 */
	public void removeComic(Comic comic) {
        this.comics.remove(comic);
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
