package br.com.icc.service;

import br.com.icc.model.Stock;
import br.com.icc.repository.StockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import org.apache.commons.validator.routines.UrlValidator;

@Service
@Transactional(readOnly = true)
public class StockService {
	
	@Autowired
	private StockRepository stockRepository;

	public List<Stock> findAll() {
		return stockRepository.findAll();
	}

	public Optional<Stock> findOne(String id) {
		return stockRepository.findById(id);
	}
	
	@Transactional(readOnly = false)
	public Optional<Stock> save(Stock entity) {
		if(entity != null && entity.getId() != null)
			entity.setId(entity.getId().toLowerCase());
		else return Optional.empty(); 
		
		UrlValidator urlValidator = new UrlValidator();
		
		return (urlValidator.isValid(entity.getId())) 
			? Optional.of(stockRepository.save(entity))
			: Optional.empty();
	}
	
	@Transactional(readOnly = false)
	public void delete(Stock entity) {
		stockRepository.delete(entity);
	}

}
