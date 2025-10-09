package edu.westga.cs1302.task_tracker.model.ascending;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.Ascending;
import edu.westga.cs1302.task_tracker.model.Task;
import edu.westga.cs1302.task_tracker.model.Task.TaskPriority;

class TestCompare {

    private Ascending ascending = new Ascending();

	@Test
    void testCompareLowAndLow() {
        Task t1 = new Task("Shopping", "Buy halloween costume", TaskPriority.LOW);
        Task t2 = new Task("Walk", "Walk 2 miles", TaskPriority.LOW);
        assertEquals(0, ascending.compare(t1, t2), "LOW vs LOW should return 0");
    }

    @Test
    void testCompareMediumAndMedium() {
        Task t1 = new Task("Wash car", "Get car detailed", TaskPriority.MEDIUM);
        Task t2 = new Task("Read", "Read first half of assigned book", TaskPriority.MEDIUM);
		assertEquals(0, ascending.compare(t1, t2), "MEDIUM vs MEDIUM should return 0");
    }

    @Test
    void testCompareHighAndHigh() {
        Task t1 = new Task("Finish Lab 6", "Commit Part A. Start Part B and C", TaskPriority.HIGH);
        Task t2 = new Task("Meet with advisor", "Plan Spring classes", TaskPriority.HIGH);
        assertEquals(0, ascending.compare(t1, t2), "HIGH vs HIGH should return 0");
    }
    
    @Test
    void testCompareLowAndMedium() {
        Task t1 = new Task("Shopping", "Buy halloween costume", TaskPriority.LOW);
        Task t2 = new Task("Read", "Read first half of assigned book", TaskPriority.MEDIUM);
        assertTrue(ascending.compare(t1, t2) < 0, "LOW should come before MEDIUM");
    }

    @Test
    void testCompareLowAndHigh() {
        Task t1 = new Task("Shopping", "Buy halloween costume", TaskPriority.LOW);
        Task t2 = new Task("Finish Lab 6", "Commit Part A. Start Part B and C", TaskPriority.HIGH);
        assertTrue(ascending.compare(t1, t2) < 0, "LOW should come before HIGH");
    }
    
    @Test
    void testCompareMediumAndLow() {
        Task t1 = new Task("Wash car", "Get car detailed", TaskPriority.MEDIUM);
        Task t2 = new Task("Shopping", "Buy halloween costume", TaskPriority.LOW);
        assertTrue(ascending.compare(t1, t2) > 0, "MEDIUM should come after LOW");
    }

    @Test
    void testCompareMediumAndHigh() {
        Task t1 = new Task("Read", "Read first half of assigned book", TaskPriority.MEDIUM);
        Task t2 = new Task("Meet with advisor", "Plan Spring classes", TaskPriority.HIGH);
        assertTrue(ascending.compare(t1, t2) < 0, "MEDIUM should come before HIGH");
    }

    @Test
    void testCompareHighAndLow() {
        Task t1 = new Task("Finish Lab 6", "Commit Part A. Start Part B and C", TaskPriority.HIGH);
        Task t2 = new Task("Shopping", "Buy halloween costume", TaskPriority.LOW);
        assertTrue(ascending.compare(t1, t2) > 0, "HIGH should come after LOW");
    }
    
    @Test
    void testCompareHighAndMedium() {
        Task t1 = new Task("Finish Lab 6", "Commit Part A. Start Part B and C", TaskPriority.HIGH);
        Task t2 = new Task("Read", "Read first half of assigned book", TaskPriority.MEDIUM);
        assertTrue(ascending.compare(t1, t2) > 0, "HIGH should come after MEDIUM");
    }

}
