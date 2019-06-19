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
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pa2.milk.api.model.enums.EnumAnalisesSolicitadas;
import com.pa2.milk.api.model.enums.EnumEspecie;
import com.pa2.milk.api.model.enums.EnumLeite;
import com.pa2.milk.api.model.enums.EnumOrigemLeite;
import com.pa2.milk.api.model.enums.EnumProdutos;

@Entity(name = "analise")
@Table
public class Analise extends AbstractModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	//@Column(name = "enumLeite", nullable = false)
	//@Enumerated(EnumType.STRING)
	//@NotNull(message = "O campo leite não pode ser vazio.")
	//private EnumLeite leite;

	@Column(name = "enumOrigemLeite", nullable = false)
	@Enumerated(EnumType.STRING)
	@NotNull(message = "O campo origem do leite não pode ser vazio.")
	private EnumOrigemLeite origemLeite;

	@ElementCollection(targetClass = EnumProdutos.class)
	@JoinTable(name = "enumProdutos", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "enumProdutos", nullable = false)
	@Enumerated(EnumType.STRING)
	private Collection<EnumProdutos> produtos;

	@ElementCollection(targetClass = EnumAnalisesSolicitadas.class)
	@JoinTable(name = "enumAnalisesSolicitadas", joinColumns = @JoinColumn(name = "id"))
	@Column(name = "enumAnalisesSolicitadas", nullable = false)
	@Enumerated(EnumType.STRING)
	private Collection<EnumAnalisesSolicitadas> analisesSolicitadas;

	@Column(name = "enumEspecie", nullable = false)
	@Enumerated(EnumType.STRING)
	@NotNull(message = "O campo especie não pode ser vazio.")
	private EnumEspecie especie;

	@Column(name = "quantidade_amostras")
	@NotNull(message = "O campo quantidade de amostras não pode ser vazio.")
	private Integer quantidadeAmostras;

	@OneToMany(mappedBy = "analise", fetch = FetchType.EAGER, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
	// @JsonIgnore
	private List<Amostra> amostras = new ArrayList<>();

	@Column(name = "descricao"/* , nullable = false */)
	//@NotBlank(message = "O campo descrição não pode ser nulo.")
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "solicitacao_id")
	@JsonIgnore
	private Solicitacao solicitacao;

	public Analise(Builder builder) {
		//this.leite = builder.leite;
		this.origemLeite = builder.origemLeite;
		this.produtos = builder.produtos;
		this.analisesSolicitadas = builder.analisesSolicitadas;
		this.especie = builder.especie;
		this.quantidadeAmostras = builder.quantidadeAmostras;
		this.descricao = builder.descricao;
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

	/*public EnumLeite getLeite() {
		return leite;
	}

	public void setLeite(EnumLeite leite) {
		this.leite = leite;
	}*/

	public EnumOrigemLeite getOrigemLeite() {
		return origemLeite;
	}

	public void setOrigemLeite(EnumOrigemLeite origemLeite) {
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

	public EnumEspecie getEspecie() {
		return especie;
	}

	public void setEspecie(EnumEspecie especie) {
		this.especie = especie;
	}

	public List<Amostra> getAmostras() {
		return amostras;
	}

	public void setAmostras(List<Amostra> amostra) {
		this.amostras = amostra;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public Integer getQuantidadeAmostras() {
		return quantidadeAmostras;
	}

	public void setQuantidadeAmostras(Integer quantidadeAmostras) {
		this.quantidadeAmostras = quantidadeAmostras;
	}

	public static class Builder {
		private EnumEspecie especie;
		private EnumLeite leite;
		private EnumOrigemLeite origemLeite;
		private Collection<EnumProdutos> produtos;
		private Collection<EnumAnalisesSolicitadas> analisesSolicitadas;
		private Integer quantidadeAmostras;
		private String descricao;

		public Builder(String descricao) {
			this.descricao = descricao;
		}

		public Builder especie(EnumEspecie especie) {
			this.especie = especie;
			return this;
		}

		public Builder leite(EnumLeite leite) {
			this.leite = leite;
			return this;
		}

		public Builder origemLeite(EnumOrigemLeite origemLeite) {
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

		public Builder quantidadeAmostras(Integer quantidadeAmostras) {
			this.quantidadeAmostras = quantidadeAmostras;
			return this;
		}

		public Analise build() {
			return new Analise(this);
		}
	}

}
