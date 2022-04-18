package br.com.desafiointer.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.com.desafiointer.entities.Company;

public class ResultInvestmentDTO {

	private List<InvestmentDTO> investments = new ArrayList<>();
	private List<Company> availableStocks;

	private Double investedValue;

	public ResultInvestmentDTO(List<Company> availableStocks, Double investedValue, Integer countCompanies) {
		this.availableStocks = availableStocks;
		this.investedValue = investedValue;
		this.processInvestments(investedValue, new ArrayList<>(), countCompanies);
	}

	private List<InvestmentDTO> obtainNewInvestments(List<InvestmentStockDTO> stocks, Double valorInvestido,
			Integer countCompanies) {
		Integer distinctsCompanies = stocks.stream().distinct().collect(Collectors.toList()).size();
		List<Company> buyableStocks = availableStocks.stream().filter(stock -> stock.getPrice() <= valorInvestido)
				.filter(stock -> stocks.stream().filter(s -> s.getTicker().equals(stock.getTicker())).count() == 0)
				.collect(Collectors.toList());
		List<InvestmentDTO> investments = new ArrayList<InvestmentDTO>();

		for (Company buyable : buyableStocks) {
			if (distinctsCompanies >= countCompanies) {
				continue;
			}
			Integer maxCount = (int) Math.floor(valorInvestido / buyable.getPrice());

			for (int i = 1; i <= maxCount; i++) {
				List<InvestmentStockDTO> investmentStocks = new ArrayList<InvestmentStockDTO>();
				investmentStocks.addAll(stocks);
				investmentStocks.add(new InvestmentStockDTO(buyable, i));
				Double troco = valorInvestido - buyable.getPrice() * i;
				investments.add(new InvestmentDTO(investmentStocks, troco));
			}
		}

		return investments;
	}

	private void processInvestments(Double valorInvestido, List<InvestmentStockDTO> stocks, Integer countCompanies) {
		List<InvestmentDTO> newInvestments = this.obtainNewInvestments(stocks, valorInvestido, countCompanies);

		if (newInvestments.size() > 0) {
			for (InvestmentDTO investment : newInvestments) {
				this.processInvestments(investment.getTroco(), investment.getStocks(), countCompanies);
			}
		} else {
			this.investments.add(new InvestmentDTO(stocks, valorInvestido));
		}

	}

	private Long countDistinctCompanies(List<InvestmentStockDTO> stocks) {
		return stocks.stream().distinct().count();
	}

	public List<InvestmentDTO> getInvestments() {
		return investments;
	}

	public List<InvestmentDTO> getInvestments(Integer minCountCompanies) {
		return investments.stream()
				.filter(investment -> countDistinctCompanies(investment.getStocks()) >= minCountCompanies)
				.collect(Collectors.toList());
	}

	public void setInvestments(List<InvestmentDTO> investments) {
		this.investments = investments;
	}

	public List<Company> getAvailableStocks() {
		return availableStocks;
	}

	public void setAvailableStocks(List<Company> availableStocks) {
		this.availableStocks = availableStocks;
	}

	public Double getValorInvestido() {
		return investedValue;
	}

	public void setValorInvestido(Double valorInvestido) {
		this.investedValue = valorInvestido;
	}

	@Override
	public int hashCode() {
		return Objects.hash(availableStocks, investedValue, investments);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResultInvestmentDTO other = (ResultInvestmentDTO) obj;
		return Objects.equals(availableStocks, other.availableStocks)
				&& Objects.equals(investedValue, other.investedValue) && Objects.equals(investments, other.investments);
	}

	
	
}
