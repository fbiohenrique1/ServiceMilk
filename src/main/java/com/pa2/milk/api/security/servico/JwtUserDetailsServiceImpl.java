package com.pa2.milk.api.security.servico;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pa2.milk.api.model.Credencial;
import com.pa2.milk.api.security.JwtUserFactory;
import com.pa2.milk.api.service.CredencialService;

@Service("userDetailsService")
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private CredencialService credencialService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Credencial> credencial = this.credencialService.buscarPorUsername(username);

		if (credencial.isPresent()) {
			return JwtUserFactory.create(credencial.get());
		}

		throw new UsernameNotFoundException("Username não Encontrado.");
	}

}
