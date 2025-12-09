package edu.westga.cs1302.task_tracker.viewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import edu.westga.cs1302.task_tracker.model.Collection;
import edu.westga.cs1302.task_tracker.model.Comic;
import javafx.collections.ObservableList;

class TestMainWindowViewModel {
	
	private MainWindowViewModel vm;
	
	@BeforeEach
	void setUp() {
		vm = new MainWindowViewModel();
	}

	@Test
	void addCollectionWorks() {
		vm.newCollection().set("My Collection");
		vm.addCollection();
		ObservableList<Collection> collections = vm.getCollections();
		assertEquals(1, collections.size());
		assertEquals("My Collection", collections.get(0).getName());
		assertEquals("", vm.newCollection().get());
	}
	
	@Test
	void addCollectionEmptyDoesNothing() {
		vm.newCollection().set("");
		vm.addCollection();
		assertTrue(vm.getCollections().isEmpty());
	}
	
	@Test
	void removeCollectionWorks() {
		Collection col = new Collection("C1");
		vm.getCollections().add(col);
		vm.selectedCollection().set(col);
		vm.removeSelectedCollection();
		assertFalse(vm.getCollections().contains(col));
	}
	
	@Test
	void removeCollectionNoneSelectedDoesNothing() {
		Collection col = new Collection("C1");
		vm.getCollections().add(col);
		vm.removeSelectedCollection();
		assertTrue(vm.getCollections().contains(col));
	}
	
	@Test
	void addComicWorks() {
		Collection col = new Collection("C1");
		vm.getCollections().add(col);
		vm.selectedCollection().set(col);
		
		vm.newComicTitle().set("Ironman");
		vm.newComicIssue().set("1");
		
		vm.addComic();
		
		ObservableList<Comic> comics = vm.getComicsForCollection();
		assertEquals(1, comics.size());
		assertEquals("Ironman", comics.get(0).getTitle());
		assertEquals("1", comics.get(0).getIssueNumber());
		
		assertEquals("", vm.newComicTitle().get());
		assertEquals("", vm.newComicIssue().get());
	}
	
	@Test
	void addComicNoCollectionDoesNothing() {
		vm.newComicTitle().set("Ironman");
		vm.newComicIssue().set("1");
		
		vm.addComic();
		assertTrue(vm.getComicsForCollection().isEmpty());
	}
	
	@Test
	void addComicEmptyTitleOrIssueDoesNothing() {
		Collection col = new Collection("C1");
		vm.getCollections().add(col);
		vm.selectedCollection().set(col);
		
		vm.newComicTitle().set("");
		vm.newComicIssue().set("1");
		vm.addComic();
		assertTrue(vm.getComicsForCollection().isEmpty());
		
		vm.newComicTitle().set("Ironman");
		vm.newComicIssue().set("");
		vm.addComic();
		assertTrue(vm.getComicsForCollection().isEmpty());
	}
	
	@Test
	void removeComicWorks() {
		Collection col = new Collection("C1");
		Comic comic = new Comic("Ironman", "55");
		col.addComic(comic);
		
		vm.getCollections().add(col);
		vm.selectedCollection().set(col);
		vm.selectedComic().set(comic);
		
		vm.removeSelectedComic();
		
		assertFalse(vm.getComicsForCollection().contains(comic));
	}
	
	@Test
	void removeComicNoneSelectedDoesNothing() {
		Collection col = new Collection("C1");
		Comic comic = new Comic("Ironman", "1");
		col.addComic(comic);
		
		vm.getCollections().add(col);
		vm.selectedCollection().set(col);
		
		vm.removeSelectedComic();
		
		assertTrue(vm.getComicsForCollection().contains(comic));
	}
	
	@Test
	void getComicsNoSelectionReturnsEmpty() {
		assertTrue(vm.getComicsForCollection().isEmpty());
	}
}
