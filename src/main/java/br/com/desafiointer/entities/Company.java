package br.com.desafiointer.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Company implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String ticker;
	private Double preco;
	private boolean active;
	
	public Company() {
		
	}

	public Company(String name, String ticker, Double price, boolean active) {
		this.nome = name;
		this.ticker = ticker;
		this.preco = price;
		this.active = active;
	}

	public String getName() {
		return nome;
	}

	public void setName(String name) {
		this.nome = name;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public Double getPrice() {
		return preco;
	}

	public void setPrice(Double price) {
		this.preco = price;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean actives) {
		this.active = actives;
	}

	@Override
	public int hashCode() {
		return Objects.hash(active, nome, preco, ticker);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		return active == other.active && Objects.equals(nome, other.nome)
				&& Objects.equals(preco, other.preco) && Objects.equals(ticker, other.ticker);
	}

}
