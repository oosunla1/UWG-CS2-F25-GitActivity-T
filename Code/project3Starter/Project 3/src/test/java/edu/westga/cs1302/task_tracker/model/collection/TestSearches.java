package edu.westga.cs1302.task_tracker.model.collection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs1302.task_tracker.model.Collection;
import edu.westga.cs1302.task_tracker.model.Comic;


class TestSearches {

	private Collection collection;

    @BeforeEach
    void setUp() {
        collection = new Collection("My Comics");
        collection.addComic(new Comic("Spider-Man", "1"));
        collection.addComic(new Comic("Batman", "5"));
        collection.addComic(new Comic("Ironman", "10"));
    }

    @Test
    void testFindNonExistingComic() {
        Comic found = collection.getComics()
                .stream()
                .filter(c -> c.getTitle().equals("Spider-Man") && c.getIssueNumber().equals("99"))
                .findFirst()
                .orElse(null);

        assertNull(found, "Should return null if comic does not exist");
    }

    @Test
    void testFindExistingComic() {
        Comic found = collection.getComics()
                .stream()
                .filter(c -> c.getTitle().equals("Batman") && c.getIssueNumber().equals("5"))
                .findFirst()
                .orElse(null);

        assertNotNull(found, "Should find the comic that exists");
        assertEquals("Batman", found.getTitle());
        assertEquals("5", found.getIssueNumber());
    }
    
    @Test
    void testFindComicWithDifCase() {
        Comic found = collection.getComics()
                .stream()
                .filter(c -> c.getTitle().equalsIgnoreCase("spider-man") && c.getIssueNumber().equals("1"))
                .findFirst()
                .orElse(null);

        assertNotNull(found, "Search should find comic even if title case differs");
        assertEquals("Spider-Man", found.getTitle());
        assertEquals("1", found.getIssueNumber());
    }

    @Test
    void testMultipleComicsWithSameTitleDifIssues() {
        collection.addComic(new Comic("Spider-Man", "2"));
        
        Comic found1 = collection.getComics()
                .stream()
                .filter(c -> c.getTitle().equals("Spider-Man") && c.getIssueNumber().equals("1"))
                .findFirst()
                .orElse(null);

        Comic found2 = collection.getComics()
                .stream()
                .filter(c -> c.getTitle().equals("Spider-Man") && c.getIssueNumber().equals("2"))
                .findFirst()
                .orElse(null);

        assertNotNull(found1, "Should find first Spider-Man comic");
        assertNotNull(found2, "Should find second Spider-Man comic with different issue");
    }
}
