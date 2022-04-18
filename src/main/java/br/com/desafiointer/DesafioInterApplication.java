package br.com.desafiointer;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import br.com.desafiointer.entities.Company;
import br.com.desafiointer.repository.CompanyRepository;

@EnableWebMvc
@SpringBootApplication
@EnableCaching
public class DesafioInterApplication implements CommandLineRunner {

	@Autowired
	private CompanyRepository companiesRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(DesafioInterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Company c2 = new Company("Magazine Luiza", "MGLU3", 18.80,true);
		Company c3 = new Company("Sulamérica", "SULA11", 28.26, true);
		Company c4 = new Company("Engie", "EGIE3", 38.30, true);
		Company c5 = new Company("CVC", "CVCB3", 20.87, true);
		Company c6 = new Company("Renner", "LREN3", 36.95, true);
		Company c7 = new Company("Marisa", "AMAR3", 6.30, true);
		
		companiesRepository.save(c2);
		companiesRepository.save(c3);
		companiesRepository.save(c4);
		companiesRepository.save(c5);
		companiesRepository.save(c6);
		companiesRepository.save(c7);
		
	}
	
}
