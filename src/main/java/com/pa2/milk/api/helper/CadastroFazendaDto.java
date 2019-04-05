package com.pa2.milk.api.helper;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public class CadastroFazendaDto {

	private Integer id;
	@NotEmpty(message = "Nome da Fazenda não pode ser vazio.")
	@Length(min = 5, max = 30, message = "Nome da Fazenda deve conter entre 5 e 30 caracteres.")
	private String nomeFazenda;

	@NotEmpty(message = "CPF não pode ser vazio.")
	@CPF(message = "CPF inválido.")
	private String cpf;

	@NotEmpty(message = "Email não pode ser vazio.")
	@Length(min = 5, max = 200, message = "Email deve conter entre 5 e 200 caracteres.")
	@Email(message = "Email inválido.")
	private String email;

	@NotEmpty(message = "CEP não pode ser vazio.")
	private String cep;

	@NotEmpty(message = "CNPJ não pode ser vazio.")
	@CNPJ(message = "CNPJ inválido.")
	private String cnpj;

	@NotEmpty(message = "Endereço não pode ser vazio.")
	private String endereco;

	@NotNull
	private int numero;

	@NotEmpty(message = "Bairro não pode ser vazio.")
	private String bairro;

	@NotEmpty(message = "Imagem não pode ser vazio.")
	private String imagem;

	@NotEmpty(message = "Cidade não pode ser vazio.")
	private String cidade;

	@NotEmpty(message = "Estado não pode ser vazio.")
	private String estado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeFazenda() {
		return nomeFazenda;
	}

	public void setNomeFazenda(String nomeFazenda) {
		this.nomeFazenda = nomeFazenda;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "CadastroFazendaDto [id=" + id + ", nomeFazenda=" + nomeFazenda + ", cpf=" + cpf + ", email=" + email
				+ ", cep=" + cep + ", cnpj=" + cnpj + ", endereco=" + endereco + ", numero=" + numero + ", bairro="
				+ bairro + ", imagem=" + imagem + ", cidade=" + cidade + ", estado=" + estado + "]";
	}

}
