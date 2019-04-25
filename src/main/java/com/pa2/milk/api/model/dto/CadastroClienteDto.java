package com.pa2.milk.api.model.dto;

import java.util.List;

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
	//@CPF(message = "CPF inválido.")
	private String cpf;

	//@NotEmpty(message = "O campo telefone não pode ser nulo.")
	private List<String> telefones;

	@NotEmpty(message = "O campo username não pode ser nulo.")
	private String username;

	@NotEmpty(message = "O campo senha não pode ser nulo.")
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

	public List<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<String> telefones) {
		this.telefones = telefones;
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
		return "CadastroClienteDto [id=" + id + ", email=" + email + ", nome=" + nome + ", cpf=" + cpf + ", telefones="
				+ telefones + ", username=" + username + ", senha=" + senha + "]";
	}

}
