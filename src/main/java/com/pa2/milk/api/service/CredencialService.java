package com.pa2.milk.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pa2.milk.api.model.Credencial;
import com.pa2.milk.api.repository.CredencialRepository;

@Service
public class CredencialService {

	@Autowired
	private CredencialRepository credencialRepositorio;

	public Optional<Credencial> buscarPorUsername(String username) {
		return Optional.ofNullable(this.credencialRepositorio.findByUsername(username));
	}

}
