package edu.westga.cs1302.task_tracker.model.comic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.Comic;

class TestConstructor {



    @Test
    void testNullTitle() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Comic(null, "10");
        });
    }

    @Test
    void testEmptyTitle() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Comic("", "10");
        });
    }

    @Test
    void testNullIssueNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Comic("Ironman", null);
        });
    }

    @Test
    void testEmptyIssueNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Comic("Ironman", "");
        });
    }

    @Test
    void testIssueNumberNotDigits() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Comic("Ironman", "12A");
        });
    }

    @Test
    void testIssueNumberWithSymbols() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Comic("Ironman", "#5");
        });
    }
    
	@Test
    void testValidComic() {
        Comic comic = new Comic("Ironman", "123");
        assertEquals("Ironman", comic.getTitle());
        assertEquals("123", comic.getIssueNumber());
    }
}
