package edu.westga.cs1302.task_tracker.model;

import java.util.Comparator;

/** Compares Tasks based on priority higher priority coming first
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class Descending implements Comparator<Task> {

	@Override
	public int compare(Task t1, Task t2) {
		return t1.getPriority().compareTo(t2.getPriority());
	}

}
