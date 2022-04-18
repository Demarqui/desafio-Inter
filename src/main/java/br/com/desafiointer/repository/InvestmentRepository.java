package br.com.desafiointer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafiointer.entities.CustomerEntity;
import br.com.desafiointer.entities.Investment;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {

	public Investment findInvestmentByCustomerCpf(String cpf);
}
