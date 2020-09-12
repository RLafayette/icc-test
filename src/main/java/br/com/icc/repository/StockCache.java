package br.com.icc.repository;

import java.io.Serializable;
import java.util.HashMap;

public class StockCache implements Serializable {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private static volatile StockCache stockCache;
	
	
	private HashMap<String,String> stocksKeys;
	
	private StockCache() {
        if (stockCache != null){
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
    }
	
	public static StockCache getInstance() {
        if (stockCache == null) {
            synchronized (StockCache.class) {
                if (stockCache == null) stockCache = new StockCache();
            }
        }
        return stockCache;
    }
	
    protected StockCache readResolve() {
        return getInstance();
    }

	public HashMap<String,String> getStocksKeys() {
		if(stocksKeys == null) {
			this.stocksKeys = new HashMap<String,String>();
		}
		
		return stocksKeys;
	}

	public void setStocksKeys(HashMap<String,String> stocks) {
		this.stocksKeys = stocks;
	}
}
