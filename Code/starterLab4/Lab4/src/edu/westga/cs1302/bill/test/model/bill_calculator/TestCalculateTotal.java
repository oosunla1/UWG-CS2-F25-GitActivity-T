package edu.westga.cs1302.bill.test.model.bill_calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillCalculator;
import edu.westga.cs1302.bill.model.BillItem;


class TestCalculateTotal {

	@Test
	public void testWhenItemIsNull() {
		assertThrows(IllegalArgumentException.class, ()->{new BillItem(null, 0.0);});
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

}
