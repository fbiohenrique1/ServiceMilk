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
import com.pa2.milk.api.model.Bolsista;
import com.pa2.milk.api.model.Credencial;
import com.pa2.milk.api.model.Usuario;
import com.pa2.milk.api.model.dto.CadastroClienteDto;
import com.pa2.milk.api.model.enums.EnumTipoPerfilUsuario;
import com.pa2.milk.api.repository.CredencialRepository;
import com.pa2.milk.api.repository.UsuarioRepository;
import com.pa2.milk.api.service.BolsistaService;
import com.pa2.milk.api.service.CredencialService;

@RestController
@RequestMapping(value = "/bolsista")
@CrossOrigin(origins = "*")
public class BolsistaController {
	private static final Logger log = LoggerFactory.getLogger(BolsistaController.class);

	@Autowired
	private BolsistaService bolsistaService;

	@Autowired
	private CredencialService credencialService;

	@Autowired
	private CredencialRepository credencialRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping
	public ResponseEntity<Response<CadastroClienteDto>> cadastrarBolsista(
			@Valid @RequestBody CadastroClienteDto clienteDto, BindingResult result) throws NoSuchAlgorithmException {

		log.info("Cadastrando Bolsista:{}", clienteDto.toString());

		Response<CadastroClienteDto> response = new Response<CadastroClienteDto>();

		validarDadosExistentes(clienteDto, result);

		Bolsista bolsista = this.converterDtoParaBolsista(clienteDto);

		Credencial credencial = this.converterDtoParaCredencial(clienteDto, result);

		if (result.hasErrors()) {
			log.error("Erro validando dados do cadastro Bolsista: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.bolsistaService.salvar(bolsista);
		credencial.setUsuario(bolsista);
		this.credencialService.salvar(credencial);

		response.setData(this.converterCadastroClienteDto(credencial));

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Bolsista>> buscarBolsistaPorId(@PathVariable("id") Integer id) {

		log.info("Buscar Bolsista por Id");

		Response<Bolsista> response = new Response<Bolsista>();

		Optional<Bolsista> bolsista = this.bolsistaService
				.buscarPorTipoPerfilUsuarioandID(EnumTipoPerfilUsuario.ROLE_BOLSISTA, id);

		if (!bolsista.isPresent()) {
			log.info("Bolsista não encontrado");
			response.getErros().add("Bolsista não encontrado");
			ResponseEntity.badRequest().body(response);
		}

		response.setData(bolsista.get());

		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "{id}")
	public ResponseEntity<Response<CadastroClienteDto>> atualizarBolsista(@PathVariable("id") Integer id,
			@Valid @RequestBody CadastroClienteDto bolsistaDto, BindingResult result) throws NoSuchAlgorithmException {

		log.info("Atualizando o Bolsista:{}", bolsistaDto.toString());

		Response<CadastroClienteDto> response = new Response<CadastroClienteDto>();

		Optional<Credencial> credencial = this.credencialService.buscarPorId(id);

		if (!credencial.isPresent()) {
			result.addError(new ObjectError("credencial", "Credencial não encontrada."));
		}

		this.atualizarDadosBolsista(credencial.get(), bolsistaDto, result);

		if (result.hasErrors()) {
			log.error("Erro validando a Credencial:{}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.credencialService.salvar(credencial.get());
		response.setData(this.converterCadastroClienteDto(credencial.get()));

		return ResponseEntity.ok(response);

	}

	private Bolsista converterDtoParaBolsista(CadastroClienteDto clienteDto) {

		Usuario bol = new Bolsista();
		bol.setCpf(clienteDto.getCpf());
		bol.setEmail(clienteDto.getEmail());
		bol.setNome(clienteDto.getNome());
		bol.setCodigoTipoPerfilUsuario(EnumTipoPerfilUsuario.ROLE_BOLSISTA);
		return (Bolsista) bol;
	}

	private void validarDadosExistentes(CadastroClienteDto clienteDto, BindingResult result) {

		this.bolsistaService.buscarPorCpf(clienteDto.getCpf())
				.ifPresent(cli -> result.addError(new ObjectError("bolsista", "Bolsista já existente")));

		this.credencialService.buscarPorUsername(clienteDto.getUsername())
				.ifPresent(cre -> result.addError(new ObjectError("credencial", "Credencial já existente")));

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
		return clienteDto;
	}

	@GetMapping
	public List<Bolsista> listarBolsistas() {
		List<Bolsista> bolsista = this.bolsistaService.buscarPorTipoPerfilUsuario(EnumTipoPerfilUsuario.ROLE_BOLSISTA);
		return bolsista;
	}

	private void atualizarDadosBolsista(Credencial credencial, CadastroClienteDto bolsistaDto, BindingResult result)
			throws NoSuchAlgorithmException {

		credencial.getUsuario().setNome(bolsistaDto.getNome());

		if (!credencial.getUsuario().getEmail().equals(bolsistaDto.getEmail())) {

			this.bolsistaService.buscarPorEmail(bolsistaDto.getEmail())
					.ifPresent(clien -> result.addError(new ObjectError("email", "Email já exitente.")));
			credencial.getUsuario().setEmail(bolsistaDto.getEmail());
		}

		if (!credencial.getUsuario().getCpf().equals(bolsistaDto.getCpf())) {

			this.bolsistaService.buscarPorCpf(bolsistaDto.getCpf())
					.ifPresent(clien -> result.addError(new ObjectError("cpf", "CPF já existente.")));
			credencial.getUsuario().setCpf(bolsistaDto.getCpf());
		}

		if (!credencial.getUsername().equals(bolsistaDto.getUsername())) {

			this.credencialService.buscarPorUsername(bolsistaDto.getUsername())
					.ifPresent(crede -> result.addError(new ObjectError("username", "Username já existente.")));
			credencial.setUsername(bolsistaDto.getUsername());
		}

		credencial.setSenha(PasswordUtils.gerarBCrypt(bolsistaDto.getSenha()));

	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<Response<Credencial>> deletarBolsista(@PathVariable("id") Integer id) {

		log.info("Removendo Bolsista: {}", id);

		Response<Credencial> response = new Response<Credencial>();

		Optional<Credencial> credencial = credencialService.buscarPorId(id);

		if (!credencial.isPresent()) {
			log.info("Credencial não encontrada");
			response.getErros().add("Credencial não encontrada");
			ResponseEntity.badRequest().body(response);
		}

		response.setData(credencial.get());
		this.usuarioRepository.deleteById(credencial.get().getUsuario().getId());
		this.credencialRepository.deleteById(credencial.get().getId());

		return ResponseEntity.ok(response);
	}

}
