package br.com.desafiointer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafiointer.entities.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String>{

}
