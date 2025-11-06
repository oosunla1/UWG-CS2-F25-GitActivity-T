package edu.westga.cs1302.password_generator.tests.model.password_generator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.password_generator.viewmodel.MainWindowViewModel;

class TestMainWindowViewModel {


	private MainWindowViewModel viewModel;

	@Test
	void testInvalidLength() {
		this.viewModel = new MainWindowViewModel();
		this.viewModel.minimumLengthProperty().set("0");
		assertThrows(IllegalArgumentException.class, () -> this.viewModel.generatePassword(), "invalid minimum length.");
	}

	@Test
	void testNonNumericInput() {
		this.viewModel = new MainWindowViewModel();
		this.viewModel.minimumLengthProperty().set("five");
		assertThrows(IllegalArgumentException.class, () -> this.viewModel.generatePassword(), "non-numeric minimum length is invalid ");
	}
	
	@Test
	void testUpdateOutputProperty() {
		this.viewModel = new MainWindowViewModel();
		this.viewModel.minimumLengthProperty().set("10");
		this.viewModel.mustIncludeDigitsProperty().set(true);
		this.viewModel.mustIncludeLowerCaseLettersProperty().set(true);
		this.viewModel.mustIncludeUpperCaseLettersProperty().set(true);
		this.viewModel.generatePassword();

		String password = this.viewModel.outputProperty().get();
		this.viewModel = new MainWindowViewModel();
		assertNotNull(password);
		assertFalse(password.isEmpty(), "output property should not be empty after generatated");
		assertTrue(password.length() >= 10, "password should meet minimum length requirement.");
	}
	@Test
	void testDefaultPropertyValues() {
		assertEquals("1", this.viewModel.minimumLengthProperty().get(), "Default min length should be 1");
		assertFalse(this.viewModel.mustIncludeDigitsProperty().get(), "Digits default to false");
		assertFalse(this.viewModel.mustIncludeLowerCaseLettersProperty().get(), "Lowercase default to false");
		assertFalse(this.viewModel.mustIncludeUpperCaseLettersProperty().get(), "Uppercase default to false");
	}
	
	@Test
	void testMinimumLengthPropertyUpdatesValue() {
		this.viewModel = new MainWindowViewModel();
		this.viewModel.minimumLengthProperty().set("12");
		assertEquals("12", this.viewModel.minimumLengthProperty().get());
	}

}
