package edu.westga.cs1302.task_tracker.views;

import edu.westga.cs1302.task_tracker.model.Collection;
import edu.westga.cs1302.task_tracker.viewmodel.MainWindowViewModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

/** Controller class for MainWindow of the Collection system.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {
	@FXML private Button addCollection;
    @FXML private ListView<Collection> collections;
    @FXML private TextField name;
    @FXML private Button removeCollectionButton;
    @FXML private MenuItem removeCollectionMenu;
    
    private MainWindowViewModel vm;

    /**
     * Adds a new collection to the ListView using the text entered
     * in the collection name field.
     *
     * @precondition none
     * @postcondition A new collection with the given name is added to the list
     *                if the name is not blank.
     *
     * @param event required by JavaFX but not used
     */
    @FXML
    void addCollection(ActionEvent event) {
    	this.vm.addCollection();
    }

    /**
     * Removes the currently selected collection from the ListView.
     *
     * @precondition none
     * @postcondition The selected collection is removed, if one exists.
     *
     * @param event required by JavaFX but not used
     */
    @FXML
    void removeCollection(ActionEvent event) {
    	this.vm.removeSelectedCollection();
    }

    /** Perform any needed initialization of UI components and underlying objects.
     * 
     * @precondition none
     * @postcondition none
     * 
     */
    @FXML
    public void initialize() {
    	this.vm = new MainWindowViewModel();
    	this.name.textProperty().bindBidirectional(this.vm.newCollection());
    	this.collections.setItems(this.vm.getCollections());
    	this.vm.selectedCollection().bind(this.collections.getSelectionModel().selectedItemProperty());
    	this.addCollection.disableProperty().bind(this.vm.newCollection().isEmpty());
    	
    	ContextMenu menu = new ContextMenu();
        MenuItem removeItem = new MenuItem("Remove Collection");
        menu.getItems().add(removeItem);
        this.collections.setContextMenu(menu);
        
        removeItem.setOnAction(event -> this.vm.removeSelectedCollection());
    }
}
