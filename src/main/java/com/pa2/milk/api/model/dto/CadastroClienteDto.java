package com.pa2.milk.api.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class CadastroClienteDto {

	private Integer id;

	@NotEmpty(message = "O campo email não pode ser nulo.")
	@Email(message = "O campo email é inválido.")
	private String email;

	@NotEmpty(message = "O campo nome não pode ser nulo.")
	private String nome;

	@NotEmpty(message = "O campo CPF não pode ser nulo.")
	// @CPF(message = "CPF inválido.")
	private String cpf;

	// @NotBlank(message = "O campo telefone 1 não pode ser nulo.")
	private String telefone1;

	// @NotBlank(message = "O campo telefone 2 não pode ser nulo.")
	private String telefone2;

	private String username;

	private String senha;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "CadastroClienteDto [id=" + id + ", email=" + email + ", nome=" + nome + ", cpf=" + cpf + ", telefone1="
				+ telefone1 + ", telefone2=" + telefone2 + ", username=" + username + ", senha=" + senha + "]";
	}

}
