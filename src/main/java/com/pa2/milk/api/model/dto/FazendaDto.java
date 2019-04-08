package com.pa2.milk.api.model.dto;

import java.util.Optional;

public class FazendaDto {

	private Optional<Integer> id = Optional.empty();
	private String nome;
	private String imagem;
	private String cnpj;
	private String cep;
	private String endereco;
	private int numero;
	private String bairro;
	private String cidade;
	private String estado;
	private Integer clienteId;

	public FazendaDto() {
	}

	public Optional<Integer> getId() {
		return id;
	}

	public void setId(Optional<Integer> id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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

	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

	@Override
	public String toString() {
		return "FazendaDto [id=" + id + ", nome=" + nome + ", imagem=" + imagem + ", cnpj=" + cnpj + ", cep=" + cep
				+ ", endereco=" + endereco + ", numero=" + numero + ", bairro=" + bairro + ", cidade=" + cidade
				+ ", estado=" + estado + ", clienteId=" + clienteId + "]";
	}

	
	
}
