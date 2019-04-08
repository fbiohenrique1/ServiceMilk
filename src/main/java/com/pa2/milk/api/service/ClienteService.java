package com.pa2.milk.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pa2.milk.api.model.Cliente;
import com.pa2.milk.api.model.enums.EnumTipoPerfilUsuario;
import com.pa2.milk.api.repository.ClienteRepository;

@Service
public class ClienteService {

	private static final Logger log = LoggerFactory.getLogger(ClienteService.class);

	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente buscarPorId(Integer id) {
		log.info("Buscando Cliente por ID ");
		Optional<Cliente> objCliente = clienteRepository.findById(id);
		return objCliente.orElse(null);
	}

	public void salvar(Cliente cliente) {
		log.info("Salvando Cliente ");
		clienteRepository.save(cliente);
	}

	public Optional<Cliente> buscarPorCpf(String cpf) {
		log.info("Buscando Cliente pelo Cpf: {}", cpf);
		return Optional.ofNullable(this.clienteRepository.findByCpf(cpf));
	}

	public Cliente buscarPorCpfNormal(String cpf) {
		log.info("Buscando Cliente pelo Cpf: {}", cpf);
		return this.clienteRepository.findByCpf(cpf);
	}

	public Optional<Cliente> buscarPorEmail(String email) {
		log.info("Buscando Cliente pelo Email: {}", email);
		return Optional.ofNullable(this.clienteRepository.findByEmail(email));
	}

	public void remover(Integer id) {
		log.info("Removendo Cliente pelo Id: {}", id);
		this.clienteRepository.deleteById(id);
	}

	public List<Cliente> listarClientes() {
		log.info("Listando Clientes");
		return this.clienteRepository.findAll();
	}
	
	public List<Cliente> buscarPorTipoPerfilUsuario(EnumTipoPerfilUsuario tipoPerfilUsuario) {
		log.info("Buscando usuario pelo tipoPerfilUsuario: {}", tipoPerfilUsuario);
		return this.clienteRepository.findByCodigoTipoPerfilUsuario(tipoPerfilUsuario.getCodigo());
	}


	public Cliente buscarPorTipoPerfilUsuarioandID(EnumTipoPerfilUsuario tipoPerfilUsuario, Integer id) {
		log.info("Buscando usuario pelo tipoPerfilUsuario: {}", tipoPerfilUsuario);
		return this.clienteRepository.findByCodigoTipoPerfilUsuarioAndId(tipoPerfilUsuario, id);
	}

	

}
