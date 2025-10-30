package edu.westga.cs1302.task_tracker.model.task;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.ContainerTask;
import edu.westga.cs1302.task_tracker.model.Task;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;

class TestAddTask {

	@Test
	void testEmptyContainer() {
	    Task task = new Task("name", "desc", TaskPriority.HIGH);
	    ContainerTask container = task.addTask(null);
	    assertTrue(container.getSubTasks().isEmpty());
	}
	
	@Test
	void testOneTaskAdded() {
	    Task task = new Task("name", "desc", TaskPriority.HIGH);
	    Task subTask = new Task("name", "desc", TaskPriority.MEDIUM);
	    ContainerTask container = task.addTask(subTask);
	    assertTrue(container.getSubTasks().contains(subTask));
	}
	
	@Test
	void testMultipleTasksAdded() {
	    Task task = new Task("name", "desc", TaskPriority.HIGH);
	    Task subTask = new Task("name", "desc", TaskPriority.MEDIUM);
	    Task subTask1 = new Task("name", "desc", TaskPriority.LOW);
	    ContainerTask container = task.addTask(subTask).addTask(subTask1);
	    assertEquals(2, container.getSubTasks().size());
	    assertTrue(container.getSubTasks().contains(subTask));
	    assertTrue(container.getSubTasks().contains(subTask1));
	}
	
	@Test
	void testAddTaskCopiesTaskProperties() {
	    Task task = new Task("name", "desc", TaskPriority.MEDIUM);
	    Task subTask = new Task("name", "desc", TaskPriority.LOW);
	    ContainerTask container = task.addTask(subTask);

	    assertEquals(task.getName(), container.getName());
	    assertEquals(task.getDescription(), container.getDescription());
	    assertEquals(task.getPriority(), container.getPriority());
	}
}
