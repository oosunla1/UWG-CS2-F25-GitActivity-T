package edu.westga.cs1302.contact_manager.viewmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.westga.cs1302.contact_manager.model.Contact;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/** View model for the MainWindow view
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class MainWindowViewModel {
	private StringProperty name;
	private StringProperty phoneNumber;
	private StringProperty searchCriteria;
	private ListProperty<Contact> contacts;
	private Map<String, Contact> contactByName;
	private Map<String, Contact> contactByPhone;
	
	/** Initialize the MainWindowViewModel
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 */
	public MainWindowViewModel() {
		this.name = new SimpleStringProperty("");
		this.phoneNumber = new SimpleStringProperty("");
		this.searchCriteria = new SimpleStringProperty("");
		this.contacts = new SimpleListProperty<Contact>(FXCollections.observableList(new ArrayList<Contact>()));
		this.contactByName = new HashMap<>();
		this.contactByPhone = new HashMap<>();
	}
	
	/** Return the name property used when adding a contact
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the name property used when adding a contact
	 */
	public StringProperty getName() {
		return this.name;
	}
	
	/** Return the phone number property used when adding a contact
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the phone number property used when adding a contact
	 */
	public StringProperty getPhoneNumber() {
		return this.phoneNumber;
	}
	
	/** Return the search criteria property used when finding a contact
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the search criteria property used when finding a contact
	 */
	public StringProperty getSearchCriteria() {
		return this.searchCriteria;
	}
	
	/** Return the list property containing all contacts added to the system
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the list property containing all contacts added to the system
	 */
	public ListProperty getContacts() {
		return this.contacts;
	}
	
	/** Adds a new contact with name and phone number set by the appropriate property
	 * 
	 * @precondition none
	 * @postcondition a new contact with name and phone number provided has been added
	 * 
	 * @throws IllegalArgumentException if either name or phone number are invalid (see Contact class)
	 */
	public void addContact() throws IllegalArgumentException {
		Contact newContact = new Contact(this.name.get(), this.phoneNumber.get());

		if (this.contactByName.containsKey(newContact.getName())) {
	        throw new IllegalArgumentException("A contact with this name already exists.");
	    }

	    if (this.contactByPhone.containsKey(newContact.getPhoneNumber())) {
	        throw new IllegalArgumentException("A contact with this number already exists.");
	    }
		
		this.contacts.add(newContact);

		this.contactByName.put(newContact.getName(), newContact);
		this.contactByPhone.put(newContact.getPhoneNumber(), newContact);
	}
	
	/** Finds a contact with name or phone number matches provide search criteria
	 * 
	 * @precondition searchCriteria must contain a valid name or phone number format
	 * @postcondition none
	 * 
	 * @return A string representation of the contact found.
	 * 
	 * @throws IllegalArgumentException if the search criteria is not a valid name or phone number format
	 */
	public String findContact() {
		if (!Contact.checkName(this.searchCriteria.get()) && !Contact.checkPhoneNumber(this.searchCriteria.get())) {
			throw new IllegalArgumentException("Search criteria is not a valid name or phone number");
		}
		Contact found = null;
		String key = this.searchCriteria.get();

		if (Contact.checkName(key)) {
		    found = this.contactByName.get(key);
		}

		if (found == null && Contact.checkPhoneNumber(key)) {
		    found = this.contactByPhone.get(key);
		}

		if (found != null) {
		    return found.toString();
		}
		return "No contact found.";
	}
	
}
