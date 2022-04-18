package br.com.desafiointer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafiointer.entities.InvestmentStock;

public interface InvestmentStockRepository extends JpaRepository<InvestmentStock, Long> {

}
