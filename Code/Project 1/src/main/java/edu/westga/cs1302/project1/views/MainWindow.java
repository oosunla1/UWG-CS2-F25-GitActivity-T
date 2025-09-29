package edu.westga.cs1302.project1.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * Controller class for drawing various things to our canvas window.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {

    @FXML
    private Button button;

    @FXML
    private TextArea taskDescription;

    @FXML
    private ListView<String> taskList;

    @FXML
    private TextField taskName;

    @FXML
    private ComboBox<String> taskPriority;

    @FXML
    void addTask(ActionEvent event) {
        String name = this.taskName.getText();
        String description = this.taskDescription.getText();
        String priority = this.taskPriority.getValue();
        
        
        if (name == null || name.trim().isEmpty()) {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
        	alert.setContentText("Missing task name.");
        	alert.showAndWait();
        } else if (description == null || description.trim().isEmpty()) {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
        	alert.setContentText("Missing description.");
        	alert.showAndWait();
        } else if (priority == null || priority.trim().isEmpty()) {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
        	alert.setContentText("Missing task priority.");
        	alert.showAndWait();
        }
      
        
        String task = name + ": "+ description + ". " + priority;

        try {
	    	this.taskList.getItems().add(task);
        }
        catch (NullPointerException error) {
        	Alert alert = new Alert(Alert.AlertType.ERROR);
        	alert.setContentText("Missing Priority. Please select a task priority.");
        	alert.showAndWait();
        }
    }
    
    @FXML
    void selectPriority(ActionEvent event) {
    	String selectedTask = this.taskPriority.getSelectionModel().getSelectedItem();
    	if (selectedTask == null) {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    		alert.setContentText("Missing Priority. Select a priority level.");
            alert.show();
    	}
    }
    
    @FXML
    void selectTask(MouseEvent event) {
    	String selectedTask = this.taskList.getSelectionModel().getSelectedItem();
    	if (selectedTask != null) {
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		alert.setContentText(selectedTask);
    		alert.show();
    	}
    }

    /**
     * Perform any needed initialization of UI components and underlying objects.
     */
    public void initialize() {
    	String[] priorityLevels = {"1-High", "2-Medium", "3-Low"};
        this.taskPriority.getItems().addAll(priorityLevels);
        this.taskPriority.getSelectionModel();
    }
}
