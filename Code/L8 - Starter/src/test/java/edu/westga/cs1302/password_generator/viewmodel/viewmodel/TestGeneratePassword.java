package edu.westga.cs1302.password_generator.viewmodel.viewmodel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.password_generator.viewmodel.ViewModel;

class TestGeneratePassword {

	@Test
	void testMinimumLengthNotANumber() {
		ViewModel vm = new ViewModel();
		vm.getMinimumLength().setValue("apple");
		
		vm.generatePassword();
		
		assertEquals("", vm.getPassword().getValue(), "checking the password property");
		assertEquals("Invalid Minimum Length: must be a positive integer, but was apple", vm.getErrorText().getValue(), "checking the error text property");
	}
	
	@Test
	void testMinimumLengthNotAValidNumber() {
		ViewModel vm = new ViewModel();
		vm.getMinimumLength().setValue("-2");
		
		vm.generatePassword();
		
		assertEquals("", vm.getPassword().getValue(), "checking the password property");
		assertEquals("Invalid Minimum Length: minimum length must be at least 1", vm.getErrorText().getValue(), "checking the error text property");
	}
	
	@Test
	void testValidInputProvided() {
		ViewModel vm = new ViewModel();
		vm.getMinimumLength().setValue("2");
		
		vm.generatePassword();
		
		assertTrue(vm.getPassword().getValue().length() >= 2, "checking the password property has an appropriate number of characters");
		assertEquals("", vm.getErrorText().getValue(), "checking the error text property");
	}

	@Test
	void testOnePasswordAddedToList() {
	    ViewModel vm = new ViewModel();
	    vm.getMinimumLength().setValue("3");
	    vm.generatePassword();
	    
	    assertEquals(1, vm.getPasswordList().size(), "the password list should have one password");
	    assertEquals(vm.getPassword().getValue(), vm.getPasswordList().get(0), "checking that the password in the list matches the generated password");
	}
	
	@Test
	void testMultiplePasswordsAddedToList() {
	    ViewModel vm = new ViewModel();
	    vm.getMinimumLength().setValue("2");
	    vm.generatePassword();
	    String v1 = vm.getPassword().getValue();
	    vm.generatePassword();
	    String v2 = vm.getPassword().getValue();
	    
	    assertEquals(2, vm.getPasswordList().size(), "the password list should have two passwords");
	    assertEquals(v1, vm.getPasswordList().get(0));
	    assertEquals(v2, vm.getPasswordList().get(1));
	}
}
