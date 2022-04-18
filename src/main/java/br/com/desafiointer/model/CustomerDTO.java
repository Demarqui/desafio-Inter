package br.com.desafiointer.model;

import java.util.Objects;

public class CustomerDTO {

	private String name;
	private String cpf;
	private Double valueToInvest;
	private Integer countCompanies;

	public CustomerDTO() {
		
	}
	
	public CustomerDTO(String name, String cpf, Double valueToInvest, Integer countCompanies) {
		this.name = name;
		this.cpf = cpf;
		this.valueToInvest = valueToInvest;
		this.countCompanies = countCompanies;
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

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Double getValueToInvest() {
		return valueToInvest;
	}

	public void setValueToInvest(Double valueToInvest) {
		this.valueToInvest = valueToInvest;
	}

	public Integer getCountCompanies() {
		return countCompanies;
	}

	public void setCountCompanies(Integer countCompanies) {
		this.countCompanies = countCompanies;
	}

	@Override
	public int hashCode() {
		return Objects.hash(countCompanies, cpf, name, valueToInvest);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerDTO other = (CustomerDTO) obj;
		return Objects.equals(countCompanies, other.countCompanies) && Objects.equals(cpf, other.cpf)
				&& Objects.equals(name, other.name) && Objects.equals(valueToInvest, other.valueToInvest);
	}
	
}
