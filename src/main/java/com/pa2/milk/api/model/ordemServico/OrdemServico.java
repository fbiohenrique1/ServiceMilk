package com.pa2.milk.api.model.ordemServico;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.pa2.milk.api.model.AbstractModel;
import com.pa2.milk.api.model.solicitacao.Solicitacao;
import com.pa2.milk.api.model.usuario.bolsista.Bolsista;

@Entity
@Table
public class OrdemServico extends AbstractModel<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ORDEM_SERVICO")
	@SequenceGenerator(name = "SEQ_ORDEM_SERVICO", sequenceName = "id_seq_ordem_servico", allocationSize = 1)
	private Integer id;

	@Column(nullable = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataHora;

//	@OneToOne(cascade = CascadeType.ALL)
//	private Bolsista bolsista;

	@OneToOne(cascade = CascadeType.ALL)
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

	/*
	 * public Bolsista getBolsista() { return bolsista; }
	 * 
	 * public void setBolsista(Bolsista bolsista) { this.bolsista = bolsista; }
	 */

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
