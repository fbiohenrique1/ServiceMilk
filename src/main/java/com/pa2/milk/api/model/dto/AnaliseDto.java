package com.pa2.milk.api.model.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pa2.milk.api.model.Amostra;
import com.pa2.milk.api.model.Analise;
import com.pa2.milk.api.model.Solicitacao;
import com.pa2.milk.api.model.enums.EnumAnalisesSolicitadas;
import com.pa2.milk.api.model.enums.EnumLeite;
import com.pa2.milk.api.model.enums.EnumOrigemLeite;
import com.pa2.milk.api.model.enums.EnumProdutos;

public class AnaliseDto {

	private Collection<EnumLeite> leite;

	private Collection<EnumOrigemLeite> origemLeite;

	private Collection<EnumProdutos> produtos;

	private Collection<EnumAnalisesSolicitadas> analisesSolicitadas;

	private String especie;

	private List<AmostraDto> amostras = new ArrayList<>();

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

	public List<AmostraDto> getAmostra() {
		return amostras;
	}

	public void setAmostra(List<AmostraDto> amostra) {
		this.amostras = amostra;
	}

	public Analise transformarParaAnalise() {
		return new Analise.Builder(especie).leite(leite).origemLeite(origemLeite)
				.analisesSolicitadas(analisesSolicitadas).build();
	}
}
