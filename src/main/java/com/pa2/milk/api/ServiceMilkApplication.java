package com.pa2.milk.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.pa2.milk.api.helper.PasswordUtils;
import com.pa2.milk.api.model.Cliente;
import com.pa2.milk.api.model.Credencial;
import com.pa2.milk.api.model.Usuario;
import com.pa2.milk.api.model.enums.TipoPerfilUsuario;
import com.pa2.milk.api.repository.ClienteRepository;
import com.pa2.milk.api.repository.CredencialRepository;

@SpringBootApplication
public class ServiceMilkApplication {

	@Autowired
	private ClienteRepository clienteRepositorio;
	
	@Autowired
	private CredencialRepository credencialRepositorio;
	
	public static void main(String[] args) {
		SpringApplication.run(ServiceMilkApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			
			Usuario usuario = new Cliente();
			usuario.setEmail("usuario@email.com");
			usuario.setTipoPerfilUsuario(TipoPerfilUsuario.ROLE_CLIENTE);
			usuario.setNome("Pedro Fernandes");
			usuario.setCpf("65750205002");
			List<String> t = new ArrayList<String>();
			t.add("43242344");
			((Cliente) usuario).setTelefones(t);
			
			Credencial c = new Credencial();
			c.setId(usuario.getId());
			c.setUsername("pedrohnf688");
			c.setSenha(PasswordUtils.gerarBCrypt("pedrohnf"));
		    c.setUsuario(usuario);
		    
			this.credencialRepositorio.save(c);
			
		};	
	}


}
