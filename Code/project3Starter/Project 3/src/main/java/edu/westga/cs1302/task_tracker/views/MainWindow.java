package edu.westga.cs1302.task_tracker.views;

import java.io.IOException;

import edu.westga.cs1302.task_tracker.model.Collection;
import edu.westga.cs1302.task_tracker.model.Comic;
import edu.westga.cs1302.task_tracker.viewmodel.MainWindowViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** Controller class for MainWindow of the Collection system.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {
	@FXML private Button addCollection;
	@FXML private Button addComicButton;
    @FXML private ListView<Collection> collections;
    @FXML private ListView<Comic> comics;
    @FXML private TextField name;
    @FXML private Button removeCollectionButton;
    @FXML private Button removeComicButton;
    @FXML private MenuItem removeCollectionMenu;
    @FXML private MenuItem removeComicMenu;
    
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
    
    /**
     * Adds a new comic to the collection using
     *
     * @precondition none
     * @postcondition A new comic is added to the selected collection
     *
     * @param event required by JavaFX but not used
     */
    @FXML
    void addComic(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("AddComicWindow.fxml"));
    		Parent root = loader.load();
    		
    		AddComicWindow controller = loader.getController();
    		controller.setViewModel(this.vm);
    		
    		Stage stage = new Stage();
	        stage.setTitle("Add Comic");
	        stage.setScene(new Scene(root));
	        stage.initModality(Modality.APPLICATION_MODAL);
    		stage.showAndWait();

    	} catch (IOException error) {
    	    System.out.println("Failed to open Add Comic to Collection window: " + error.getMessage());
    	}
    }

    /**
     * Removes the currently selected comic from the ListView.
     *
     * @precondition none
     * @postcondition The selected comic is removed, if one exists.
     *
     * @param event required by JavaFX but not used
     */
    @FXML
    void removeComic(ActionEvent event) {
    	this.vm.removeSelectedComic();
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
    	
    	ContextMenu collectionMenu = new ContextMenu();
        MenuItem removeCollectionItem = new MenuItem("Remove Collection");
        collectionMenu.getItems().add(removeCollectionItem);
        this.collections.setContextMenu(collectionMenu);
        removeCollectionItem.setOnAction(event -> this.vm.removeSelectedCollection());

        this.comics.setItems(this.vm.getComicsForCollection());
        this.vm.selectedComic().bind(this.comics.getSelectionModel().selectedItemProperty());
        this.addComicButton.disableProperty().bind(this.vm.selectedCollection().isNull());
        this.removeComicButton.disableProperty().bind(this.vm.selectedComic().isNull());

        ContextMenu comicMenu = new ContextMenu();
        MenuItem removeComicItem = new MenuItem("Remove Comic from Collection");
        comicMenu.getItems().add(removeComicItem);
        this.comics.setContextMenu(comicMenu);
        removeComicItem.setOnAction(event -> this.vm.removeSelectedComic());
        
        this.comics.setItems(this.vm.getComicsForCollection());

        this.vm.selectedCollection().addListener((obs, oldCollection, newCollection) -> {
            if (newCollection != null) {
                this.comics.setItems(newCollection.getComics());
            } else {
                this.comics.setItems(FXCollections.observableArrayList());
            }
        });
    }
}