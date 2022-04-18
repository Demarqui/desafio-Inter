package br.com.desafiointer.entities;



import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import br.com.desafiointer.model.CustomerDTO;

@Entity
public class CustomerEntity {

	private String name;
	@Id
	private String cpf;
	
	@OneToMany
	@JoinColumn(name = "cpfCliente")
	private List<Investment> investments;
	
	public CustomerEntity() {
		
	}

	public CustomerEntity(CustomerDTO clientdto) {
		this.name = clientdto.getName();
		this.cpf = clientdto.getCpf();
	}
	
	public CustomerEntity(String name, String cpf) {
		this.name = name;
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, investments, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerEntity other = (CustomerEntity) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(investments, other.investments)
				&& Objects.equals(name, other.name);
	}
	
}
