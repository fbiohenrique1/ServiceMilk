package com.pa2.milk.api.controller.usuario;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.beans.factory.annotation.Value;

import com.pa2.milk.api.model.usuario.Usuario;
import com.pa2.milk.api.model.usuario.cliente.Cliente;
import com.pa2.milk.api.model.usuario.enums.TipoPerfilUsuario;
import com.pa2.milk.api.repository.usuario.UsuarioRepository;
import com.pa2.milk.api.response.Response;
//import com.pa2.milk.api.senhas.PasswordUtils;
import com.pa2.milk.api.service.usuario.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioRepository usuarioRepositorio;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	@GetMapping(value="hello")
	public String findById() {
		return "REST está funcionando123";
	}
	
	@PostMapping
	public ResponseEntity<Response<Cliente>> cadastrarCliente(@Valid @RequestBody Cliente cliente,
			BindingResult result) throws NoSuchAlgorithmException {
		
		log.info("Cadastrando Usuario:{}",cliente.toString());
		
		Response<Cliente> response = new Response<Cliente>();
	
		Usuario user = new Cliente();
		user.setNome(cliente.getNome());
		user.setEmail(cliente.getEmail());
		user.setTipoPerfilUsuario(TipoPerfilUsuario.ROLE_CLIENTE);
		user.setCpf(cliente.getCpf());
		user.setTelefone(cliente.getTelefone());
		
		if(result.hasErrors()) {
            
			log.error("Erro validando dados do cadastro Cliente: {}",result.getAllErrors());
            
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
	
		usuarioService.getClienteRepository().salvar((Cliente) user);
		
		return ResponseEntity.ok(response);
	}
	
	
//	@GetMapping(value="{id}")
//	public ResponseEntity<Response<Cliente>> listarClientePorId(@PathVariable("id") Integer id){
//	
//		log.info("Buscar Cliente por Id");
//		
//		Response<Cliente> response = new Response<Cliente>();
//		
//    	Optional<Cliente> cliente = this.usuarioService.getClienteRepository().buscarPorId(id);
//    	
//    	if(!cliente.isPresent()) {
//    		log.info("Cliente não encontrado");
//    		
//    		response.getErros().add("Cliente não encontrado para o Id:" + id);
//    		
//    		return ResponseEntity.badRequest().body(response);
//    	}
//		response.setData(cliente);
//    	
//		return ResponseEntity.ok(response);
//	}
//		
//	@PutMapping(value="{id}")	
//	public ResponseEntity<Response<Cliente>> atualizarCliente(@PathVariable("id") Integer id, 
//			@Valid @RequestBody Cliente cliente, BindingResult result) throws NoSuchAlgorithmException {
//		
//		log.info("Atualizando o Cliente:{}",cliente.toString());
//		
//		Response<Cliente> response = new Response<Cliente>();
//		
//		Optional<Cliente> cliente1 = this.usuarioService.getClienteRepository().buscarPorId(id);
//		
//		
//		if(!cliente1.isPresent()) {
//			result.addError(new ObjectError("cliente", "Cliente não encontrado."));
//			response.getErros().add("Cliente não encontrado para o Id:" + id);
//			
//			return ResponseEntity.notFound().build();
//		}
//		
//		
//		this.atualizarDadosCliente(cliente1.get(), cliente, result);
//		
//		if(result.hasErrors()) {
//			log.error("Erro validando lancamento:{}", result.getAllErrors());
//			
//			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
//			
//			return ResponseEntity.badRequest().body(response);
//		}
//		
//		usuarioService.getClienteRepository().salvar(cliente1.get());
//	
//		response.setData2(this.converterFuncionarioDto(cliente1.get()));
//		
//		return ResponseEntity.ok(response);
//		
//	}
//	
//	
//	@DeleteMapping(value="{id}")
//	public ResponseEntity<Response<Cliente>> deletarCliente(@PathVariable("id") Integer id) {
//			
//		log.info("Removendo Cliente: {}", id);
//		
//		Response<Cliente> response = new Response<Cliente>();
//		
//		Optional<Cliente> cliente = this.usuarioService.getClienteRepository().buscarPorId(id);
//		
//		if(!cliente.isPresent()) {
//			log.info("Erro ao remover devido ao lancamento Id: {} ser inválido.", id);
//			response.getErros().add("Erro ao remover lancamento. Resgistro não para o Id:" + id);
//			return ResponseEntity.badRequest().body(response);
//		}
//		
//		this.usuarioService.getClienteRepository().remover(id);
//		
//		return ResponseEntity.ok(response);
//	}
//
//	
//	@GetMapping
//	public ResponseEntity<List<Cliente>> listarClientes(){
//	
//		
//		//List<Usuario> listarClientes = this.usuarioRepositorio.findAll();
//		
//		
//		return null;
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	private void atualizarDadosCliente(Cliente cliente, Cliente cliente2, BindingResult result) 
//			  throws NoSuchAlgorithmException {
//			
//		cliente.setNome(cliente2.getNome());
//			
//			if(!cliente.getEmail().equals(cliente2.getEmail())) {
//				this.usuarioService.getClienteRepository().buscarPorEmail(cliente2.getEmail())
//				    .ifPresent(clien -> result.addError(new ObjectError("email","Email já exitente.")));
//				cliente.setEmail(cliente2.getEmail());
//			}
//					
//			if(!cliente.getCpf().equals(cliente2.getEmail())) {
//				this.usuarioService.getClienteRepository().buscarPorCpf(cliente2.getCpf())
//				     .ifPresent(clien -> result.addError(new ObjectError("cpf", "CPF já existente.")));
//				cliente.setCpf(cliente2.getCpf());
//			}
//			
//		}
//
//	
//   private Cliente converterFuncionarioDto(Cliente cliente1) {
//		
//	   Cliente funcionarioDto = new Cliente();
//		
//		funcionarioDto.setId(cliente1.getId());
//		funcionarioDto.setEmail(cliente1.getEmail());
//		funcionarioDto.setNome(cliente1.getNome());
//		funcionarioDto.setCpf(cliente1.getCpf());
//		funcionarioDto.setTelefone(cliente1.getTelefone());
//		
//		return funcionarioDto;
//			
//   }
   
	
}
	