package br.com.icc.model;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import junit.framework.TestCase;

public class StockTest extends TestCase {

	@Test
	public void testPutOnMap() {
		Map<LocalDate, Double> map = new HashMap<LocalDate, Double>();
		Stock stock = new Stock("vasco", map);
		
		LocalDate ld = LocalDate.now();
		Double vl = new Double("1000000000000");
		
		stock.getQuotes().put(ld, vl);
		
		LocalDate key = LocalDate.now().minusDays(1);
		Double value = new Double("0");
		stock.getQuotes().put(key, value);
		assertEquals(2, stock.getQuotes().size());
	}

}
