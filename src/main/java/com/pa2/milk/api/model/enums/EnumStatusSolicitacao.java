package com.pa2.milk.api.model.enums;

public enum EnumStatusSolicitacao {
	PENDENTE(1, "Pendente"), APROVADA(2, "Aprovada"), REPROVADA(3, "Reprovada"),
	APROVADO_COM_RESTRIÇÕES(4, "Aprovado com restrições"), ANALISANDO(5, "Analisando"),
	PAGAMENTO_PENDENTE(6, "Pagamento Pendente"), LAUDO_LIBERADO(4, "Laudo Liberado");

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
