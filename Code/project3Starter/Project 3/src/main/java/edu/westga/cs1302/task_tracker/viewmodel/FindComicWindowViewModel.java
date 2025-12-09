package edu.westga.cs1302.task_tracker.viewmodel;

import edu.westga.cs1302.task_tracker.model.Collection;
import edu.westga.cs1302.task_tracker.model.Comic;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

/** View model for the FindComicWindow view
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class FindComicWindowViewModel {

    private final StringProperty comicTitle;
    private final StringProperty comicIssue;
    private final ObjectProperty<Comic> foundComic;

    private final Collection collection;

    /**
     * Initialize the ViewModel for finding a comic in a collection
     * @param collection the collection to search
     */
    public FindComicWindowViewModel(Collection collection) {
        this.collection = collection;
        this.comicTitle = new SimpleStringProperty("");
        this.comicIssue = new SimpleStringProperty("");
        this.foundComic = new SimpleObjectProperty<>(null);
    }

    /** Returns the property storing the title entered by the user.
     * 
     * @return the StringProperty representing the comics title.
     */
    public StringProperty comicTitle() {
        return this.comicTitle;
    }

    /** Returns the property storing the issue number entered by the user.
     * 
     * @return the StringProperty representing the comic issue number
     */
    public StringProperty comicIssue() {
        return this.comicIssue;
    }

    /** Returns the property storing the found comic after searching.
     * 
     * @return the ObjectProperty representing the found Comic
     */
    public ObjectProperty<Comic> foundComic() {
        return this.foundComic;
    }

    /** Searches for the comic with the title and issue number
     * 
     */
    public void searchComic() {
        Comic result = this.collection.findComic(this.comicTitle.get(), this.comicIssue.get());
        this.foundComic.set(result);
    }
}
