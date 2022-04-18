package br.com.desafiointer.model;

import java.util.Objects;

import br.com.desafiointer.entities.Company;
import br.com.desafiointer.entities.InvestmentStock;

public class InvestmentStockDTO {

	private String name;
	private String ticker;
	private Double price = 0.0;
	private Integer quantity = 0;
	private Double total = 0.0;

	public InvestmentStockDTO() {

	}

	public InvestmentStockDTO(String name, String ticker, Double price, Integer quantity) {
		this.name = name;
		this.ticker = ticker;
		this.price = price;
		this.quantity = quantity;
		this.total = price * quantity;
	}

	public InvestmentStockDTO(Company company, Integer quantity) {
		this.name = company.getName();
		this.ticker = company.getTicker();
		this.price = company.getPrice();
		this.quantity = quantity;
		this.total = company.getPrice() * quantity;
	}
	
	public InvestmentStockDTO(InvestmentStock investmentStockEntity) {
		this.name = investmentStockEntity.getName();
		this.price = investmentStockEntity.getPrice();
		this.quantity = investmentStockEntity.getQuantity();
		this.ticker = investmentStockEntity.getTicker();
		this.total = price * quantity;
	}

	public String getNome() {
		return name;
	}

	public void setNome(String nome) {
		this.name = nome;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.total = price * quantity;
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.total = price * quantity;
		this.quantity = quantity;
	}

	public Double getTotal() {
		return total;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, price, quantity, ticker, total);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvestmentStockDTO other = (InvestmentStockDTO) obj;
		return Objects.equals(name, other.name) && Objects.equals(price, other.price)
				&& Objects.equals(quantity, other.quantity) && Objects.equals(ticker, other.ticker)
				&& Objects.equals(total, other.total);
	}
}
