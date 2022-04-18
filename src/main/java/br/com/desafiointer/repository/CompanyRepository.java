package br.com.desafiointer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.desafiointer.entities.Company;
import br.com.desafiointer.exceptions.ResourceNotFoundException;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
	
	public List<Company> findByActive(boolean active);
	
	public void deleteCompanyByTicker(String ticker);
	
	public Company findCompanyByTicker(String ticker) throws ResourceNotFoundException;
	

}
