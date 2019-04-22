package com.pa2.milk.api.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

	@ElementCollection(targetClass = EnumLeite.class, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "enumLeite", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "enumLeite", nullable = false)
	@Enumerated(EnumType.STRING)
	private Collection<EnumLeite> leite;

	@ElementCollection(targetClass = EnumOrigemLeite.class, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "enumOrigemLeite", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "enumOrigemLeite", nullable = false)
	@Enumerated(EnumType.STRING)
	private Collection<EnumOrigemLeite> origemLeite;

	@ElementCollection(targetClass = EnumProdutos.class, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "enumProdutos", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "enumProdutos", nullable = false)
	@Enumerated(EnumType.STRING)
	private Collection<EnumProdutos> produtos;

	@ElementCollection(targetClass = EnumAnalisesSolicitadas.class, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinTable(name = "enumAnalisesSolicitadas", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "enumAnalisesSolicitadas", nullable = false)
	@Enumerated(EnumType.STRING)
	private Collection<EnumAnalisesSolicitadas> analisesSolicitadas;

	@NotBlank(message = "O campo especie não pode ser nulo.")
	private String especie;

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

	public Collection<EnumLeite> getLeite() {
		return leite;
	}

	public void setLeite(Collection<EnumLeite> leite) {
		this.leite = leite;
	}

	public Collection<EnumOrigemLeite> getOrigemLeite() {
		return origemLeite;
	}

	public void setOrigemLeite(Collection<EnumOrigemLeite> origemLeite) {
		this.origemLeite = origemLeite;
	}

	public Collection<EnumProdutos> getProdutos() {
		return produtos;
	}

	public void setProdutos(Collection<EnumProdutos> produtos) {
		this.produtos = produtos;
	}

	public Collection<EnumAnalisesSolicitadas> getAnalisesSolicitadas() {
		return analisesSolicitadas;
	}

	public void setAnalisesSolicitadas(Collection<EnumAnalisesSolicitadas> analisesSolicitadas) {
		this.analisesSolicitadas = analisesSolicitadas;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
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
	
//	public void addAmostra(Amostra novaAmostra) {
//		amostra.add(novaAmostra);
//		novaAmostra.setAnalise(this);
//	}
//
//	public void removerAmostra(Amostra removeAmostra) {
//		amostra.remove(removeAmostra);
//		removeAmostra.setAnalise(null);
//	}

}
