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
public class AddComicWindow {
	@FXML private TextField title;
	@FXML private TextField issueNumber;
	@FXML private Button confirmButton;
	@FXML private Button cancelButton;
	
    @FXML
    void cancelComic(ActionEvent event) {
    	((Stage) this.cancelButton.getScene().getWindow()).close();
    }

    @FXML
    void confirmComic(ActionEvent event) {
    	((Stage) this.confirmButton.getScene().getWindow()).close();
    }

}
