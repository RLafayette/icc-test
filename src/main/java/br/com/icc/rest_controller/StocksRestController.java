package br.com.icc.rest_controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/*
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
*/

import br.com.icc.model.Stock;
import br.com.icc.service.StockCacheService;
import br.com.icc.service.StockService;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200", "http://localhost:8081" })
@RestController
public class StocksRestController {
	@Autowired
	private StockService stockService;
	
	@Autowired
	private StockCacheService stockCacheService;
	
	@GetMapping("stock-quote/{id}")
	public ResponseEntity<Stock> show(@PathVariable("id") String id) throws Exception {
		Optional<Stock> quote = stockService.findOne(id);
		
		if(quote.isPresent()) {
			return ResponseEntity.ok().body(quote.get());
		} else return ResponseEntity.notFound().build();
	}
	
	@GetMapping("stock-quotes")
	public ResponseEntity<List<Stock>> showAll() {
		return ResponseEntity.ok().body(stockService.findAll());
	}
	
	@PostMapping("stock-quote")
	public ResponseEntity<Stock> create(@Valid @RequestBody Stock entity) {
		if (stockCacheService.find(entity.getId())) { 
			
			Optional<Stock> saveResponse = stockService.save(entity);
			
			return (saveResponse.isPresent())
				? ResponseEntity.created(URI.create("/stock-quote/" + entity.getId())).build()
				: ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	

	/*
	 * Not requested
		@PutMapping("stock-quote/{id}")
		public Stock update(@Valid @RequestBody Stock entity) {
			Optional<Stock> saveResponse = stockService.save(entity);
	
			return (saveResponse.isPresent())
				? saveResponse.get()
				: null;
		}
	*/
		
	
	/*
	 * Not requested
		@DeleteMapping("stock-quote/{id}")
		public ResponseEntity<Stock> delete(@PathVariable("id") String id) {
			Optional<Stock> stock = stockService.findOne(id);
			
			if(stock.isPresent()) {
				stockService.delete(stock.get());
				return ResponseEntity.noContent().build();
			} else return ResponseEntity.notFound().build();
		}
	*/
}
