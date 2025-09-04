package edu.westga.cs1302.lab2.test.model.bill_item;

import edu.westga.cs1302.lab2.model.BillItem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestConstructor {

	@Test
	public void testWhenNameIsNull() {
		
		assertThrows(
				IllegalArgumentException.class,
				() -> new BillItem(null, 3.00)
		);
	}
	
	@Test
	public void testWhenAmountIsZero() {
		assertThrows(
				IllegalArgumentException.class,
				() ->  new BillItem("sugar", 0.00)
		);
	}
	
	@Test
	public void testWhenAmountIsNegatgive() {
		assertThrows(
				IllegalArgumentException.class,
				() ->  new BillItem("sugar", -0.01)
		);
	}
	
	@Test
	public void testWhenAmountIsPositive() {
		BillItem item = new BillItem("sugar", 0.01);
		assertEquals("sugar", item.getName());
		assertEquals(0.01, item.getAmount(), 0.0001);
	}

}
