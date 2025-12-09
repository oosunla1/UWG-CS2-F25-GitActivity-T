package edu.westga.cs1302.task_tracker.model;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Stores basic information for a Collection
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class Collection {

	private final String name;
	private final ObservableList<Comic> comics;
	private final Map<String, Comic> comicSearchFor;
	
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
		this.comics = FXCollections.observableArrayList();
		this.comicSearchFor = new HashMap<>();
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
	public ObservableList<Comic> getComics() {
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
        String key = comic.getTitle().toLowerCase() + " " + comic.getIssueNumber();
        this.comicSearchFor.put(key, comic);
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
        String key = comic.getTitle().toLowerCase() + " " + comic.getIssueNumber();
        this.comicSearchFor.remove(key, comic);
    }

	/** Searches for a comic using the given title and issue number
	 * 
	 * @param title the title of the comic being searched
	 * @param issue the issue number of the comic being searched
	 * @return the location of comic searched for, if one exists
	 */
	public Comic findComic(String title, String issue) {
	    if (title == null || issue == null) {
	        return null;
	    }
	    String key = title.toLowerCase() + " " + issue;
	    return this.comicSearchFor.get(key);
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
