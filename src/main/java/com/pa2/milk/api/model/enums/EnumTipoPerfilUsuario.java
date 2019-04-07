package com.pa2.milk.api.model.enums;

public enum EnumTipoPerfilUsuario {

	ROLE_CLIENTE(1),
	ROLE_BOLSISTA(2),
	ROLE_ADMINISTRADOR(3);
	
	private Integer codigo;

	private EnumTipoPerfilUsuario(Integer codigo) {
		this.codigo = codigo;	
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public static EnumTipoPerfilUsuario porCodigo(Integer codigo) {
		if (codigo.equals(null)) {
			return null;
		}

		for (EnumTipoPerfilUsuario tipo : EnumTipoPerfilUsuario.values()) {
			if (codigo.equals(tipo.getCodigo())) {
				return tipo;
			}
		}
		throw new IllegalArgumentException("Código Inválido: " + codigo);
	}

}
