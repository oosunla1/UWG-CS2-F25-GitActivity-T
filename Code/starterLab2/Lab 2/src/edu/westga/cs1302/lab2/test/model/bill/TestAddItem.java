package edu.westga.cs1302.lab2.test.model.bill;

import edu.westga.cs1302.lab2.model.Bill;
import edu.westga.cs1302.lab2.model.BillItem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestAddItem {
	
	@Test
	public void testWhenItemIsNull() {
		Bill bill = new Bill();
		assertThrows(
				IllegalArgumentException.class,
				() -> bill.addItem(null)
		);
	}
	
	@Test
	public void testWhenItemAdded() {
	    Bill bill = new Bill();
	    BillItem item = new BillItem("Chips", 2.75);
	    bill.addItem(item);
	    var items = bill.getItems();
	    assertEquals(1, items.size(), "Bill should contain one item");
	    assertEquals(item, bill.getItems().get(0), "First item (Chips) should be the one added");
	    
	}
	
	@Test
	public void testWhenMultipleItemsAdded() {
		Bill bill = new Bill();
	    BillItem item1 = new BillItem("Chips", 2.75);
	    BillItem item2 = new BillItem("Bacon", 6.50);
	    bill.addItem(item1);
	    bill.addItem(item2);
	    var items = bill.getItems();
	    assertEquals(2, items.size(), "Bill should contain one item");
	    assertEquals(item1, bill.getItems().get(0), "First item (Chips) should be the one added");
	    assertEquals(item2, bill.getItems().get(1), "Second item (Bacon) should be the one added");
	}
}
