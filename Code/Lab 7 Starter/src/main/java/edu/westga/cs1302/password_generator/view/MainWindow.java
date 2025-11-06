package edu.westga.cs1302.password_generator.view;

import java.util.Random;

import edu.westga.cs1302.password_generator.model.PasswordGenerator;
import edu.westga.cs1302.password_generator.viewmodel.MainWindowViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/** Codebehind for the MainWindow of the Application.
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {

    @FXML private CheckBox mustIncludeDigits;
    @FXML private CheckBox mustIncludeLowerCaseLetters;
    @FXML private CheckBox mustIncludeUpperCaseLetters;
    @FXML private TextField minimumLength;
    @FXML private TextArea output;
    
    private PasswordGenerator generator;
	private MainWindowViewModel viewModel;

    @FXML
    void generatePassword(ActionEvent event) {
    	try {
            this.viewModel.generatePassword();
        } catch (IllegalArgumentException error) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Invalid Minimum Length: " + error.getMessage());
            alert.show();
        }
    }

    private void bindComponentsToViewModel() {
    	this.viewModel = new MainWindowViewModel();
    	
        this.mustIncludeDigits.selectedProperty().bindBidirectional(this.viewModel.mustIncludeDigitsProperty());
        this.mustIncludeLowerCaseLetters.selectedProperty().bindBidirectional(this.viewModel.mustIncludeLowerCaseLettersProperty());
        this.mustIncludeUpperCaseLetters.selectedProperty().bindBidirectional(this.viewModel.mustIncludeUpperCaseLettersProperty());
        this.minimumLength.textProperty().bindBidirectional(this.viewModel.minimumLengthProperty());

        this.output.textProperty().bind(this.viewModel.outputProperty());
    }
    
    @FXML
	void handleGeneration() {
		this.viewModel.generatePassword();
	}
    
    @FXML
    void initialize() {
        assert this.mustIncludeDigits != null : "fx:id=\"mustIncludeDigits\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.mustIncludeLowerCaseLetters != null : "fx:id=\"mustIncludeLowerCaseLetters\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.mustIncludeUpperCaseLetters != null : "fx:id=\"mustIncludeUpperCaseLetters\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.minimumLength != null : "fx:id=\"minimumLength\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.output != null : "fx:id=\"output\" was not injected: check your FXML file 'MainWindow.fxml'.";

        this.minimumLength.setText("1");
        Random randomNumberGenerator = new Random();
        this.generator = new PasswordGenerator(randomNumberGenerator.nextLong());
        
        this.bindComponentsToViewModel();
    }
}
