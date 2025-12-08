package edu.westga.cs1302.task_tracker.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/** Controller class for MainWindow of the Task Tracker system.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {
	@FXML private Button addCollectionButtom;
    @FXML private ListView<String> collections;
    @FXML private TextField name;
    @FXML private Button removeCollectionButton;
    @FXML private MenuItem removeCollectionMenu;

    /**
     * Adds a new collection to the ListView using the text entered
     * in the collectionNameField.
     *
     * @precondition none
     * @postcondition A new collection with the given name is added to the list
     *                if the name is not blank.
     *
     * @param event required by JavaFX but not used
     */
    @FXML
    void addCollection(ActionEvent event) {
        String name = this.name.getText();

        if (name != null && !name.isEmpty()) {
            this.collections.getItems().add(name);
            this.name.clear();
        }
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
        String selected = this.collections.getSelectionModel().getSelectedItem();
        if (selected != null) {
            this.collections.getItems().remove(selected);
        }
    }

    /**
     * Handles updating UI state based on which collection
     * the user selects from the ListView.
     *
     * @precondition none
     * @postcondition The selected collection is stored in the selection model.
     *
     * @param event required by JavaFX but not used
     */
    @FXML
    void selectCollection(MouseEvent event) {
        //String selected = this.collections.getSelectionModel().getSelectedItem();
    }

    /** Perform any needed initialization of UI components and underlying objects.
     * 
     * @precondition none
     * @postcondition none
     * 
     */
    @FXML
    public void initialize() {
    	ContextMenu menu = new ContextMenu();
        MenuItem removeItem = new MenuItem("Remove Collection");
        menu.getItems().add(removeItem);
        this.collections.setContextMenu(menu);
        
        removeItem.setOnAction(event -> {
            String selected = this.collections.getSelectionModel().getSelectedItem();
            if (selected != null) {
                this.collections.getItems().remove(selected);
            }
        });
    }
}
