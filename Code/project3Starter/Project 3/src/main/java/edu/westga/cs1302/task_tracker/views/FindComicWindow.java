package edu.westga.cs1302.task_tracker.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/** Controller class for MainWindow of the Collection system.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class FindComicWindow {
	@FXML private TextField title;
	@FXML private TextField issueNumber;
	@FXML private Button searchButton;
	@FXML private Button cancelButton;
	//private Object vm;
	
	@FXML
    void cancelComic(ActionEvent event) {
    	this.closeWindow();
    }

    @FXML
    void searchComic(ActionEvent event) {

    }
    
    /**
     * Close this window.
     */
    private void closeWindow() {
        Stage stage = (Stage) this.cancelButton.getScene().getWindow();
        stage.close();
    }
    
    /** Initialize the controller with the ViewModel.
     * 
     * @param vm the MainWindowViewModel to use
     */
    public void setViewModel(Object vm) {
    	//this.vm = vm;
	}
}
