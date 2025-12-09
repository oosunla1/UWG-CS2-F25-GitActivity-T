package edu.westga.cs1302.task_tracker.viewmodel;

import edu.westga.cs1302.task_tracker.model.Collection;
import edu.westga.cs1302.task_tracker.model.Comic;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** View model for the MainWindow view
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindowViewModel {

	private final StringProperty newCollection;
    private final ObservableList<Collection> collections;
    private final ObjectProperty<Collection> selectedCollection;
    private final StringProperty newComicTitle;
    private final StringProperty newComicIssue;
    private final ObjectProperty<Comic> selectedComic;
	
    /** Initialize the MainWindowViewModel
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 */
    public MainWindowViewModel() {
        this.newCollection = new SimpleStringProperty("");
        this.collections = FXCollections.observableArrayList();
        this.selectedCollection = new SimpleObjectProperty<>(null);
        this.newComicTitle = new SimpleStringProperty("");
        this.newComicIssue = new SimpleStringProperty("");
        this.selectedComic = new SimpleObjectProperty<>(null);
    }

    /** Returns the property storing the name of the new collection
     * 
     * @precondition none
	 * @postcondition none
	 * 
     * @return the StringProperty representing the new collection name
     */
    public StringProperty newCollection() {
        return this.newCollection;
    }
	
    /** Gets the list of all collections
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return an ObservableList of Collection objects
	 */
    public ObservableList<Collection> getCollections() {
        return this.collections;
    }
    
    /** Returns the property storing the currently selected collection
     * 
	 * @precondition none
	 * @postcondition none
	 * 
     * @return the ObjectProperty representing the selected Collection
     */
    public ObjectProperty<Collection> selectedCollection() {
        return this.selectedCollection;
    }

    /** Adds a new collection based on the value in newCollection.
     * 
     * @precondition newCollection.get() is not empty
     * @postcondition A new Collection is added to the list
     * 
     */
    public void addCollection() {
        String name = this.newCollection.get();
        if (name != null && !name.isEmpty()) {
            this.collections.add(new Collection(name));
            this.newCollection.set("");
        }
    }
    
    /** Removes the currently selected collection.
     *
     * @precondition none
     * @postcondition selected collection is removed if one exists
     */
    public void removeSelectedCollection() {
    	Collection selected = this.selectedCollection.get();    	
    	if (selected != null) {
    		this.collections.remove(selected);		
    	}
    }
    
    /** Returns the property storing the title of the new comic
     * 
     * @precondition none
	 * @postcondition none
	 * 
     * @return the StringProperty representing the new comic title
     */
    public StringProperty newComicTitle() {
        return this.newComicTitle;
    }
    
    /** Returns the property storing the issue of the new comic
     * 
     * @precondition none
	 * @postcondition none
	 * 
     * @return the StringProperty representing the new comic issue number
     */
    public StringProperty newComicIssue() {
        return this.newComicIssue;
    }
    
    /** Returns the property storing the currently selected comic
     * 
	 * @precondition none
	 * @postcondition none
	 * 
     * @return the ObjectProperty representing the selected comic
     */
    public ObjectProperty<Comic> selectedComic() {
        return this.selectedComic;
    }
    
    /** Gets the list of all comics from the selected collection
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return an ObservableList of Comic objects for the selected collection
	 */
    public ObservableList<Comic> getComicsForCollection() {
        Collection selected = this.selectedCollection.get();
        if (selected == null) {
            return FXCollections.observableArrayList(); 
        }
        return FXCollections.observableArrayList(selected.getComics());
    }
    
    /** Adds a new comic to the selected collection.
     * 
     * @precondition title and issue number cannot be null
     * @postcondition A new comic is added to the list
     * 
     */
    public void addComic() {
        Collection selected = this.selectedCollection.get();
        if (selected == null) {
            return;
        }

        String title = this.newComicTitle.get();
        String issueNumber = this.newComicIssue.get();
        if (title == null || title.isEmpty() || issueNumber == null || issueNumber.isEmpty()) {
            return;
        }

        Comic newComic = new Comic(title, issueNumber);
        selected.addComic(newComic);
        this.newComicTitle.set("");
        this.newComicIssue.set("");
    }
    
    /** Removes the currently selected comic.
    *
    * @precondition none
    * @postcondition selected comic is removed if one exists
    */
    public void removeSelectedComic() {
        Collection selected = this.selectedCollection.get();
        Comic comic = this.selectedComic.get();
        if (selected != null && comic != null) {
            selected.removeComic(comic);
        }
    }
}
