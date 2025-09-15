package edu.westga.cs1302.bill.test.model.bill_calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillCalculator;
import edu.westga.cs1302.bill.model.BillItem;


class TestCalculateTotal {

	@Test
	public void testNullItemInArray() {
		BillItem[] items = {
	        new BillItem("Sugar", 5.0),
	        null,
	        new BillItem("Sugar", 5.0)
	    };
	    assertThrows(IllegalArgumentException.class, () -> {BillCalculator.calculateTotal(items);});
	}
	
	@Test
	public void testWhenArrayIsEmpty() {
	    BillItem[] items = {};
	    assertEquals(0.0, BillCalculator.calculateTotal(items), 0.0001);
	}
	
	@Test
	public void testMultipleItems() {
		BillItem[] items = {
			new BillItem("Sugar", 5.0),
	        new BillItem("Pizza", 10.0)
        };
        double subtotal = 15.0;
        double expectedTotal = subtotal + (subtotal * Bill.TAX_RATE) + (subtotal * Bill.TIP_RATE);
        assertEquals(expectedTotal, BillCalculator.calculateTotal(items), 0.0001);
	}
	
	@Test
	public void testTaxOnItems() {
	    BillItem[] items = {
	        new BillItem("Sugar", 5.0),
	        new BillItem("Pizza", 10.0)
	    };
	    double expectedTax = 15.0 * Bill.TAX_RATE;
	    assertEquals(expectedTax, BillCalculator.calculateTax(items), 0.0001);
	}

	@Test
	public void testTipOnItems() {
	    BillItem[] items = {
	        new BillItem("Sugar", 5.0),
	        new BillItem("Pizza", 10.0)
	    };
	    double expectedTip = 15.0 * Bill.TIP_RATE;
	    assertEquals(expectedTip, BillCalculator.calculateTip(items), 0.0001);
	}

}
