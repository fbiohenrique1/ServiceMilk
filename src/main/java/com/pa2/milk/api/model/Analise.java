package com.pa2.milk.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.pa2.milk.api.model.enums.EnumAnalisesSolicitadas;
import com.pa2.milk.api.model.enums.EnumLeite;
import com.pa2.milk.api.model.enums.EnumOrigemLeite;
import com.pa2.milk.api.model.enums.EnumProdutos;

@Entity
@Table
public class Analise extends AbstractModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ANALISE")
	@SequenceGenerator(name = "SEQ_ANALISE", sequenceName = "id_seq_analise", allocationSize = 1)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(name = "leite", nullable = false)
	private EnumLeite leite;

	@Enumerated(EnumType.STRING)
	@Column(name = "origemLeite", nullable = false)
	private EnumOrigemLeite origemLeite;

	@Enumerated(EnumType.STRING)
	@Column(name = "produtos", nullable = false)
	private EnumProdutos produtos;

	@NotBlank
	private String especie;

	@Enumerated(EnumType.STRING)
	@Column(name = "analisesSolicitadas", nullable = false)
	private EnumAnalisesSolicitadas analisesSolicitadas;

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
}
