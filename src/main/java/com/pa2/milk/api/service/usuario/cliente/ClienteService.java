package com.pa2.milk.api.service.usuario.cliente;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pa2.milk.api.controller.usuario.UsuarioController;
import com.pa2.milk.api.model.usuario.cliente.Cliente;
import com.pa2.milk.api.repository.usuario.cliente.ClienteRepository;

@Service
public class ClienteService {


	private static final Logger log = LoggerFactory.getLogger(Cliente.class);

	
	@Autowired
	private ClienteRepository clienteRepository;
	
//	public Cliente buscar(Integer id) {
//		Optional<Cliente> objCliente = clienteRepository.findById(id);
//		return objCliente.orElse(null);
//	}
	
	public Optional<Cliente> buscarPorId(Integer id) {
		return this.clienteRepository.findById(id);
	}
	
	public void salvar(Cliente cliente) {
		clienteRepository.save(cliente);
	}
	
	
	public Optional<Cliente> buscarPorCpf(String cpf) {
		log.info("Buscando Cliente pelo Cpf: {}",cpf);
		return Optional.ofNullable(this.clienteRepository.findByCpf(cpf));
	}

	public Optional<Cliente> buscarPorEmail(String email) {
		log.info("Buscando Cliente pelo Email: {}", email);
		return Optional.ofNullable(this.clienteRepository.findByEmail(email));
	}
	
	
	public void remover(Integer id) {
		log.info("Removendo Lancamento pelo Id: {}", id);
		this.clienteRepository.deleteById(id);
	}
}
