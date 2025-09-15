package edu.westga.cs1302.bill.model;

//import java.util.Arrays;
//import edu.westga.cs1302.bill.model.Bill;
//import edu.westga.cs1302.bill.model.BillItem;

/** Calculates the prices of items in a bill.
 * 
 * @author CS 1302
 * @version Fall 2025
 */
public class BillCalculator {
	
	/** Calculates the subtotal of an array of BillItem items.
	 * 
	 * @precondition item != null
	 * @param items an array of BillItem objects whose amounts will be added
	 * @return the subtotal as the sum of all item amounts
	 */
	public static double calculateSubtotal(BillItem[] items) {
        double subtotal = 0.0;
		for (BillItem item : items) {
            if (item == null) {
                throw new IllegalArgumentException("bill must not contain null values.");
            }
        }
		for (BillItem currItem : items) {
			subtotal += currItem.getAmount();
		}
		return subtotal;
	}

	/** Calculates the amount of tax on the items.
	 * 
	 * @param items an array of BillItem objects whose amounts will be added
	 * @return the tax amount for the bill
	 */
	public static double calculateTax(BillItem[] items) {
		double subtotal = calculateSubtotal(items);
		return subtotal * Bill.TAX_RATE;
	}
	
	/** Calculates the amount of tip on the bill.
	 * 
	 * @param items an array of BillItem objects whose amounts will be added
	 * @return the tip amount for the bill
	 */
	public static double calculateTip(BillItem[] items) {
		double subtotal = calculateSubtotal(items);
		return subtotal * Bill.TIP_RATE;
	}
	
	/** Calculates the total cost of the bill.
	 * 
	 * @param items an array of BillItem objects whose amounts will be added
	 * @return the total cost for the items on the bill
	 */
	public static double calculateTotal(BillItem[] items) {
		double subtotal = calculateSubtotal(items);
		return subtotal + subtotal * Bill.TAX_RATE + subtotal * Bill.TIP_RATE;
	}
}
