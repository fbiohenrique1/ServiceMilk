package com.pa2.milk.api.service.usuario.cliente;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pa2.milk.api.model.usuario.cliente.Cliente;
import com.pa2.milk.api.repository.usuario.cliente.ClienteRepository;

@Service
public class ClienteService {

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
}
