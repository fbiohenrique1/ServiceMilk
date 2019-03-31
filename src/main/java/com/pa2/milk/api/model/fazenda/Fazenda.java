package com.pa2.milk.api.model.fazenda;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CNPJ;
@Entity
public class Fazenda {
	//Erro no package, diz que esse package nao existe.
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE)
	private Integer id;
	
	@NotBlank
	private String empresa;
	
	@NotBlank
	private String localizacao;
	
	@CNPJ
	@NotBlank
	private String cnpj;
	
	@NotBlank
	private String cep;
	
	@NotBlank
	private String endereco;
	
	@NotBlank
	private String imagem;

	public Fazenda(@NotBlank String empresa, @NotBlank String imagem, @NotBlank String cep, @CNPJ @NotBlank String cnpj,
			@NotBlank String localizacao, @NotBlank String endereco) {
		super();
		this.empresa = empresa;
		this.imagem = imagem;
		this.cep = cep;
		this.cnpj = cnpj;
		this.localizacao = localizacao;
		this.endereco = endereco;
	}

	public Fazenda() {
		super();
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
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

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	
	

}
