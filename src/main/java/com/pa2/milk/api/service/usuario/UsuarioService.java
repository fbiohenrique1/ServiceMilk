package com.pa2.milk.api.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pa2.milk.api.service.usuario.administrador.AdministradorService;
import com.pa2.milk.api.service.usuario.bolsista.BolsistaService;
import com.pa2.milk.api.service.usuario.cliente.ClienteService;

@Service
public class UsuarioService {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private AdministradorService administradorService;
	
	@Autowired
	private BolsistaService bolsistaService;

	public ClienteService getClienteRepository() {
		return clienteService;
	}

	public AdministradorService getAdministradorService() {
		return administradorService;
	}

	public BolsistaService getBolsistaService() {
		return bolsistaService;
	}
}
