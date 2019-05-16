package com.pa2.milk.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.pa2.milk.api.model.Administrador;
import com.pa2.milk.api.model.Credencial;
import com.pa2.milk.api.model.Usuario;
import com.pa2.milk.api.model.dto.CadastroClienteDto;
import com.pa2.milk.api.model.enums.EnumTipoPerfilUsuario;
import com.pa2.milk.api.repository.CredencialRepository;
import com.pa2.milk.api.repository.UsuarioRepository;
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
	private CredencialRepository credencialRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
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

	@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
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

	@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
	@GetMapping
	public List<Administrador> listarAdministradores() {
		List<Administrador> administrador = this.administradorService
				.buscarPorTipoPerfilUsuario(EnumTipoPerfilUsuario.ROLE_ADMINISTRADOR);
		return administrador;
	}

	@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Response<Credencial>> deletarAdministrador(@PathVariable("id") Integer id) {

		log.info("Removendo Administrador: {}", id);

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

	@PreAuthorize("hasAnyRole('ADMINISTRADOR')")
	@PutMapping(value = "{id}")
	public ResponseEntity<Response<CadastroClienteDto>> atualizarBolsista(@PathVariable("id") Integer id,
			@Valid @RequestBody CadastroClienteDto adminDto, BindingResult result) throws NoSuchAlgorithmException {

		log.info("Atualizando o Bolsista:{}", adminDto.toString());

		Response<CadastroClienteDto> response = new Response<CadastroClienteDto>();

		Optional<Credencial> credencial = this.credencialService.buscarPorId(id);

		if (!credencial.isPresent()) {
			result.addError(new ObjectError("credencial", "Credencial não encontrada."));
		}

		this.atualizarDadosAdministrador(credencial.get(), adminDto, result);

		if (result.hasErrors()) {
			log.error("Erro validando a Credencial:{}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.credencialService.salvar(credencial.get());
		response.setData(this.converterCadastroClienteDto(credencial.get()));

		return ResponseEntity.ok(response);

	}

	private void atualizarDadosAdministrador(Credencial credencial, CadastroClienteDto adminDto, BindingResult result)
			throws NoSuchAlgorithmException {

		credencial.getUsuario().setNome(adminDto.getNome());

		if (!credencial.getUsuario().getEmail().equals(adminDto.getEmail())) {

			this.administradorService.buscarPorEmail(adminDto.getEmail())
					.ifPresent(clien -> result.addError(new ObjectError("email", "Email já exitente.")));
			credencial.getUsuario().setEmail(adminDto.getEmail());
		}

		if (!credencial.getUsuario().getCpf().equals(adminDto.getCpf())) {

			this.administradorService.buscarPorCpf(adminDto.getCpf())
					.ifPresent(clien -> result.addError(new ObjectError("cpf", "CPF já existente.")));
			credencial.getUsuario().setCpf(adminDto.getCpf());
		}

		if (adminDto.getUsername() != null || adminDto.getSenha() != null) {

			if (!credencial.getUsername().equals(adminDto.getUsername())) {

				this.credencialService.buscarPorUsername(adminDto.getUsername())
						.ifPresent(crede -> result.addError(new ObjectError("username", "Username já existente.")));
				credencial.setUsername(adminDto.getUsername());
			}

			credencial.setSenha(PasswordUtils.gerarBCrypt(adminDto.getSenha()));

		} else {
			credencial.setUsername(credencial.getUsername());
			credencial.setSenha(credencial.getSenha());

		}

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

}
