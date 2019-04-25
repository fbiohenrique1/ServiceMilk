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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pa2.milk.api.helper.PasswordUtils;
import com.pa2.milk.api.helper.Response;
import com.pa2.milk.api.model.Administrador;
import com.pa2.milk.api.model.Credencial;
import com.pa2.milk.api.model.Usuario;
import com.pa2.milk.api.model.dto.CadastroClienteDto;
import com.pa2.milk.api.model.enums.EnumTipoPerfilUsuario;
import com.pa2.milk.api.repository.AdministradorRepository;
import com.pa2.milk.api.service.AdministradorService;
import com.pa2.milk.api.service.CredencialService;

@RestController
@RequestMapping(value = "/administrador")
@CrossOrigin(origins = "*")
public class AdministradorController {

	private static final Logger log = LoggerFactory.getLogger(AdministradorController.class);

	@Autowired
	private AdministradorService administradorService;

	@Autowired
	private CredencialService credencialService;

	@Autowired
	private AdministradorRepository administradorRepository;

	@PostMapping
	public ResponseEntity<Response<CadastroClienteDto>> cadastrarAdministrador(
			@Valid @RequestBody CadastroClienteDto clienteDto, BindingResult result) throws NoSuchAlgorithmException {

		log.info("Cadastrando Administrador:{}", clienteDto.toString());

		Response<CadastroClienteDto> response = new Response<CadastroClienteDto>();

		validarDadosExistentes(clienteDto, result);

		Administrador administrador = this.converterDtoParaAdministrador(clienteDto);

		Credencial credencial = this.converterDtoParaCredencial(clienteDto, result);

		if (result.hasErrors()) {

			log.error("Erro validando dados do cadastro Administrador: {}", result.getAllErrors());

			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.administradorService.salvar(administrador);
		credencial.setUsuario(administrador);
		this.credencialService.salvar(credencial);

		response.setData(this.converterCadastroClienteDto(credencial));

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Administrador>> buscarAdministradorPorId(@PathVariable("id") Integer id) {

		log.info("Buscar Administrador por Id");

		Response<Administrador> response = new Response<Administrador>();

		Optional<Administrador> administrador = this.administradorService
				.buscarPorTipoPerfilUsuarioandID(EnumTipoPerfilUsuario.ROLE_ADMINISTRADOR, id);

		if (!administrador.isPresent()) {
			log.info("Administrador não encontrado");
			response.getErros().add("Administrador	 não encontrado");
			ResponseEntity.badRequest().body(response);
		}

		response.setData(administrador.get());

		return ResponseEntity.ok(response);
	}

//@PutMapping(value = "{id}")
//public ResponseEntity<Response<Administrador>> atualizarAdministrador(@PathVariable("id") Integer id,
//		@Valid @RequestBody Administrador administrador, BindingResult result) throws NoSuchAlgorithmException {
//
//	log.info("Atualizando o Administrador:{}", administrador.toString());
//
//	Response<Administrador> response = new Response<Administrador>();
//
//	Administrador administrador1 = this.administradorService.buscarPorTipoPerfilUsuarioandID(EnumTipoPerfilUsuario.ROLE_ADMINISTRADOR, id);
//
//	response.setData(Optional.ofNullable(administrador1));
//
//	verificarResposta(response);
//
//	this.atualizarDadosAdministrador(administrador1, administrador, result);
//
//	if (result.hasErrors()) {
//		log.error("Erro validando lancamento:{}", result.getAllErrors());
//
//		result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
//
//		return ResponseEntity.badRequest().body(response);
//	}
//
//	this.administradorService.salvar(administrador1);
//
//	return ResponseEntity.ok(response);
//
//}

	private Administrador converterDtoParaAdministrador(CadastroClienteDto clienteDto) {

		Usuario adm = new Administrador();
		adm.setCpf(clienteDto.getCpf());
		adm.setEmail(clienteDto.getEmail());
		adm.setNome(clienteDto.getNome());
		adm.setCodigoTipoPerfilUsuario(EnumTipoPerfilUsuario.ROLE_ADMINISTRADOR);
		return (Administrador) adm;
	}

	private void validarDadosExistentes(CadastroClienteDto clienteDto, BindingResult result) {

		this.administradorService.buscarPorCpf(clienteDto.getCpf())
				.ifPresent(cli -> result.addError(new ObjectError("administrador", "Administrador já existente")));

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
	public List<Administrador> listarAdministradores() {
		List<Administrador> administrador = this.administradorRepository
				.findByCodigoTipoPerfilUsuario(EnumTipoPerfilUsuario.ROLE_ADMINISTRADOR.getCodigo());
		return administrador;
	}

	private void atualizarDadosAdministrador(Administrador administrador, Administrador administrador2,
			BindingResult result) throws NoSuchAlgorithmException {

		administrador.setNome(administrador2.getNome());

		if (!administrador.getEmail().equals(administrador2.getEmail())) {

			this.administradorService.buscarPorEmail(administrador2.getEmail())
					.ifPresent(clien -> result.addError(new ObjectError("email", "Email já exitente.")));
			administrador.setEmail(administrador2.getEmail());
		}

		if (!administrador.getCpf().equals(administrador2.getCpf())) {

			this.administradorService.buscarPorCpf(administrador2.getCpf())
					.ifPresent(clien -> result.addError(new ObjectError("cpf", "CPF já existente.")));
			administrador.setCpf(administrador2.getCpf());
		}

	}

//@DeleteMapping(value = "{id}")
//public ResponseEntity<Response<Administrador>> deletarAdministrador(@PathVariable("id") Integer id) {
//
//	log.info("Removendo Administrador: {}", id);
//
//	Response<Administrador> response = new Response<Administrador>();
//
//	Administrador administrador = this.adService.buscarPorTipoPerfilUsuarioandID(EnumTipoPerfilUsuario.ROLE_ADMINISTRADOR, id);
//
//	response.setData(Optional.ofNullable(administrador));
//
//	verificarResposta(response);
//
//	this.administradorService.remover(id);
//
//	return ResponseEntity.ok(response);
//}																			

}
