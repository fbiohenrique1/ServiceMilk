package com.pa2.milk.api.model.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public class CadastroClienteDto {

	private Integer id;

	@NotEmpty(message = "Email não pode ser vazio.")
	@Length(min = 5, max = 200, message = "Email deve conter entre 5 e 200 caracteres.")
	@Email(message = "Email inválido.")
	private String email;

	@NotEmpty(message = "Nome não pode ser vazio.")
	@Length(min = 5, max = 30, message = "Nome deve conter entre 5 e 30 caracteres.")
	private String nome;

	@NotEmpty(message = "CPF não pode ser vazio.")
	@CPF(message = "CPF inválido.")
	private String cpf;

	@NotEmpty(message = "Telefones não pode ser vazio.")
	private List<String> telefones;

	@NotEmpty(message = "Username não pode ser vazio.")
	private String username;

	@NotEmpty(message = "Senha não pode ser vazio.")
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
