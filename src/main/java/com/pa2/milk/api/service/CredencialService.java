package com.pa2.milk.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pa2.milk.api.model.Cliente;
import com.pa2.milk.api.model.Credencial;
import com.pa2.milk.api.model.enums.EnumTipoPerfilUsuario;
import com.pa2.milk.api.repository.CredencialRepository;

@Service
public class CredencialService {

	private static final Logger log = LoggerFactory.getLogger(CredencialService.class);

	@Autowired
	private CredencialRepository credencialRepository;

	public Optional<Credencial> buscarPorUsername(String username) {
		log.info("Buscando usuario pelo username: {}", username);
		return Optional.ofNullable(this.credencialRepository.findByUsername(username));
	}

	public void salvar(Credencial credencial) {
		log.info("Salvando Credencial ");
		credencialRepository.save(credencial);
	}

	public Optional<Credencial> buscarPorId(Integer id) {
		log.info("Buscando Credencial por ID ");
		return this.credencialRepository.findById(id);
	}
	
	public void remover(Credencial credencial) {
		log.info("Removendo Credencial pelo Objeto: {}",credencial);
		this.credencialRepository.delete(credencial);
	}


}
