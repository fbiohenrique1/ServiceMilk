package com.pa2.milk.api.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.pa2.milk.api.model.Credencial;
import com.pa2.milk.api.model.Usuario;
import com.pa2.milk.api.model.enums.TipoPerfilUsuario;

public class JwtUserFactory {

	private JwtUserFactory() {

	}

	public static JwtUser create(Credencial credencial) {
		return new JwtUser(credencial.getId(), credencial.getUsername(), credencial.getSenha(),
				mapToGrantedAuthorities(credencial.getUsuario().getTipoPerfilUsuario()));

	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(TipoPerfilUsuario perfilEnum) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));
		return authorities;

	}

}
