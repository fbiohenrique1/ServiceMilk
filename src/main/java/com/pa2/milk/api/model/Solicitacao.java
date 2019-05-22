package com.pa2.milk.api.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pa2.milk.api.model.enums.EnumStatusSolicitacao;

@Entity(name = "solicitacao")
@Table
public class Solicitacao extends AbstractModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	@JsonIgnore
	private Cliente cliente;

	@OneToOne
	@JoinColumn(name = "fazenda_id", nullable = false)
	private Fazenda fazenda;

	@OneToMany(mappedBy = "solicitacao", fetch = FetchType.EAGER, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
	private List<Analise> listaAnalise;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private EnumStatusSolicitacao status;

	@Column(name = "dataCriada", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Brazil/East")
	private Date dataCriada;

	@Column(length = 2047)
	private String observacao;

	public Solicitacao() {
		listaAnalise = new ArrayList<>();
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Fazenda getFazenda() {
		return fazenda;
	}

	public void setFazenda(Fazenda fazenda) {
		this.fazenda = fazenda;
	}

	public List<Analise> getListaAnalise() {
		return listaAnalise;
	}

	public void setListaAnalise(List<Analise> listaAnalise) {
		this.listaAnalise = listaAnalise;
	}

	public EnumStatusSolicitacao getStatus() {
		return status;
	}

	public void setStatus(EnumStatusSolicitacao status) {
		this.status = status;
	}

	public Date getDataCriada() {
		return dataCriada;
	}

	public void setDataCriada(Date dataCriada) {
		this.dataCriada = dataCriada;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public void addAnalise(Analise novaAnalise) {
		listaAnalise.add(novaAnalise);
		novaAnalise.setSolicitacao(this);
	}

	public void removeAnalise(Analise removeAnalise) {
		listaAnalise.remove(removeAnalise);
		removeAnalise.setSolicitacao(null);
	}

}
