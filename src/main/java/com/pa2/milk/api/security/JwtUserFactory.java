package com.pa2.milk.api.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.pa2.milk.api.model.Credencial;
import com.pa2.milk.api.model.enums.EnumTipoPerfilUsuario;

public class JwtUserFactory {

	private JwtUserFactory() {

	}

	public static JwtUser create(Credencial credencial) {
		return new JwtUser(credencial.getId(), credencial.getUsername(), credencial.getSenha(),
				mapToGrantedAuthorities(credencial.getUsuario().getCodigoTipoPerfilUsuario()));

	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(EnumTipoPerfilUsuario perfilEnum) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));
		return authorities;

	}

}
