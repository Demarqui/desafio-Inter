package br.com.desafiointer.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafiointer.entities.Company;
import br.com.desafiointer.exceptions.ResourceNotFoundException;
import br.com.desafiointer.repository.CompanyRepository;
import br.com.desafiointer.service.CompanyService;

@Transactional
@RestController
@RequestMapping("/companies")
public class CompanyController {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private CompanyService companyService;

	@GetMapping
	public List<Company> obtainAllCompanies() {
		List<Company> result = companyService.obtainAllCompanies();
		return result;
	}

	@PostMapping
	public Company addCompany(@RequestBody Company company) {
		return companyService.createNewCompany(company);
	}

	@PutMapping
	public Company updatePriceCompany(@RequestBody Company company) {
		Company currentCompany = companyService.obtainStockByTicker(company.getTicker());
		if (!currentCompany.isActive() && !company.isActive()) {
			throw new ResourceNotFoundException("The requested company is inactive and cannot receive investment");
		} else {
			BeanUtils.copyProperties(company, currentCompany);
			return companyRepository.save(currentCompany);
		}
	}

	@RequestMapping("/{active}")
	@Cacheable("Companies")
	public ResponseEntity<List<Company>> findByActive(@PathVariable(value = "active") String active) throws Exception {
		List<Company> empresas = companyService.obtainCompaniesByStatus(active);
		return ResponseEntity.ok().body(empresas);
	}

	@DeleteMapping("/{ticker}")
	@ResponseStatus(HttpStatus.OK)
	public void removeCompanyByTicker(@PathVariable(value = "ticker") String ticker) {
		Company companyToDelete = companyService.obtainStockByTicker(ticker);
		companyService.deleteCompanyByTicker(companyToDelete.getTicker());
	}
	
	@GetMapping("/cancel")
	@CacheEvict("Companies")
	public void cancelCaching() {
	}
}
