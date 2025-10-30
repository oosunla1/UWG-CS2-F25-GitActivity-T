package edu.westga.cs1302.task_tracker.views;

import java.util.Comparator;

import edu.westga.cs1302.task_tracker.model.AscendingByName;
import edu.westga.cs1302.task_tracker.model.AscendingByPriority;
import edu.westga.cs1302.task_tracker.model.ContainerTask;
import edu.westga.cs1302.task_tracker.model.DescendingByName;
import edu.westga.cs1302.task_tracker.model.DescendingByPriority;
import edu.westga.cs1302.task_tracker.model.Task;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;
import edu.westga.cs1302.task_tracker.model.TaskUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/** Controller class for MainWindow of the Task Tracker system.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindow {
    @FXML private TextArea description;
    @FXML private Label highCount;
    @FXML private Label lowCount;
    @FXML private Label mediumCount;
    @FXML private TextField name;
    @FXML private ComboBox<TaskPriority> priority;
    @FXML private TextArea selectedDescription;
    @FXML private TextField selectedPriority;
    @FXML private ListView<Task> subTasks;
    @FXML private ListView<Task> tasks;
    @FXML private ComboBox<Comparator<Task>> order;

    /** Add a new task with the provided information to the listview.
     * 
     * @precondition none
     * @postcondition A task will be added to the listview with 
     * 							  1) a name matching the text of the name textfield, 
     * 							  2) a description matching the text of the description textarea,
     * 							  3) a priority matching the selected value of the priority combobox,
     * 
     * @param event we will not use this parameter, only here due to JavaFX Library requirement
     */
    @FXML 
    void addTask(ActionEvent event) {
    	try {
    		this.tasks.getItems().add(new Task(this.name.getText(), this.description.getText(), this.priority.getValue()));
    		this.sortTasks(event);
    	} catch (IllegalArgumentException error) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setContentText(error.getMessage());
    		alert.showAndWait();
    	}
    }
    
    /** Add a new sub task to the listview.
     * 
     * @param event we will not use this parameter, only here due to JavaFX Library requirement
     */
    @FXML
    void addSubTask(ActionEvent event) {
    	Task selectedTask = this.tasks.getSelectionModel().getSelectedItem();

        if (selectedTask == null) {
            return;
        }
        String subtaskName = this.name.getText();
        String subtaskDesc = this.description.getText();
        Task.TaskPriority subtaskPriority = this.priority.getValue();
        Task newSubtask = new Task(subtaskName, subtaskDesc, subtaskPriority);
        
        ContainerTask updatedTask = selectedTask.addTask(newSubtask);
        int index = this.tasks.getItems().indexOf(selectedTask);
        this.tasks.getItems().set(index, updatedTask);
        selectedTask = updatedTask;
        this.subTasks.getItems().setAll(selectedTask.getSubTasks());
        
    }

    /** Display the priority and description of the task selected in the listview.
     * 
     * @precondition none
     * @postcondition the description for the selected task will be displayed in the selectedDescription text area &&
     * 				  the priority for the selected task will be displayed in the selectedPriority text field
     * 
     * @param event we will not use this parameter, only here due to JavaFX Library requirement
     */
    @FXML
    void selectTask(MouseEvent event) {
    	Task selectedTask = this.tasks.getSelectionModel().getSelectedItem();

        if (selectedTask == null) {
            selectedTask = this.subTasks.getSelectionModel().getSelectedItem();
        }

        if (selectedTask != null) {
            this.selectedPriority.setText(selectedTask.getPriority().toString());
            this.selectedDescription.setText(selectedTask.getDescription());

            if (this.tasks.getItems().contains(selectedTask)) {
                this.subTasks.getItems().setAll(selectedTask.getSubTasks());
            }
        } else {
            this.selectedPriority.clear();
            this.selectedDescription.clear();
            this.subTasks.getItems().clear();
        }
    }
    
    /** shows a list of subtasks for the currently selected task
     * 
     * @param event we will not use this parameter, only here due to JavaFX Library requirement
     */
    @FXML
    void showSubTask(MouseEvent event) {
        Task selectedTask = this.tasks.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            this.subTasks.getItems().setAll(selectedTask.getSubTasks());
        } else {
            this.subTasks.getItems().clear();
        }
    }
    
    /** Displays a subtask k in a popup alert window whenever a subtask is selected
     * 
     * @param event we will not use this parameter, only here due to JavaFX Library requirement
     */
    @FXML
    void displaySubTask(MouseEvent event) {
        Task selectedSubTask = this.subTasks.getSelectionModel().getSelectedItem();

        if (selectedSubTask != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Subtask");
            alert.setHeaderText(selectedSubTask.getName());
            alert.setContentText("Priority: " + selectedSubTask.getPriority() + "\n"
                                + "Description: " + selectedSubTask.getDescription());
            alert.showAndWait();
        }
    }

    /** Remove the currently selected task.
     * 
     * @precondition none
     * @postcondition task selected in the listview will be removed
     * 
     * @param event we will not use this parameter, only here due to JavaFX Library requirement
     */
    @FXML
    void removeTask(ActionEvent event) {
    	Task selectedTask = this.tasks.getSelectionModel().getSelectedItem();
    	if (selectedTask != null) {
    		this.tasks.getItems().remove(selectedTask);
    	}
    }

    /** Update the description of the selected task.
     * 
     * @precondition none
     * @postcondition description for the task selected in the listview will be updated to match the text in the selectedDescription text area.
     * 
     * @param event we will not use this parameter, only here due to JavaFX Library requirement
     */
    @FXML
    void updateDescription(ActionEvent event) {
    	Task selectedTask = this.tasks.getSelectionModel().getSelectedItem();
    	if (selectedTask != null) {
    		selectedTask.setDescription(this.selectedDescription.getText());
    		this.sortTasks(event);
    	}
    }

    /** Display the count of tasks for each priority.
     * 
     * @precondition none
     * @postcondition count of tasks for each priority are displayed in the appropriate labels.
     * 
     * @param event we will not use this parameter, only here due to JavaFX Library requirement
     */
    @FXML
    void countPriorities(ActionEvent event) {
    	this.highCount.setText(Integer.toString(TaskUtility.countOfPriority(TaskPriority.HIGH, this.tasks.getItems())));
    	this.mediumCount.setText(Integer.toString(TaskUtility.countOfPriority(TaskPriority.MEDIUM, this.tasks.getItems())));
    	this.lowCount.setText(Integer.toString(TaskUtility.countOfPriority(TaskPriority.LOW, this.tasks.getItems())));
    }
    
    /** Sort tasks based on the selected ordering.
     * 
     * @precondition none
     * @postcondition tasks in the listview are sorted based on the provided ordering.
     * 
     * @param event we will not use this parameter, only here due to JavaFX Library requirement
     */
    @FXML
    void sortTasks(ActionEvent event) {
    	if (this.order.getValue() != null) {
    		this.tasks.getItems().sort(this.order.getValue());
    	}
    }

    /** Perform any needed initialization of UI components and underlying objects.
     * 
     * @precondition none
     * @postcondition none
     * 
     */
    @FXML
    public void initialize() {
    	this.priority.getItems().addAll(TaskPriority.HIGH, TaskPriority.MEDIUM, TaskPriority.LOW);
    	this.priority.setValue(this.priority.getItems().get(0));
    	this.order.getItems().add(new AscendingByPriority());
    	this.order.getItems().add(new DescendingByPriority());
    	this.order.getItems().add(new AscendingByName());
    	this.order.getItems().add(new DescendingByName());
    	this.priority.setValue(this.priority.getItems().get(0));
    }
}
