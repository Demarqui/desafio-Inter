package br.com.desafiointer.model;

import java.util.List;
import java.util.Objects;

public class InvestmentDTO {

	private List<InvestmentStockDTO> stocks;
	private Double troco = 0.0;
	private Double total = 0.0;

	public InvestmentDTO() {

	}

	private void calcTotal() {
		Double total = 0.0;
		for (InvestmentStockDTO stock : stocks) {
			total += stock.getTotal();
		}
		this.total = total;
	}

	public InvestmentDTO(List<InvestmentStockDTO> stocks, Double troco) {
		this.stocks = stocks;
		this.troco = troco;
		calcTotal();
	}

	public List<InvestmentStockDTO> getStocks() {
		return stocks;
	}

	public void setStocks(List<InvestmentStockDTO> stocks) {
		this.stocks = stocks;
		calcTotal();
	}

	public Double getTroco() {
		return troco;
	}

	public void setTroco(Double troco) {
		this.troco = troco;
	}

	public Double getTotal() {
		return total;
	}

	@Override
	public int hashCode() {
		return Objects.hash(stocks, total, troco);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvestmentDTO other = (InvestmentDTO) obj;
		return Objects.equals(stocks, other.stocks) && Objects.equals(total, other.total)
				&& Objects.equals(troco, other.troco);
	}

}
