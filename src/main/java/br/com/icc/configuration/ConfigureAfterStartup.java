package br.com.icc.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import br.com.icc.model.HostPort;
import br.com.icc.repository.StockCache;
import br.com.icc.service.StockCacheService;
import br.com.icc.service.StockManagerService;

public class ConfigureAfterStartup {

	@Autowired
	public static StockCacheService cacheService;
	@Autowired
	public static StockManagerService stockService;
	
	@EventListener(ApplicationReadyEvent.class)
	public void setupAfterStartup() {
		StockCache.getInstance();
		cacheService.fillCache();
		 		
		//TODO: Test the connection
		stockService.subscribe(new HostPort("localhost", new Integer(8081)));
	}
}
