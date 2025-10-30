package edu.westga.cs1302.task_tracker.model;

import java.util.ArrayList;
import java.util.List;

/** Contains basic information for a Task
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class ContainerTask extends Task {
	
	private final List<Task> subtasks;
	
	/** Creates a new ContainerTask with the given name, description, and priority.
     * 
     * @precondition name != null && !name.isEmpty() &&
     *               description != null &&
     *               priority != null
     *               
     * @param name the name of the task
     * @param description the description of the task
     * @param priority the priority of the task
     */
	public ContainerTask(String name, String description, TaskPriority priority) {
		super(name, description, priority);
		this.subtasks = new ArrayList<>();
	}

	/** Adds a subtask to this container.
     * 
     * @precondition subtask != null
     * @postcondition the subtask is added to this container's list
     * @param subTask the task to add
     * @return this ContainerTask, allowing method chaining
     */
	@Override
	public ContainerTask addTask(Task subTask) {
		if (subTask != null) {
            this.subtasks.add(subTask);
        }
        return this;	
	}
	
    /** Returns the list of subtasks for this container task
     * 
     * @precondition none
	 * @postcondition none
     * 
     * @return list of subtasks
     */
    @Override
    public List<Task> getSubTasks() {
        return this.subtasks;
    }
    
    /** Returns the name of the task to represent the task as a String
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the name of the task with "(+)" indicating it's subtasks.
	 */
    @Override
    public String toString() {
        return super.getName() + " (+)";
    }

}
