package br.com.desafiointer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import br.com.desafiointer.entities.Company;
import br.com.desafiointer.entities.CustomerEntity;
import br.com.desafiointer.exceptions.ResourceNotFoundException;
import br.com.desafiointer.repository.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	CompanyRepository companyRepository;

	public List<Company> obtainCompaniesByStatus(String status) throws Exception {
		if (status.equals("active")) {
			return companyRepository.findByActive(true);
		} else if (status.equals("inactive")) {
			return companyRepository.findByActive(false);
		} else {
			throw new ResourceNotFoundException("Status must be active or inactive");
		}
	}

	public Company obtainStockByTicker(String ticker) {
		if(companyRepository.findCompanyByTicker(ticker) == null) {
			throw new ResourceNotFoundException("Ticker not found " + ticker);
		}
		return companyRepository.findCompanyByTicker(ticker);
	}

	public List<Company> obtainAllCompanies() {
		return companyRepository.findAll();
	}

	public Company createNewCompany(Company company) {
		return companyRepository.save(company);
	}

	public void deleteCompanyByTicker(String ticker) {
		companyRepository.deleteCompanyByTicker(ticker);
	}
}
