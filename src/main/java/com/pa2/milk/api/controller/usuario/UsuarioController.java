package com.pa2.milk.api.controller.usuario;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pa2.milk.api.model.usuario.Usuario;
import com.pa2.milk.api.model.usuario.cliente.Cliente;
import com.pa2.milk.api.model.usuario.enums.TipoPerfilUsuario;
import com.pa2.milk.api.repository.usuario.UsuarioRepository;
import com.pa2.milk.api.response.Response;
//import com.pa2.milk.api.senhas.PasswordUtils;
import com.pa2.milk.api.service.usuario.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioRepository usuarioRepositorio;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
//	@RequestMapping(method = RequestMethod.GET)
	@GetMapping
	public String findById() {
		return "REST está funcionando123";
	}
	
	@PostMapping
	public ResponseEntity<Response<Usuario>> cadastrarCliente(@Valid @RequestBody Usuario usuario,
			BindingResult result) throws NoSuchAlgorithmException {
		
		log.info("Cadastrando Usuario:{}",usuario.toString());
		
		Response<Usuario> response = new Response<Usuario>();
	
		Usuario user = new Cliente();
		user.setNome(usuario.getNome());
		user.setEmail(usuario.getEmail());
		user.setTipoPerfilUsuario(TipoPerfilUsuario.ROLE_CLIENTE);
		user.setCpf(usuario.getCpf());
		user.getCredencial().setUsername(usuario.getCredencial().getUsername());
//		user.getCredencial().setSenha(PasswordUtils.gerarBCrypt(usuario.getCredencial().getSenha()));
//		
		if(result.hasErrors()) {
            
			log.error("Erro validando dados do cadastro Cliente: {}",result.getAllErrors());
            
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
	
		usuarioService.getClienteRepository().salvar((Cliente) user);
		
		return ResponseEntity.ok(response);
	}
		
}
