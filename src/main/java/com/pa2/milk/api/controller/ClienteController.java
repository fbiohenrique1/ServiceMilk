package com.pa2.milk.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pa2.milk.api.helper.Response;
import com.pa2.milk.api.model.Cliente;
import com.pa2.milk.api.model.Usuario;
import com.pa2.milk.api.service.ClienteService;

@RestController
@RequestMapping(value = "/usuarios/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

	private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public List<Cliente> listarClientes() {
		List<Cliente> clientes = this.clienteService.listarClientes();
		return clientes;
	}

	@PostMapping
	public ResponseEntity<Response<Cliente>> cadastrarCliente(@Valid @RequestBody Cliente cliente, BindingResult result)
			throws NoSuchAlgorithmException {

		log.info("Cadastrando Cliente:{}", cliente.toString());

		Response<Cliente> response = new Response<Cliente>();

		response.setData(Optional.ofNullable(cliente));

		Usuario user = cliente;

		if (result.hasErrors()) {

			log.error("Erro validando dados do cadastro Cliente: {}", result.getAllErrors());

			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.clienteService.salvar((Cliente) user);

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Cliente>> buscarClientePorId(@PathVariable("id") Integer id) {

		log.info("Buscar Cliente por Id");

		Response<Cliente> response = new Response<Cliente>();

		Cliente cliente = this.clienteService.buscarPorId(id);

		response.setData(Optional.ofNullable(cliente));

		verificarResposta(response);

		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "{id}")
	public ResponseEntity<Response<Cliente>> atualizarCliente(@PathVariable("id") Integer id,
			@Valid @RequestBody Cliente cliente, BindingResult result) throws NoSuchAlgorithmException {

		log.info("Atualizando o Cliente:{}", cliente.toString());

		Response<Cliente> response = new Response<Cliente>();

		Cliente cliente1 = this.clienteService.buscarPorId(id);

		response.setData(Optional.ofNullable(cliente1));

		verificarResposta(response);

		this.atualizarDadosCliente(cliente1, cliente, result);

		if (result.hasErrors()) {
			log.error("Erro validando lancamento:{}", result.getAllErrors());

			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		this.clienteService.salvar(cliente);

		return ResponseEntity.ok(response);

	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<Response<Cliente>> deletarCliente(@PathVariable("id") Integer id) {

		log.info("Removendo Cliente: {}", id);

		Response<Cliente> response = new Response<Cliente>();

		Cliente cliente = this.clienteService.buscarPorId(id);

		response.setData(Optional.ofNullable(cliente));

		verificarResposta(response);

		this.clienteService.remover(id);

		return ResponseEntity.ok(response);
	}

	private void atualizarDadosCliente(Cliente cliente, Cliente cliente2, BindingResult result)
			throws NoSuchAlgorithmException {

		cliente.setNome(cliente2.getNome());

		if (!cliente.getEmail().equals(cliente2.getEmail())) {

			this.clienteService.buscarPorEmail(cliente2.getEmail())
					.ifPresent(clien -> result.addError(new ObjectError("email", "Email já exitente.")));
			cliente.setEmail(cliente2.getEmail());
		}

		if (!cliente.getCpf().equals(cliente2.getCpf())) {

			this.clienteService.buscarPorCpf(cliente2.getCpf())
					.ifPresent(clien -> result.addError(new ObjectError("cpf", "CPF já existente.")));
			cliente.setCpf(cliente2.getCpf());
		}

	}

	private void verificarResposta(Response<Cliente> response) {
		if (!response.getData().isPresent()) {
			log.info("Cliente não encontrado");

			response.getErros().add("Cliente não encontrado");

			ResponseEntity.badRequest().body(response);
		}
	}

}
