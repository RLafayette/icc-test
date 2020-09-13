package br.com.icc.stock_quote_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication(scanBasePackages="br.com.icc")
@EntityScan("br.com.icc.model")
@EnableJpaRepositories("br.com.icc.repository")
@RequestMapping("/")
public class StockQuoteManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(StockQuoteManagerApplication.class, args);
	}

}
