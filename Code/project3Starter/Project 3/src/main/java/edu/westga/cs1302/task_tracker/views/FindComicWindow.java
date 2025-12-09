package edu.westga.cs1302.task_tracker.views;

import edu.westga.cs1302.task_tracker.model.Comic;
import edu.westga.cs1302.task_tracker.viewmodel.FindComicWindowViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
	
	private FindComicWindowViewModel vm;
	
	@FXML
    void cancelComic(ActionEvent event) {
    	this.closeWindow();
    }

    @FXML
    void searchComic(ActionEvent event) {
    	this.vm.searchComic();
        Comic found = this.vm.foundComic().get();
        if (found != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Comic Found");
            alert.setHeaderText(null);
            alert.setContentText("Found: " + found);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Comic Not Found");
            alert.setHeaderText(null);
            alert.setContentText("No comic matches that title and issue number.");
            alert.showAndWait();
        }
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
    public void setViewModel(FindComicWindowViewModel vm) {
    	this.vm = vm;
        this.title.textProperty().bindBidirectional(this.vm.comicTitle());
        this.issueNumber.textProperty().bindBidirectional(this.vm.comicIssue());

        this.searchButton.disableProperty().bind(
            this.vm.comicTitle().isEmpty().or(this.vm.comicIssue().isEmpty())
        );
	}
}
