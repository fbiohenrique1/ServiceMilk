package com.pa2.milk.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "cliente")
public class Cliente extends Usuario {

	// @NotBlank(message = "O campo telefone 1 não pode ser nulo.")
	private String telefone1;

	// @NotBlank(message = "O campo telefone 2 não pode ser nulo.")
	private String telefone2;

	@OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
	@JsonIgnore
	private List<Fazenda> listaFazenda = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "cliente")
	@JsonIgnore
	private List<Solicitacao> listaSolicitacao;
	
	public Cliente() {
		
	}

	public Cliente(String telefone1, String telefone2, List<Fazenda> listaFazenda, List<Solicitacao> listaSolicitacao) {
		super();
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
		this.listaFazenda = listaFazenda;
		this.listaSolicitacao = listaSolicitacao;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
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
