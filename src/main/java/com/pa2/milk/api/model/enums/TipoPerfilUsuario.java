package com.pa2.milk.api.model.enums;

public enum TipoPerfilUsuario {
	ROLE_CLIENTE(1, "ROLE_CLIENTE"),
	ROLE_BOLSISTA(2, "ROLE_BOLSISTA"),
	ROLE_ADMINISTRADOR(3, "ROLE_ADMINISTRADOR");

	private Integer cod;
	private String desc;

	
	
	
	
	
	
	
	
	
	private TipoPerfilUsuario(Integer cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	

}
