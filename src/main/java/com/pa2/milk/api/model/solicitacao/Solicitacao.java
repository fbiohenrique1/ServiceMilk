package com.pa2.milk.api.model.solicitacao;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.pa2.milk.api.model.AbstractModel;
import com.pa2.milk.api.model.analise.Analise;
import com.pa2.milk.api.model.enums.EnumStatusSolicitacao;
import com.pa2.milk.api.model.fazenda.Fazenda;
import com.pa2.milk.api.model.ordemServico.OrdemServico;

@Entity
@Table
public class Solicitacao extends AbstractModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ORDEM_SERVICO")
	@SequenceGenerator(name = "SEQ_ORDEM_SERVICO", sequenceName = "id_seq_ordem_servico", allocationSize = 1)
	private Integer id;

	@OneToOne(cascade = CascadeType.ALL)
	private Fazenda fazenda;

	@OneToOne(cascade = CascadeType.ALL)
	private OrdemServico ordemServico;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Analise> analise;

	@Enumerated(EnumType.STRING)
	@Column(name = "produtos", nullable = false)
	private EnumStatusSolicitacao status;

	@Column(length = 2047)
	private String observacao;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public Fazenda getFazenda() {
		return fazenda;
	}

	public void setFazenda(Fazenda fazenda) {
		this.fazenda = fazenda;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public List<Analise> getAnalise() {
		return analise;
	}

	public void setAnalise(List<Analise> analise) {
		this.analise = analise;
	}

	public EnumStatusSolicitacao getStatus() {
		return status;
	}

	public void setStatus(EnumStatusSolicitacao status) {
		this.status = status;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
