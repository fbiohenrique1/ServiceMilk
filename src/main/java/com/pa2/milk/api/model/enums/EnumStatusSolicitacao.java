package com.pa2.milk.api.model.enums;

public enum EnumStatusSolicitacao {
	APROVADA(1, "Aprovada"), PENDENTE(2, "Pendente"), PAGAMENTO_PENDENTE(3, "Pagamento Pendente"),
	RECUSADA(4, "Recusada");

	private Integer codigo;
	private String statusDescricao;

	private EnumStatusSolicitacao(Integer codigo, String statusDescricao) {
		this.codigo = codigo;
		this.statusDescricao = statusDescricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getStatusDescricao() {
		return statusDescricao;
	}

	public void setStatusDescricao(String statusDescricao) {
		this.statusDescricao = statusDescricao;
	}

	public static EnumStatusSolicitacao porCodigo(Integer codigo) {
		if (codigo.equals(null)) {
			return null;
		}

		for (EnumStatusSolicitacao status : EnumStatusSolicitacao.values()) {
			if (codigo.equals(status.getCodigo())) {
				return status;
			}
		}
		throw new IllegalArgumentException("Código Inválido: " + codigo);
	}
}
