package br.com.desafiointer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.desafiointer.model.CustomerDTO;
import br.com.desafiointer.model.InvestmentDTO;
import br.com.desafiointer.service.InvestmentService;

@RestController
@RequestMapping("/investment")
public class InvestmentController {
	
	@Autowired
	InvestmentService investmentService;
	
	@GetMapping(value = "/{cpf}")
	@Cacheable("Investment")
	public List<InvestmentDTO> findInvestmentByCPF(@PathVariable String cpf) throws Exception {
		List<InvestmentDTO> investments = investmentService.obtainInvestments(cpf);
		return investments;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public InvestmentDTO addInvestment(@RequestBody CustomerDTO cliente) throws Exception {
		return investmentService.doInvestment(cliente);
	}
	
	@GetMapping("/cancel")
	@CacheEvict("Investment")
	public void cancelCaching() {
	}

}
