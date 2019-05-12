
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

import com.pa2.milk.api.helper.PasswordUtils;
import com.pa2.milk.api.helper.Response;
import com.pa2.milk.api.model.Cliente;
import com.pa2.milk.api.model.Credencial;
import com.pa2.milk.api.model.Fazenda;
import com.pa2.milk.api.model.Solicitacao;
import com.pa2.milk.api.model.Usuario;
import com.pa2.milk.api.model.dto.CadastroClienteDto;
import com.pa2.milk.api.model.enums.EnumTipoPerfilUsuario;
import com.pa2.milk.api.repository.CredencialRepository;
import com.pa2.milk.api.repository.UsuarioRepository;
import com.pa2.milk.api.service.ClienteService;
import com.pa2.milk.api.service.CredencialService;
import com.pa2.milk.api.service.FazendaService;
import com.pa2.milk.api.service.SolicitacaoService;

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
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CredencialRepository credencialRepository;

	@Autowired
	private FazendaService fazendaService;

	@Autowired
	private SolicitacaoService solicitacaoService;

	@GetMapping
	public List<Cliente> listarClientes() {
		List<Cliente> clientes = this.clienteService.buscarPorTipoPerfilUsuario(EnumTipoPerfilUsuario.ROLE_CLIENTE);
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

		response.setData(this.converterCadastroClienteDto(credencial));

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Cliente>> buscarClientePorId(@PathVariable("id") Integer id) {

		log.info("Buscar Cliente por Id");

		Response<Cliente> response = new Response<Cliente>();

		Optional<Cliente> cliente = this.clienteService
				.buscarPorTipoPerfilUsuarioandID(EnumTipoPerfilUsuario.ROLE_CLIENTE, id);

		if (!cliente.isPresent()) {
			log.info("Cliente não encontrado: {}", cliente.get());
			response.getErros().add("Cliente não encontrado");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(cliente.get());

		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "{id}")
	public ResponseEntity<Response<CadastroClienteDto>> atualizarCliente(@PathVariable("id") Integer id,
			@Valid @RequestBody CadastroClienteDto clienteDto, BindingResult result) throws NoSuchAlgorithmException {

		log.info("Atualizando o Cliente:{}", clienteDto.toString());

		Response<CadastroClienteDto> response = new Response<CadastroClienteDto>();

		Optional<Credencial> credencial = this.credencialService.buscarPorId(id);

		if (!credencial.isPresent()) {
			log.info("Cliente não encontrado: {}", credencial.get());
			result.addError(new ObjectError("credencial", "Credencial não encontrada."));
		}

		this.atualizarDadosCliente(credencial.get(), clienteDto, result);

		if (result.hasErrors()) {
			log.error("Erro validando a Credencial:{}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.credencialService.salvar(credencial.get());
		response.setData(this.converterCadastroClienteDto(credencial.get()));

		return ResponseEntity.ok(response);

	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<Response<Credencial>> deletarCliente(@PathVariable("id") Integer id) {

		log.info("Removendo Cliente: {}", id);

		Response<Credencial> response = new Response<Credencial>();

		Optional<Credencial> credencial = credencialService.buscarPorId(id);

		if (!credencial.isPresent()) {
			log.info("Credencial não encontrada");
			response.getErros().add("Credencial não encontrada");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(credencial.get());

		this.usuarioRepository.deleteById(credencial.get().getUsuario().getId());
		this.credencialRepository.deleteById(credencial.get().getId());

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "{id}/fazenda")
	public List<Fazenda> buscarFazendaClientePorId(@PathVariable("id") Integer id) {
		log.info("Buscar Fazenda por Id do Cliente");

		List<Fazenda> farm = this.fazendaService.buscarFazendaClienteId(id);
		return farm;
	}

	@GetMapping(value = "{id}/solicitacao")
	public List<Solicitacao> buscarSolicitacaoClientePorId(@PathVariable("id") Integer id) {
		log.info("Buscar Solicitação por Id do Cliente");

		List<Solicitacao> s = this.solicitacaoService.buscarSolicitacaoClienteId(id);
		return s;
	}

	private void atualizarDadosCliente(Credencial credencial, CadastroClienteDto clienteDto, BindingResult result)
			throws NoSuchAlgorithmException {

		credencial.getUsuario().setNome(clienteDto.getNome());

		if (!credencial.getUsuario().getEmail().equals(clienteDto.getEmail())) {

			this.clienteService.buscarPorEmail(clienteDto.getEmail())
					.ifPresent(clien -> result.addError(new ObjectError("email", "Email já exitente.")));
			credencial.getUsuario().setEmail(clienteDto.getEmail());
		}

		if (!credencial.getUsuario().getCpf().equals(clienteDto.getCpf())) {

			this.clienteService.buscarPorCpf(clienteDto.getCpf())
					.ifPresent(clien -> result.addError(new ObjectError("cpf", "CPF já existente.")));
			credencial.getUsuario().setCpf(clienteDto.getCpf());
		}

		if (!credencial.getUsername().equals(clienteDto.getUsername())) {

			this.credencialService.buscarPorUsername(clienteDto.getUsername())
					.ifPresent(crede -> result.addError(new ObjectError("username", "Username já existente.")));
			credencial.setUsername(clienteDto.getUsername());
		}

		credencial.setSenha(PasswordUtils.gerarBCrypt(clienteDto.getSenha()));

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
		((Cliente) cli).setTelefone1(clienteDto.getTelefone1());
		((Cliente) cli).setTelefone2(clienteDto.getTelefone2());

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
		clienteDto.setTelefone1(((Cliente) credencial.getUsuario()).getTelefone1());
		clienteDto.setTelefone2(((Cliente) credencial.getUsuario()).getTelefone2());
		return clienteDto;
	}

}
