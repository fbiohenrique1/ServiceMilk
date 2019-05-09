package com.pa2.milk.api.model.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.pa2.milk.api.model.Analise;
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

	private Integer quantidadeAmostras;

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

	public Integer getQuantidadeAmostras() {
		return quantidadeAmostras;
	}
	
	public void setQuantidadeAmostras(Integer quantidadeAmostras) {
		this.quantidadeAmostras = quantidadeAmostras;
	}
	
	public Analise transformarParaAnalise() {
		return new Analise.Builder(especie).leite(leite).origemLeite(origemLeite)
				.analisesSolicitadas(analisesSolicitadas).quantidadeAmostras(quantidadeAmostras).produtos(produtos).build();
	}
}
