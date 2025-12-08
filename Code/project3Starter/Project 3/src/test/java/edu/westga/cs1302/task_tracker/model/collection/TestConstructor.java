package edu.westga.cs1302.task_tracker.model.collection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.Collection;


class TestConstructor {

	@Test
	void testNullName() {
		assertThrows(IllegalArgumentException.class, ()->{new Collection(null);});
	}

	@Test
	void testEmptyName() {
		assertThrows(IllegalArgumentException.class, ()->{new Collection("");});
	}

	@Test
	void testValidArguments() {
		Collection result = new Collection("name");
		
		assertEquals("name", result.getName(), "checking name");
	}

}
