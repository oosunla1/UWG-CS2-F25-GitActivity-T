package edu.westga.cs1302.task_tracker.model.collection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.Collection;
import edu.westga.cs1302.task_tracker.model.Comic;


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
	
	@Test
	void testAddComicIsNull() {
	    Collection collection = new Collection("My Collection");
	    assertThrows(IllegalArgumentException.class, () -> collection.addComic(null));
	}
		
	@Test
	void testAddComic() {
	    Collection collection = new Collection("My Collection");
	    Comic comic = new Comic("Ironman", "1");
	    collection.addComic(comic);
	    assertTrue(collection.getComics().contains(comic));
	}


	@Test
	void testRemoveComic() {
	    Collection collection = new Collection("My Collection");
	    Comic comic = new Comic("Ironman", "1");
	    collection.addComic(comic);
	    collection.removeComic(comic);
	    assertFalse(collection.getComics().contains(comic));
	}

}
