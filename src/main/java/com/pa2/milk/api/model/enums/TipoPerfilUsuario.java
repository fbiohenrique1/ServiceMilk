package com.pa2.milk.api.model.enums;

public enum TipoPerfilUsuario {



	ROLE_CLIENTE(1, "ROLE_CLIENTE"),
	ROLE_BOLSISTA(2, "ROLE_BOLSISTA"),
	ROLE_ADMINISTRADOR(3, "ROLE_ADMINISTRADOR");

	private Integer codigo;
	private String descricao;

	private TipoPerfilUsuario(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
