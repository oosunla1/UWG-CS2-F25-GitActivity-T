package edu.westga.cs1302.task_tracker.model.ascendingByName;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.AscendingByName;
import edu.westga.cs1302.task_tracker.model.Task;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;

class TestCompare {

	@Test
	void testO1IsNull() {
		Task o1 = new Task("name", "desc", TaskPriority.HIGH);
		Task o2 = new Task("name", "desc", TaskPriority.HIGH);
		AscendingByName ascending = new AscendingByName();
		
		assertThrows(IllegalArgumentException.class, ()->{ascending.compare(null, o2);});
	}

	@Test
	void testO2IsNull() {
		Task o1 = new Task("name", "desc", TaskPriority.HIGH);
		Task o2 = new Task("name", "desc", TaskPriority.HIGH);
		AscendingByName ascending = new AscendingByName();
		
		assertThrows(IllegalArgumentException.class, ()->{ascending.compare(o1, null);});
	}
	
	@Test
	void testO1AToO2Z() {
		Task o1 = new Task("apple", "desc", TaskPriority.HIGH);
		Task o2 = new Task("zebra", "desc", TaskPriority.HIGH);
		AscendingByName ascending = new AscendingByName();
		
		int result = ascending.compare(o1, o2);

		assertTrue(result < 0);
	}
	
	@Test
	void testO1ZToO2A() {
		Task o1 = new Task("zebra", "desc", TaskPriority.HIGH);
		Task o2 = new Task("apple", "desc", TaskPriority.LOW);
		AscendingByName ascending = new AscendingByName();
		
		int result = ascending.compare(o1, o2);
		
		assertTrue(result > 0);
	}
	

	
	@Test
	void testSameName() {
		Task o1 = new Task("name", "desc", TaskPriority.HIGH);
		Task o2 = new Task("name", "desc", TaskPriority.MEDIUM);
		AscendingByName ascending = new AscendingByName();
		
		int result = ascending.compare(o1, o2);

		assertTrue(result == 0);
	}
	
	@Test
	void testDifferentCaseWithSameName() {
		Task o1 = new Task("Name", "desc", TaskPriority.MEDIUM);
		Task o2 = new Task("name", "desc", TaskPriority.LOW);
		AscendingByName ascending = new AscendingByName();
		
		int result = ascending.compare(o1, o2);

		assertTrue(result == 0);
	}

}
