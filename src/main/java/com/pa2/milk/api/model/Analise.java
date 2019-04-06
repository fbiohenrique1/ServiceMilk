package com.pa2.milk.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pa2.milk.api.model.enums.EnumAnalisesSolicitadas;
import com.pa2.milk.api.model.enums.EnumLeite;
import com.pa2.milk.api.model.enums.EnumOrigemLeite;
import com.pa2.milk.api.model.enums.EnumProdutos;

@Entity
@Table
public class Analise extends AbstractModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// TODO: Lista de Enum
	@Enumerated(EnumType.STRING)
	@Column(name = "leite", nullable = false)
	private EnumLeite leite;

	// TODO: Lista de Enum
	@Enumerated(EnumType.STRING)
	@Column(name = "origemLeite", nullable = false)
	private EnumOrigemLeite origemLeite;

	// TODO: Lista de Enum
	@Enumerated(EnumType.STRING)
	@Column(name = "produtos", nullable = false)
	private EnumProdutos produtos;

	@NotBlank
	private String especie;

	// TODO: Lista de Enum
	@Enumerated(EnumType.STRING)
	@Column(name = "analisesSolicitadas", nullable = false)
	private EnumAnalisesSolicitadas analisesSolicitadas;

//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<Amostra> amostra;

	@ManyToOne
	@JoinColumn(name = "solicitacao_id")
	@JsonIgnore
	private Solicitacao solicitacao;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public EnumLeite getLeite() {
		return leite;
	}

	public void setLeite(EnumLeite leite) {
		this.leite = leite;
	}

	public EnumOrigemLeite getOrigemLeite() {
		return origemLeite;
	}

	public void setOrigemLeite(EnumOrigemLeite origemLeite) {
		this.origemLeite = origemLeite;
	}

	public EnumProdutos getProdutos() {
		return produtos;
	}

	public void setProdutos(EnumProdutos produtos) {
		this.produtos = produtos;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public EnumAnalisesSolicitadas getAnalisesSolicitadas() {
		return analisesSolicitadas;
	}

	public void setAnalisesSolicitadas(EnumAnalisesSolicitadas analisesSolicitadas) {
		this.analisesSolicitadas = analisesSolicitadas;
	}

//	public List<Amostra> getAmostra() {
//		return amostra;
//	}
//
//	public void setAmostra(List<Amostra> amostra) {
//		this.amostra = amostra;
//	}

	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}

}
