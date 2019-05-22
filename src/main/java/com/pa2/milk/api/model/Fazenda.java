package com.pa2.milk.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name = "fazenda")
@Table
public class Fazenda extends AbstractModel<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message = "O campo nome não pode ser nulo.")
	private String nome;

	@NotBlank(message = "O campo imagem não pode ser nulo.")
	private String imagem;

	// @CNPJ(message = "O campo CNPJ é invalido")
	@NotBlank(message = "O campo CNPJ não pode ser nulo.")
	private String cpfcnpj;

	@NotBlank(message = "O campo cep não pode ser nulo.")
	private String cep;

	@NotBlank(message = "O campo endereço não pode ser nulo.")
	private String endereco;

	@NotNull(message = "O campo numero não pode ser nulo.")
	private int numero;

	@NotBlank(message = "O campo bairro não pode ser nulo.")
	private String bairro;

	@NotBlank(message = "O campo cidade não pode ser nulo.")
	private String cidade;

	@NotBlank(message = "O campo estado não pode ser nulo.")
	private String estado;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getCpfcnpj() {
		return cpfcnpj;
	}

	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}