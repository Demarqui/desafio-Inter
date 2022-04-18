package br.com.desafiointer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.desafiointer.entities.Company;
import br.com.desafiointer.entities.CustomerEntity;
import br.com.desafiointer.entities.Investment;
import br.com.desafiointer.entities.InvestmentStock;
import br.com.desafiointer.exceptions.ResourceNotFoundException;
import br.com.desafiointer.model.CustomerDTO;
import br.com.desafiointer.model.InvestmentDTO;
import br.com.desafiointer.model.InvestmentStockDTO;
import br.com.desafiointer.model.ResultInvestmentDTO;
import br.com.desafiointer.repository.CustomerRepository;
import br.com.desafiointer.repository.InvestmentRepository;
import br.com.desafiointer.repository.InvestmentStockRepository;

@Service
public class InvestmentService {

	@Autowired
	InvestmentRepository investmentRepository;

	@Autowired
	CompanyService companyService;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	InvestmentStockRepository stockRepository;

	public List<InvestmentDTO> obtainInvestments(String cpf) {
		CustomerEntity customerEntity = customerRepository.findById(cpf)
				.orElseThrow(() -> new ResourceNotFoundException("CPF not found " + cpf));
		
		List<Investment> investments = new ArrayList<>();
		Investment investment = this.findInvestmentByCostumerCpf(customerEntity);
		investments.add(investment);

		List<InvestmentDTO> investmentsDto = new ArrayList<InvestmentDTO>();

		for (Investment inv : investments) {
			List<InvestmentStockDTO> investmentstockdto = new ArrayList<InvestmentStockDTO>();
			for (InvestmentStock s : inv.getStocks()) {
				investmentstockdto.add(new InvestmentStockDTO(s));
			}
			InvestmentDTO investmentDto = new InvestmentDTO(investmentstockdto, inv.getTroco());
			investmentsDto.add(investmentDto);
		}

		return investmentsDto;
	}

	public InvestmentDTO doInvestment(CustomerDTO customer) throws Exception {

		List<Company> activeCompanies = companyService.obtainCompaniesByStatus("active");

		if (activeCompanies.isEmpty()) {
			throw new ResourceNotFoundException("There are no companies active");

		}
		if (customer.getCountCompanies() > activeCompanies.size()) {
			if (activeCompanies.size() == 1) {
				throw new ResourceNotFoundException(
						"The number of companies requested for investment exceeds the total number "
								+ " of active companies. There is " + activeCompanies.size() + " active company");
			} else {
				throw new ResourceNotFoundException(
						"The number of companies requested for investment exceeds the total number "
								+ " of active companies. There are " + activeCompanies.size() + " active company");
			}
		}

		activeCompanies.sort((a, b) -> b.getPrice().compareTo(a.getPrice()));

		ResultInvestmentDTO result = new ResultInvestmentDTO(activeCompanies, customer.getValueToInvest(),
				customer.getCountCompanies());

		List<InvestmentDTO> investments = result.getInvestments(customer.getCountCompanies());

		investments.sort((a, b) -> a.getTroco().compareTo(b.getTroco()));

		InvestmentDTO investmentdto = investments.get(0);

		CustomerEntity customerEntity = customerRepository.findById(customer.getCpf()).orElse(null);

		if (customerEntity == null) {
			customerEntity = customerRepository.save(new CustomerEntity(customer));
		}

		List<InvestmentStock> stocks = new ArrayList<>();
		for (InvestmentStockDTO stocksDTO : investmentdto.getStocks()) {
			InvestmentStock stock = new InvestmentStock(stocksDTO.getNome(), stocksDTO.getTicker(),
					stocksDTO.getPrice(), stocksDTO.getQuantity(), stocksDTO.getTotal());
			stock = stockRepository.save(stock);
			stocks.add(stock);
		}

		Investment investment = new Investment(customerEntity, stocks, investmentdto.getTotal(),
				investmentdto.getTroco());
		investmentRepository.save(investment);

		return investmentdto;
	}

	public Investment findInvestmentByCostumerCpf(CustomerEntity customer) {
		return investmentRepository.findInvestmentByCustomerCpf(customer.getCpf());
	}

}
