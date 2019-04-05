/*package com.pa2.milk.api.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.pa2.milk.api.model.Usuario;
import com.pa2.milk.api.model.enums.TipoPerfilUsuario;


public class JwtUserFactory {
	
	private JwtUserFactory() {
		
	}

	public static JwtUser create(Usuario usuario) {
		return new JwtUser(usuario.getId(), usuario.getCredencial().getUsername(), usuario.getCredencial().getSenha(), mapToGrantedAuthorities(usuario.getTipoPerfilUsuario()));
		
	}
	
	private static List<GrantedAuthority> mapToGrantedAuthorities(TipoPerfilUsuario perfilEnum){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));
		return authorities;
		
	}
	
	
}
*/