package br.com.icc.rest_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.icc.service.StockCacheService;


@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
public class StockCacheRestController {
	
	@Autowired
	private StockCacheService stockCacheService;
	
	
	@DeleteMapping("stockcache")
	public ResponseEntity<Object> delete() {
		stockCacheService.deleteCache();
		stockCacheService.fillCache();
        return ResponseEntity.noContent().build();
		
	}
	
	

	
}