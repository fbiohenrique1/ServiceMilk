package com.pa2.milk.api.model.dto;

import java.util.Collection;
import java.util.List;

import com.pa2.milk.api.model.Analise;
import com.pa2.milk.api.model.enums.EnumAnalisesSolicitadas;
import com.pa2.milk.api.model.enums.EnumEspecie;
import com.pa2.milk.api.model.enums.EnumLeite;
import com.pa2.milk.api.model.enums.EnumOrigemLeite;
import com.pa2.milk.api.model.enums.EnumProdutos;

public class AnaliseDto {

	//private EnumLeite leite;

	private EnumOrigemLeite origemLeite;

	private List<EnumProdutos> produtos;

	private List<EnumAnalisesSolicitadas> analisesSolicitadas;

	private EnumEspecie especie;

	private List<AmostraDto> amostras;

	private Integer quantidadeAmostras;

	private String descricao;

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

	public List<EnumProdutos> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<EnumProdutos> produtos) {
		this.produtos = produtos;
	}

	public List<EnumAnalisesSolicitadas> getAnalisesSolicitadas() {
		return analisesSolicitadas;
	}

	public void setAnalisesSolicitadas(List<EnumAnalisesSolicitadas> analisesSolicitadas) {
		this.analisesSolicitadas = analisesSolicitadas;
	}

	public EnumEspecie getEspecie() {
		return especie;
	}

	public void setEspecie(EnumEspecie especie) {
		this.especie = especie;
	}

	public List<AmostraDto> getAmostras() {
		return amostras;
	}

	public void setAmostras(List<AmostraDto> amostras) {
		this.amostras = amostras;
	}

	public Integer getQuantidadeAmostras() {
		return quantidadeAmostras;
	}

	public void setQuantidadeAmostras(Integer quantidadeAmostras) {
		this.quantidadeAmostras = quantidadeAmostras;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Analise transformarParaAnalise() {
		return new Analise.Builder(descricao).especie(especie).origemLeite(origemLeite)
				.analisesSolicitadas(analisesSolicitadas).quantidadeAmostras(quantidadeAmostras).produtos(produtos)
				.build();
	}
}
