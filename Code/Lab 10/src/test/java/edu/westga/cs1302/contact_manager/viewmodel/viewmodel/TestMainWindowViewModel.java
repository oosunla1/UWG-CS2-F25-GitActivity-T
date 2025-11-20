package edu.westga.cs1302.contact_manager.viewmodel.viewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.contact_manager.viewmodel.MainWindowViewModel;

class TestMainWindowViewModel {
	private MainWindowViewModel vm;
    
	@Test
    void testAddContactAndFindContact() {
    	this.vm = new MainWindowViewModel();
        this.vm.getName().set("John");
        this.vm.getPhoneNumber().set("111-1111");
        this.vm.addContact();

        this.vm.getSearchCriteria().set("John");
        assertEquals("John, 111-1111", this.vm.findContact());
    }
	
	@Test
    void testAddMultipleContactsAndFindEach() {
    	this.vm = new MainWindowViewModel();
        this.vm.getName().set("John");
        this.vm.getPhoneNumber().set("111-1111");
        this.vm.addContact();

        this.vm.getName().set("Shayla");
        this.vm.getPhoneNumber().set("222-2222");
        this.vm.addContact();

        this.vm.getSearchCriteria().set("John");
        assertEquals("John, 111-1111", this.vm.findContact());

        this.vm.getSearchCriteria().set("222-2222");
        assertEquals("Shayla, 222-2222", this.vm.findContact());
    }
	
	@Test
    void testFindContactByName() {
		this.vm = new MainWindowViewModel();
        this.vm.getName().set("John");
        this.vm.getPhoneNumber().set("765-4321");
        this.vm.addContact();

        this.vm.getSearchCriteria().set("John");

        String result = this.vm.findContact();
        assertEquals("John, 765-4321", result);
    }

    @Test
    void testFindContactByPhoneNumber() {
    	this.vm = new MainWindowViewModel();
        this.vm.getName().set("Shayla");
        this.vm.getPhoneNumber().set("222-3333");
        this.vm.addContact();

        this.vm.getSearchCriteria().set("222-3333");

        String result = this.vm.findContact();
        assertEquals("Shayla, 222-3333", result);
    }

    @Test
    void testIfNeitherMatch() {
    	this.vm = new MainWindowViewModel();
        this.vm.getName().set("Shayla");
        this.vm.getPhoneNumber().set("555-1212");
        this.vm.addContact();

        this.vm.getSearchCriteria().set("John");

        String result = this.vm.findContact();
        assertEquals("No contact found.", result);
    }

    @Test
    void testInvalidSearchCriteria() {
    	this.vm = new MainWindowViewModel();
        this.vm.getSearchCriteria().set("125fBC");

        assertThrows(IllegalArgumentException.class, () -> this.vm.findContact());
    }

    @Test
    void testIfContactListIsEmpty() {
    	this.vm = new MainWindowViewModel();
        this.vm.getSearchCriteria().set("John");

        String result = this.vm.findContact();
        assertEquals("No contact found.", result);
    }

}
