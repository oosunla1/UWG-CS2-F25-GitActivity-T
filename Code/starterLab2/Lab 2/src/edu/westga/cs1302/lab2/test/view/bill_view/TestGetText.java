package edu.westga.cs1302.lab2.test.view.bill_view;

import edu.westga.cs1302.lab2.model.Bill;
import edu.westga.cs1302.lab2.model.BillItem;
import edu.westga.cs1302.lab2.view.BillView;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestGetText {

	@Test
	public void testWhenNoItemsInBill() {
		Bill bill = new Bill();
        BillView view = new BillView();

        String actual = "ITEMS" + System.lineSeparator() + System.lineSeparator()
                        + "SUBTOTAL - $0.0" + System.lineSeparator()
                        + "TAX - $0.0" + System.lineSeparator()
                        + "TIP - $0.0" + System.lineSeparator()
                        + "TOTAL - $0.0";

        assertEquals(actual, view.getText(bill), "Bill should be $0.0");
	}
	
	@Test
	public void testWhenOneItemInBill() {
		Bill bill = new Bill();
        bill.addItem(new BillItem("Sandwich", 12.50));

        BillView view = new BillView();

        double subTotal = 12.50;
        double tax = subTotal * Bill.TAX_RATE;
        double tip = subTotal * Bill.TIP_RATE;
        double total = subTotal + tax + tip;

        String actual = "ITEMS" + System.lineSeparator() +
                          "Sandwich - 12.5" + System.lineSeparator() +
                          System.lineSeparator() +
                          "SUBTOTAL - $12.5" + System.lineSeparator() +
                          "TAX - $" + tax + System.lineSeparator() +
                          "TIP - $" + tip + System.lineSeparator() +
                          "TOTAL - $" + total;

        assertEquals(actual, view.getText(bill), "Checks the one item on the bill");
	}
	
	@Test
	public void testWhenMultipleItemsInBill() {
		Bill bill = new Bill();
        bill.addItem(new BillItem("Sandwich", 12.50));
        bill.addItem(new BillItem("Soda", 2.50));

        BillView view = new BillView();

        double subTotal = 12.50 + 2.50;
        double tax = subTotal * Bill.TAX_RATE;
        double tip = subTotal * Bill.TIP_RATE;
        double total = subTotal + tax + tip;

        String actual = "ITEMS" + System.lineSeparator() +
                          "Sandwich - 12.5" + System.lineSeparator() +
                          "Soda - 2.5" + System.lineSeparator() +
                          System.lineSeparator() +
                          "SUBTOTAL - $15.0" + System.lineSeparator() +
                          "TAX - $" + tax + System.lineSeparator() +
                          "TIP - $" + tip + System.lineSeparator() +
                          "TOTAL - $" + total;

        assertEquals(actual, view.getText(bill), "Checks and diplays the items on the bill");
	}

}
