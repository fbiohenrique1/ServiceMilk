package com.pa2.milk.api.model.dto;

import com.pa2.milk.api.model.enums.EnumStatusSolicitacao;

public class SolicitacaoDetalhesDto {

	private EnumStatusSolicitacao status;
	private String observacao;

	public EnumStatusSolicitacao getStatus() {
		return status;
	}

	public void setStatus(EnumStatusSolicitacao status) {
		this.status = status;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public String toString() {
		return "SolicitacaoDetalhesDto [status=" + status + ", observacao=" + observacao + "]";
	}

}
