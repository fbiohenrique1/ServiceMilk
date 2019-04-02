package com.pa2.milk.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Cliente extends Usuario {

	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CLIENTE")
//	@SequenceGenerator(name = "SEQ_CLIENTE", sequenceName = "seq_cliente", allocationSize = 1)
	private Integer id;

	@NotNull(message = "O campo telefone não pode ser nulo.")
	@Fetch(FetchMode.SELECT)
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> telefones;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "cliente")
	@JsonIgnore
	private List<Fazenda> fazenda;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "cliente")
	@JsonIgnore
	private List<Solicitacao> solicitacao;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public List<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<String> telefones) {
		this.telefones = telefones;
	}

	public List<Fazenda> getFazenda() {
		return fazenda;
	}

	public void setFazenda(List<Fazenda> fazenda) {
		this.fazenda = fazenda;
	}

	public List<Solicitacao> getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(List<Solicitacao> solicitacao) {
		this.solicitacao = solicitacao;
	}

}
