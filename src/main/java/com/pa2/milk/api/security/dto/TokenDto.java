package com.pa2.milk.api.security.dto;

public class TokenDto {

	private String token;
	private String perfil;

	public TokenDto() {
	}

	public TokenDto(String token, String perfil) {
		this.token = token;
		this.perfil = perfil;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	

}
