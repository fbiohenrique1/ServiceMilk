package com.pa2.milk.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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

import com.pa2.milk.api.helper.PasswordUtils;
import com.pa2.milk.api.helper.Response;
import com.pa2.milk.api.model.Cliente;
import com.pa2.milk.api.model.Credencial;
import com.pa2.milk.api.model.Usuario;
import com.pa2.milk.api.model.dto.CadastroClienteDto;
import com.pa2.milk.api.model.enums.EnumTipoPerfilUsuario;
import com.pa2.milk.api.repository.ClienteRepository;
import com.pa2.milk.api.repository.UsuarioRepository;
import com.pa2.milk.api.service.ClienteService;
import com.pa2.milk.api.service.CredencialService;

@RestController
@RequestMapping(value = "/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {

	private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private CredencialService credencialService;

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	public List<Cliente> listarClientes() {
		List<Cliente> clientes = this.clienteRepository.findByCodigoTipoPerfilUsuario(EnumTipoPerfilUsuario.ROLE_CLIENTE.getCodigo());
		return clientes;
	}

	@PostMapping
	public ResponseEntity<Response<CadastroClienteDto>> cadastrarCliente(
			@Valid @RequestBody CadastroClienteDto clienteDto, BindingResult result) throws NoSuchAlgorithmException {

		log.info("Cadastrando Cliente:{}", clienteDto.toString());

		Response<CadastroClienteDto> response = new Response<CadastroClienteDto>();

		validarDadosExistentes(clienteDto, result);

		Cliente cliente = this.converterDtoParaCliente(clienteDto);

		Credencial credencial = this.converterDtoParaCredencial(clienteDto, result);

		if (result.hasErrors()) {

			log.error("Erro validando dados do cadastro Cliente: {}", result.getAllErrors());

			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.clienteService.salvar(cliente);
		credencial.setUsuario(cliente);
		this.credencialService.salvar(credencial);

		response.setData2(this.converterCadastroClienteDto(credencial));

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Cliente>> buscarClientePorId(@PathVariable("id") Integer id) {

		log.info("Buscar Cliente por Id");

		Response<Cliente> response = new Response<Cliente>();

		Cliente cliente = this.clienteService.buscarPorTipoPerfilUsuarioandID(EnumTipoPerfilUsuario.ROLE_CLIENTE, id);

		response.setData(Optional.ofNullable(cliente));

		verificarResposta(response);

		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "{id}")
	public ResponseEntity<Response<Cliente>> atualizarCliente(@PathVariable("id") Integer id,
			@Valid @RequestBody Cliente cliente, BindingResult result) throws NoSuchAlgorithmException {

		log.info("Atualizando o Cliente:{}", cliente.toString());

		Response<Cliente> response = new Response<Cliente>();

		Cliente cliente1 = this.clienteService.buscarPorTipoPerfilUsuarioandID(EnumTipoPerfilUsuario.ROLE_CLIENTE, id);

		response.setData(Optional.ofNullable(cliente1));

		verificarResposta(response);

		this.atualizarDadosCliente(cliente1, cliente, result);

		if (result.hasErrors()) {
			log.error("Erro validando lancamento:{}", result.getAllErrors());

			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		this.clienteService.salvar(cliente1);

		return ResponseEntity.ok(response);

	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<Response<Cliente>> deletarCliente(@PathVariable("id") Integer id) {

		log.info("Removendo Cliente: {}", id);

		Response<Cliente> response = new Response<Cliente>();

		Cliente cliente = this.clienteService.buscarPorTipoPerfilUsuarioandID(EnumTipoPerfilUsuario.ROLE_CLIENTE, id);

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

	private void validarDadosExistentes(CadastroClienteDto clienteDto, BindingResult result) {

		this.clienteService.buscarPorCpf(clienteDto.getCpf())
				.ifPresent(cli -> result.addError(new ObjectError("cliente", "Cliente já existente")));

		this.credencialService.buscarPorUsername(clienteDto.getUsername())
				.ifPresent(cre -> result.addError(new ObjectError("credencial", "Credencial já existente")));

	}

	private Cliente converterDtoParaCliente(CadastroClienteDto clienteDto) {

		Usuario cli = new Cliente();
		cli.setCpf(clienteDto.getCpf());
		cli.setEmail(clienteDto.getEmail());
		cli.setNome(clienteDto.getNome());
		cli.setCodigoTipoPerfilUsuario(EnumTipoPerfilUsuario.ROLE_CLIENTE);
		((Cliente) cli).setTelefones(clienteDto.getTelefones());

		return (Cliente) cli;
	}

	private Credencial converterDtoParaCredencial(CadastroClienteDto clienteDto, BindingResult result)
			throws NoSuchAlgorithmException {

		Credencial cre = new Credencial();
		cre.setUsername(clienteDto.getUsername());
		cre.setSenha(PasswordUtils.gerarBCrypt(clienteDto.getSenha()));
		return cre;
	}

	private CadastroClienteDto converterCadastroClienteDto(Credencial credencial) {
		CadastroClienteDto clienteDto = new CadastroClienteDto();

		clienteDto.setId(credencial.getId());
		clienteDto.setUsername(credencial.getUsername());
		clienteDto.setEmail(credencial.getUsuario().getEmail());
		clienteDto.setCpf(credencial.getUsuario().getCpf());
		clienteDto.setNome(credencial.getUsuario().getNome());
		clienteDto.setTelefones(((Cliente) credencial.getUsuario()).getTelefones());
		return clienteDto;
	}

}
