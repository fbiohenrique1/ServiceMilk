package com.pa2.milk.api.model.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.pa2.milk.api.model.Analise;
import com.pa2.milk.api.model.Solicitacao;

public class SolicitacaoDto {

	private String cpfcnpj;

//	private Date dataCriada;

	private List<AnaliseDto> listaAnalise;

	public SolicitacaoDto() {
	}

	public String getCpfcnpj() {
		return cpfcnpj;
	}

	public void setCpfcnpj(String cpfcnpj) {
		this.cpfcnpj = cpfcnpj;
	}

	public List<AnaliseDto> getListaAnalise() {
		return listaAnalise;
	}

	public void setListaAnalise(List<AnaliseDto> listaAnalise) {
		this.listaAnalise = listaAnalise;
	}

//	public Date getDataCriada() {
//		return dataCriada;
//	}
//
//	public void setDataCriada(Date dataCriada) {
//		this.dataCriada = dataCriada;
//	}

	public Solicitacao transformarParaSolicitacao() {
		return new Solicitacao();
	}

	public List<Analise> transformarParaListaAnalise() {
		List<Analise> listaAnalise = this.listaAnalise.stream().map(obj -> obj.transformarParaAnalise())
				.collect(Collectors.toList());
		return listaAnalise;
	}

}
