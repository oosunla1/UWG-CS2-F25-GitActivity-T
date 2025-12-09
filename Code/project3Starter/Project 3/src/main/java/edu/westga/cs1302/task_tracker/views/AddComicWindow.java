package edu.westga.cs1302.task_tracker.views;

import edu.westga.cs1302.task_tracker.viewmodel.MainWindowViewModel;
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
	
	private MainWindowViewModel vm;
	
    @FXML
    void cancelComic(ActionEvent event) {
    	this.closeWindow();
    }

    @FXML
    void confirmComic(ActionEvent event) {
    	this.vm.addComic();
        this.closeWindow();
    }
    
    /**
     * Close this window.
     */
    private void closeWindow() {
        Stage stage = (Stage) this.confirmButton.getScene().getWindow();
        stage.close();
    }
    
    /** Initialize the controller with the ViewModel.
     * 
     * @param vm the MainWindowViewModel to use
     */
    public void setViewModel(MainWindowViewModel vm) {
    	this.vm = vm;
		this.title.textProperty().bindBidirectional(this.vm.newComicTitle());
        this.issueNumber.textProperty().bindBidirectional(this.vm.newComicIssue());
        this.confirmButton.disableProperty().bind(
        	    this.vm.newComicTitle().isEmpty().or(this.vm.newComicIssue().isEmpty()));
	}
}
