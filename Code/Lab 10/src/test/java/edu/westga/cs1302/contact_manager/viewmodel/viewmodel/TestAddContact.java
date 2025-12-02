package edu.westga.cs1302.contact_manager.viewmodel.viewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.contact_manager.viewmodel.MainWindowViewModel;

class TestAddContact {

	private MainWindowViewModel vm;
	
	@Test
    void testAddMultipleContacts() {
    	this.vm = new MainWindowViewModel();
        vm.getName().set("Charlie");
        vm.getPhoneNumber().set("444-4444");
        vm.addContact();

        vm.getName().set("David");
        vm.getPhoneNumber().set("555-5555");
        vm.addContact();

        assertEquals(2, vm.getContacts().size());
    }
	
	@Test
    void testAddDuplicateNameThrowsException() {
		this.vm = new MainWindowViewModel();
        vm.getName().set("Alice");
        vm.getPhoneNumber().set("111-1111");
        vm.addContact();

        vm.getName().set("Alice");
        vm.getPhoneNumber().set("222-2255"); 

        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> vm.addContact());
        assertEquals("A contact with this name already exists.", error.getMessage());
    }

    @Test
    void testAddDuplicatePhoneThrowsException() {
    	this.vm = new MainWindowViewModel();
        vm.getName().set("Bob");
        vm.getPhoneNumber().set("333-0033");
        vm.addContact();

        vm.getName().set("Robert");
        vm.getPhoneNumber().set("333-0033"); 

        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> vm.addContact());
        assertEquals("A contact with this number already exists.", error.getMessage());
    }
    
}
