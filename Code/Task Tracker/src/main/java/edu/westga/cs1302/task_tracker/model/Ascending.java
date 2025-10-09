package edu.westga.cs1302.task_tracker.model;

import java.util.Comparator;

/** Compares Tasks based on priority lower priority coming first
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class Ascending implements Comparator<Task> {
	
	@Override
	public int compare(Task t1, Task t2) {
		return t2.getPriority().compareTo(t1.getPriority());
	}
	
}
