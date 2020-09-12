package br.com.icc.stock_quote_manager;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.icc.model.HostPort;
import br.com.icc.service.StockCacheService;
import br.com.icc.service.StockManagerService;

@SpringBootApplication(scanBasePackages="br.com.icc")
@EntityScan("br.com.icc.model")
@EnableJpaRepositories("br.com.icc.repository")
@RequestMapping("/")
public class StockQuoteManagerApplication {
	
	@Autowired
	public static StockCacheService cacheService;
	
	@Autowired
	public static StockManagerService stockService;
	
	public static void main(String[] args) {
		cacheService.fillCache();
        //TODO: Test the connection
        stockService.subscribe(new HostPort("localhost", new Integer(8081)));
        
		SpringApplication.run(StockQuoteManagerApplication.class, args);
	}

}
