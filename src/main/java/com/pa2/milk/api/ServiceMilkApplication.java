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

import com.pa2.milk.api.model.Cliente;
import com.pa2.milk.api.model.Credencial;
import com.pa2.milk.api.model.Usuario;
import com.pa2.milk.api.model.enums.TipoPerfilUsuario;
import com.pa2.milk.api.repository.ClienteRepository;

@SpringBootApplication
public class ServiceMilkApplication {

	@Autowired
	private ClienteRepository clienteRepositorio;
	
	public static void main(String[] args) {
		SpringApplication.run(ServiceMilkApplication.class, args);
	}

	/*
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			
			Cliente usuario = new Cliente();
			usuario.setId(100);
			usuario.setEmail("usuario@email.com");
			usuario.setTipoPerfilUsuario(TipoPerfilUsuario.ROLE_CLIENTE);
			usuario.setNome("Pedro Fernandes");
			usuario.setCpf("65750205002");
			List<String> t = new ArrayList<String>();
			t.add("43242344");
			usuario.setTelefones(t);
			
			Credencial c = new Credencial();
			c.setId(2);
			c.setUsername("pedrohnf688");
			c.setSenha(PasswordUtils.gerarBCrypt("fernandes"));
			usuario.setCredencial(c);
			
			this.clienteRepositorio.save((Cliente) usuario);
			
//			Usuario admin = new Usuario();
//			admin.setEmail("admin@email.com");
//			admin.setPerfil(PerfilEnum.ROLE_ADMIN);
//			admin.setSenha(SenhasUtils.gerarBCrypt("123456"));
//			this.usuarioRepositorio.save(admin);
		};	
	}
	
*/
	
}
