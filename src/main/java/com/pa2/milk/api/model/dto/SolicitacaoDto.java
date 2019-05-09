package com.pa2.milk.api.model.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.pa2.milk.api.model.Analise;
import com.pa2.milk.api.model.Solicitacao;

public class SolicitacaoDto {

	private String cnpj;

	private List<AnaliseDto> listaAnalise;

	public SolicitacaoDto() {
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public List<AnaliseDto> getListaAnalise() {
		return listaAnalise;
	}

	public void setListaAnalise(List<AnaliseDto> listaAnalise) {
		this.listaAnalise = listaAnalise;
	}
	
	public Solicitacao transformarParaSolicitacao() {
		return new Solicitacao();
	}

	public List<Analise> transformarParaListaAnalise() {
		List<Analise> listaAnalise = this.listaAnalise.stream().map(obj -> obj.transformarParaAnalise())
				.collect(Collectors.toList());
		return listaAnalise;
	}

}
