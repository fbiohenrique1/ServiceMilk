package com.pa2.milk.api.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pa2.milk.api.model.enums.EnumAnalisesSolicitadas;
import com.pa2.milk.api.model.enums.EnumLeite;
import com.pa2.milk.api.model.enums.EnumOrigemLeite;
import com.pa2.milk.api.model.enums.EnumProdutos;

@Entity(name = "analise")
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

	@OneToMany(mappedBy = "analise", fetch = FetchType.EAGER, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
	// @JsonIgnore
	private List<Amostra> amostras = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "solicitacao_id")
	@JsonIgnore
	private Solicitacao solicitacao;

	public Analise(Builder builder) {
		this.leite = builder.leite;
		this.origemLeite = builder.origemLeite;
		this.produtos = builder.produtos;
		this.analisesSolicitadas = builder.analisesSolicitadas;
		this.especie = builder.especie;
	}

	public Analise() {
	}

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

	public List<Amostra> getAmostras() {
		return amostras;
	}

	public void setAmostras(List<Amostra> amostra) {
		this.amostras = amostra;
	}

	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}

	public void addAmostra(Amostra novaAmostra) {
		amostras.add(novaAmostra);
		novaAmostra.setAnalise(this);
	}

	public void removerAmostra(Amostra removeAmostra) {
		amostras.remove(removeAmostra);
		removeAmostra.setAnalise(null);
	}

	public static class Builder {
		private final String especie;
		private Collection<EnumLeite> leite;
		private Collection<EnumOrigemLeite> origemLeite;
		private Collection<EnumProdutos> produtos;
		private Collection<EnumAnalisesSolicitadas> analisesSolicitadas;

		public Builder(String especie) {
			this.especie = especie;
		}

		public Builder leite(Collection<EnumLeite> leite) {
			this.leite = leite;
			return this;
		}

		public Builder origemLeite(Collection<EnumOrigemLeite> origemLeite) {
			this.origemLeite = origemLeite;
			return this;
		}

		public Builder produtos(Collection<EnumProdutos> produtos) {
			this.produtos = produtos;
			return this;
		}

		public Builder analisesSolicitadas(Collection<EnumAnalisesSolicitadas> analisesSolicitadas) {
			this.analisesSolicitadas = analisesSolicitadas;
			return this;
		}

		public Analise build() {
			return new Analise(this);
		}
	}

}
