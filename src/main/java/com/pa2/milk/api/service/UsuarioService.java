package com.pa2.milk.api.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pa2.milk.api.model.Usuario;
import com.pa2.milk.api.repository.UsuarioRepositorio;

@Service
public class UsuarioService {

	private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	public Optional<Usuario> buscarPorEmail(String email) {
		log.info("Buscando usuario pelo email: {}", email);
		return Optional.ofNullable(this.usuarioRepositorio.findByEmail(email));
	}

}
