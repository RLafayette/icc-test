package br.com.icc.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "stocks")
public class Stock implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull(message = "Stock Id may not be null")
	@NotEmpty(message = "Stock Id may not be empty")
	@NotBlank(message = "Stock Id may not be blank")
	private String id;
	
	@ElementCollection
	private Map<LocalDate, Double> quotes;
	
	public Stock(
			@NotNull(message = "Stock Id may not be null") 
			@NotEmpty(message = "Stock Id may not be empty") 
			@NotBlank(message = "Stock Id may not be blank") String id,
			Map<LocalDate, Double> quotes) {
		super();
		this.id = id;
		this.quotes = quotes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Map<LocalDate, Double> getQuotes() {
		return quotes;
	}

	public void setQuotes(Map<LocalDate, Double> quotes) {
		this.quotes = quotes;
	}
	
}
