package edu.westga.cs1302.task_tracker.viewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import edu.westga.cs1302.task_tracker.model.Collection;
import edu.westga.cs1302.task_tracker.model.Comic;

class TestFindComicWindowViewModel {
	
	private Collection collection;
    private FindComicWindowViewModel vm;

    @BeforeEach
    void setUp() {
        collection = new Collection("My Comics");
        vm = new FindComicWindowViewModel(collection);
    }

    @Test
    void testPropertiesInitiallyEmpty() {
        assertEquals("", vm.comicTitle().get(), "comicTitle should initially be empty");
        assertEquals("", vm.comicIssue().get(), "comicIssue should initially be empty");
        assertNull(vm.foundComic().get(), "foundComic should initially be null");
    }

    @Test
    void testPropertySetters() {
        vm.comicTitle().set("Spider-Man");
        vm.comicIssue().set("1");

        assertEquals("Spider-Man", vm.comicTitle().get());
        assertEquals("1", vm.comicIssue().get());
    }

    @Test
    void testSearchComicFound() {
        Comic comic = new Comic("Spider-Man", "1");
        collection.addComic(comic);

        vm.comicTitle().set("Spider-Man");
        vm.comicIssue().set("1");
        vm.searchComic();

        assertEquals(comic, vm.foundComic().get(), "Found comic should match the one in the collection");
    }

    @Test
    void testSearchComicNotFound() {
        Comic comic = new Comic("Batman", "5");
        collection.addComic(comic);

        vm.comicTitle().set("Spider-Man");
        vm.comicIssue().set("1");
        vm.searchComic();

        assertNull(vm.foundComic().get(), "No comic should be found for non-existent title/issue");
    }

    @Test
    void testSearchComicCaseInsensitive() {
        Comic comic = new Comic("Spider-Man", "1");
        collection.addComic(comic);

        vm.comicTitle().set("spider-man");
        vm.comicIssue().set("1");
        vm.searchComic();

        assertEquals(comic, vm.foundComic().get(), "Search should be case-insensitive");
    }
}
