package com.pa2.milk.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pa2.milk.api.model.Administrador;
import com.pa2.milk.api.model.enums.EnumTipoPerfilUsuario;
import com.pa2.milk.api.repository.AdministradorRepository;

@Service
public class AdministradorService {

	private static final Logger log = LoggerFactory.getLogger(AdministradorService.class);

	@Autowired
	private AdministradorRepository administradorRepository;

	public void salvar(Administrador administrador) {
		log.info("Salvando Administrador ");
		administradorRepository.save(administrador);
	}

	public Administrador buscarPorId(Integer id) {
		log.info("Buscando Administrador por ID ");
		Optional<Administrador> objAdministrador = administradorRepository.findById(id);
		return objAdministrador.orElse(null);
	}

	public Optional<Administrador> buscarPorCpf(String cpf) {
		log.info("Buscando Administrador pelo Cpf: {}", cpf);
		return Optional.ofNullable(this.administradorRepository.findByCpf(cpf));
	}

	public Administrador buscarPorCpfNormal(String cpf) {
		log.info("Buscando Administrador pelo Cpf: {}", cpf);
		return this.administradorRepository.findByCpf(cpf);
	}

	public Optional<Administrador> buscarPorEmail(String email) {
		log.info("Buscando Administrador pelo Email: {}", email);
		return Optional.ofNullable(this.administradorRepository.findByEmail(email));
	}

	public List<Administrador> listarAdministrador() {
		log.info("Listando Administrador");
		return this.administradorRepository.findAll();
	}

	public List<Administrador> buscarPorTipoPerfilUsuario(EnumTipoPerfilUsuario tipoPerfilUsuario) {
		log.info("Buscando usuario pelo tipoPerfilUsuario: {}", tipoPerfilUsuario);
		return this.administradorRepository.findByCodigoTipoPerfilUsuarioAndAtivoTrue(tipoPerfilUsuario.getCodigo());
	}

	public Optional<Administrador> buscarPorTipoPerfilUsuarioandID(EnumTipoPerfilUsuario tipoPerfilUsuario,
			Integer id) {
		log.info("Buscando usuario pelo tipoPerfilUsuario: {}", tipoPerfilUsuario);
		return Optional.ofNullable(this.administradorRepository
				.findByCodigoTipoPerfilUsuarioAndIdAndAtivoTrue(tipoPerfilUsuario.getCodigo(), id));
	}

}