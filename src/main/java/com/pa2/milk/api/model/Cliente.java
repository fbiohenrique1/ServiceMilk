package com.pa2.milk.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "cliente")
public class Cliente extends Usuario {

	// @NotNull(message = "O campo telefone não pode ser nulo.")
	@Fetch(FetchMode.SELECT)
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> telefones;

	@OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
	@JsonIgnore
	private List<Fazenda> listaFazenda = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "cliente")
	@JsonIgnore
	private List<Solicitacao> listaSolicitacao;

	public List<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<String> telefones) {
		this.telefones = telefones;
	}

	public List<Fazenda> getListaFazenda() {
		return listaFazenda;
	}

	public void setListaFazenda(List<Fazenda> listaFazenda) {
		this.listaFazenda = listaFazenda;
	}

	public List<Solicitacao> getListaSolicitacao() {
		return listaSolicitacao;
	}

	public void setListaSolicitacao(List<Solicitacao> listaSolicitacao) {
		this.listaSolicitacao = listaSolicitacao;
	}

	public void addFazenda(Fazenda novaFazenda) {
		listaFazenda.add(novaFazenda);
		novaFazenda.setCliente(this);
	}

	public void removeFazenda(Fazenda removeFazenda) {
		listaFazenda.remove(removeFazenda);
		removeFazenda.setCliente(null);
	}

}
