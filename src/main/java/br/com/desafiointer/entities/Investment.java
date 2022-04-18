package br.com.desafiointer.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Investment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idInvestment;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "cpfCustomer")
	private CustomerEntity customer;

	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "investmentId")
	private List<InvestmentStock> stocks;
	private Double total;
	private Double troco;

	public Investment() {

	}

	public Investment(CustomerEntity customer, List<InvestmentStock> stocks, Double total, Double troco) {
		this.customer = customer;
		this.stocks = stocks;
		this.total = total;
		this.troco = troco;
	}

	public CustomerEntity getClient() {
		return customer;
	}

	public void setClient(CustomerEntity client) {
		this.customer = client;
	}

	public List<InvestmentStock> getStocks() {
		return stocks;
	}

	public void setStocks(List<InvestmentStock> stocks) {
		this.stocks = stocks;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTroco() {
		return troco;
	}

	public void setTroco(Double troco) {
		this.troco = troco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customer, idInvestment, stocks, total, troco);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Investment other = (Investment) obj;
		return Objects.equals(customer, other.customer) && Objects.equals(idInvestment, other.idInvestment)
				&& Objects.equals(stocks, other.stocks) && Objects.equals(total, other.total)
				&& Objects.equals(troco, other.troco);
	}
	
}
