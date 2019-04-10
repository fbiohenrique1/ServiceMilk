package com.pa2.milk.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService {

	private static final Logger log = LoggerFactory.getLogger(AdministradorService.class);

	@Autowired
	private AdministradorRepository administradorRepository;

	public Administrador buscarPorId(Integer id) {
		log.info("Buscando Administrador por ID ");
		Optional<Administrador> objAdministrador = administradorRepository.findById(id);
		return objAdministrador.orElse(null);
	}

	public void salvar(Administrador administrador) {
		log.info("Salvando Administrador ");
		clienteRepository.save(administrador);
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
	
	public void remover(Integer id) {
		log.info("Removendo Administrador pelo Id: {}", id);
		this.administradorRepository.deleteById(id);
	}

	public List<Administrador> listarAdministrador() {
		log.info("Listando Administradores");
		return this.clienteRepository.findAll();
	}
	
	public List<Administrador> buscarPorTipoPerfilUsuario(EnumTipoPerfilUsuario tipoPerfilUsuario) {
		log.info("Buscando usuario pelo tipoPerfilUsuario: {}", tipoPerfilUsuario);
		return this.administradorRepository.findByCodigoTipoPerfilUsuario(tipoPerfilUsuario.getCodigo());
	}


	public Administrador buscarPorTipoPerfilUsuarioandID(EnumTipoPerfilUsuario tipoPerfilUsuario, Integer id) {
		log.info("Buscando usuario pelo tipoPerfilUsuario: {}", tipoPerfilUsuario);
		return this.administradorRepository.findByCodigoTipoPerfilUsuarioAndId(tipoPerfilUsuario.getCodigo(), id);
	}

}
