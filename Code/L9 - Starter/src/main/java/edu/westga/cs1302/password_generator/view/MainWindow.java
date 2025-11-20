package edu.westga.cs1302.password_generator.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import edu.westga.cs1302.password_generator.viewmodel.ViewModel;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/** Codebehind for the MainWindow of the Application.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {

    @FXML private CheckBox mustIncludeDigits;
    @FXML private CheckBox mustIncludeLowerCaseLetters;
    @FXML private CheckBox mustIncludeUpperCaseLetters;
    @FXML private TextField minimumLength;
    @FXML private Label errorTextLabel;
    @FXML private Menu file;
    @FXML private MenuItem fileAbout;
    @FXML private MenuItem fileClose;
    @FXML private MenuItem fileSave;
    @FXML private Label minLengthErrorText;
    @FXML private Button generatePasswordButton;
    @FXML private ListView<String> passwordHistory;
    
    private ViewModel vm;
    
    private void saveFile() {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Save Password History");
    	fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
    	File file = fileChooser.showSaveDialog(((Node) this.errorTextLabel).getScene().getWindow());
    	
    	if (file != null) {
    		try (FileWriter writer = new FileWriter(file)) {
    			for (String password : this.vm.getPasswordHistory()) {
    				writer.write(password + System.lineSeparator());
    			}
    			Alert alert = new Alert(Alert.AlertType.INFORMATION);
    			alert.setTitle("Save");
    			alert.setHeaderText("Save was successful");
    			alert.showAndWait();
    		} catch (IOException error) {
    			Alert alert = new Alert(Alert.AlertType.ERROR);
    			alert.setTitle("Save Error");
    			alert.setHeaderText("Errorrrrrrrrrrrrrrrrrrr");
    			alert.setContentText(error.getMessage());
    			alert.showAndWait();
    		}
    	}
    }
    
    private void aboutFile() {
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
    	alert.setTitle("About Password Generator");
    	alert.setHeaderText("Password Generator\n" + "Author: Tobi Osunlalu");
    	alert.setContentText("The Application generates passwords based on the users prefrences.");
    	alert.showAndWait();
    }
    
    private void closeFile() {
    	Window window = ((Node) this.errorTextLabel).getScene().getWindow();
        window.hide();
    }
    
    @FXML
    void initialize() {
    	this.vm = new ViewModel();
    	this.vm.getRequireDigits().bind(this.mustIncludeDigits.selectedProperty());
    	this.vm.getRequireLowercase().bind(this.mustIncludeLowerCaseLetters.selectedProperty());
    	this.vm.getRequireUppercase().bind(this.mustIncludeUpperCaseLetters.selectedProperty());
    	this.minimumLength.setText(this.vm.getMinimumLength().getValue());
    	this.vm.getMinimumLength().bind(this.minimumLength.textProperty());
    	
    	this.errorTextLabel.textProperty().bind(this.vm.getErrorText());
    	this.passwordHistory.setItems(this.vm.getPasswordHistory());
    	
    	this.minimumLength.textProperty().addListener((observable, oldValue, newValue) -> {
    	    boolean invalid;

    	    if (!newValue.matches("\\d+")) {
    	        invalid = true;
    	    } else {
    	        int value = Integer.parseInt(newValue);
    	        invalid = value <= 0;
    	    }
    	    this.minLengthErrorText.setVisible(invalid);
    	});
    	
    	this.generatePasswordButton.disableProperty().bind(
    			this.minimumLength.textProperty().isEmpty().or(this.minLengthErrorText.visibleProperty()));
    	
    	this.generatePasswordButton.setOnAction(
    			(event) -> { 
    				this.vm.generatePassword();
    			} 
    	);
    	
    	this.fileSave.setOnAction(
    			(event) -> {
    				this.saveFile();
    			}
    	);
    	
    	this.fileAbout.setOnAction(
    			(event) -> {
    				this.aboutFile();
    			}
    	);
    	
    	this.fileClose.setOnAction(
    			(event) -> {
    				this.closeFile();
    			}
    	);
    }
}
