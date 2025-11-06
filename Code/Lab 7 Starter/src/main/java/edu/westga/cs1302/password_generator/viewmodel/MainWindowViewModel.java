package edu.westga.cs1302.password_generator.viewmodel;

import java.util.Random;

import edu.westga.cs1302.password_generator.model.PasswordGenerator;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/** View Model for the MainWindow of the Application.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindowViewModel {
	private BooleanProperty mustIncludeDigits;
    private BooleanProperty mustIncludeLowerCaseLetters;
    private BooleanProperty mustIncludeUpperCaseLetters;
    private StringProperty minimumLength;
    private StringProperty output;
	private PasswordGenerator generator;
    
    /** Instantiates a new main window view model.
     * 
     */
    public MainWindowViewModel() {
    	this.mustIncludeDigits = new SimpleBooleanProperty(false);
		this.mustIncludeLowerCaseLetters = new SimpleBooleanProperty(false);
		this.mustIncludeUpperCaseLetters = new SimpleBooleanProperty(false);
		this.minimumLength = new SimpleStringProperty("1");
		this.output = new SimpleStringProperty("Password3");
		
		Random randomRange = new Random();
        this.generator = new PasswordGenerator(randomRange.nextLong());
    }
    
	/**
     * Gets the mustIncludeDigits property.
	 * 
	 * @return the mustIncludeDigits
	 */
	public BooleanProperty mustIncludeDigitsProperty() {
		return this.mustIncludeDigits;
	}
	
	/**
     * Gets the mustIncludeLowerCaseLetters property.
	 * 
	 * @return the mustIncludeLowerCaseLetters
	 */
	public BooleanProperty mustIncludeLowerCaseLettersProperty() {
		return this.mustIncludeLowerCaseLetters;
	}
	
	/**
     * Gets the mustIncludeUpperCaseLetters property.
	 * 
	 * @return the mustIncludeUpperCaseLetters
	 */
	public BooleanProperty mustIncludeUpperCaseLettersProperty() {
		return this.mustIncludeUpperCaseLetters;
	}
	
	/**
	 * Gets the minimumLength property.
	 * 
	 * @return the minimumLength
	 */
	public StringProperty minimumLengthProperty() {
		return this.minimumLength;
	}

	/**
	 * Gets the output property.
	 * 
	 * @return the output
	 */
	public StringProperty outputProperty() {
		return this.output;
	}

	/**
	 * Generates a password based on the user's settings.
	 * 
	 * @precondition minimumLengthProperty must represent a positive integer.
	 * @postcondition outputProperty is updated with the generated password.
	 * 
	 * @throws IllegalArgumentException if minimumLengthProperty is not a valid positive integer.
	 */
	public void generatePassword() {
		int minimumLength;
		try {
			minimumLength = Integer.parseInt(this.minimumLength.getValue());
		} catch (NumberFormatException error) {
			throw new IllegalArgumentException("input must be a positive integer");
		}

		if (minimumLength < 1) {
			throw new IllegalArgumentException("minimum length must be at least 1");
		}

		this.generator.setMinimumLength(minimumLength);
		this.generator.setMustHaveAtLeastOneDigit(this.mustIncludeDigits.get());
		this.generator.setMustHaveAtLeastOneLowerCaseLetter(this.mustIncludeLowerCaseLetters.get());
		this.generator.setMustHaveAtLeastOneUpperCaseLetter(this.mustIncludeUpperCaseLetters.get());

		String password = this.generator.generatePassword();
		this.output.set(password);
	}
	
}
