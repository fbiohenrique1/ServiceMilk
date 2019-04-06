package com.pa2.milk.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table
public class OrdemServico extends AbstractModel<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataHora;

	@OneToOne
	@JoinColumn(name = "bolsista_id")
	private Bolsista bolsista;

	@OneToOne
	@JoinColumn(name = "solicitacao_id")
	private Solicitacao solicitacao;

	@NotNull
	private double valor;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public Bolsista getBolsista() {
		return bolsista;
	}

	public void setBolsista(Bolsista bolsista) {
		this.bolsista = bolsista;
	}

	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
