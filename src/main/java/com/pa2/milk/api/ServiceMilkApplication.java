package com.pa2.milk.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.pa2.milk.api.model.Administrador;
import com.pa2.milk.api.model.Bolsista;
import com.pa2.milk.api.model.Cliente;
import com.pa2.milk.api.model.Credencial;
import com.pa2.milk.api.model.Usuario;
import com.pa2.milk.api.model.enums.TipoPerfilUsuario;
import com.pa2.milk.api.repository.AdministradorRepository;
import com.pa2.milk.api.repository.BolsistaRepository;
import com.pa2.milk.api.repository.ClienteRepository;
import com.pa2.milk.api.repository.CredencialRepository;

@SpringBootApplication
public class ServiceMilkApplication {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private BolsistaRepository bolsistaRepository;

	@Autowired
	private AdministradorRepository administradorRepository;

	@Autowired
	private CredencialRepository credencialRepository;

	public static void main(String[] args) {
		SpringApplication.run(ServiceMilkApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {

			// Cliente
			Usuario usuario = new Cliente();
			usuario.setEmail("pedro@email.com");
			usuario.setTipoPerfilUsuario(TipoPerfilUsuario.ROLE_CLIENTE);
			usuario.setNome("Pedro Fernandes");
			usuario.setCpf("65750205002");
			List<String> t = new ArrayList<String>();
			t.add("43242344");
			((Cliente) usuario).setTelefones(t);

			Credencial c = new Credencial();
			c.setId(usuario.getId());
			c.setUsername("pedrohnf688");
			c.setSenha("pedrohnf");
			c.setUsuario(usuario);
			this.credencialRepository.save(c);

			// Bolsista
			Usuario b = new Bolsista();
			b.setEmail("fabio@email.com");
			b.setTipoPerfilUsuario(TipoPerfilUsuario.ROLE_BOLSISTA);
			b.setNome("Fábio Henrique");
			b.setCpf("13286865745");

			Credencial cB = new Credencial();
			cB.setUsername("fabio");
			cB.setSenha("fabio");
			cB.setUsuario(b);
			this.credencialRepository.save(cB);

			// Administrador
			Usuario a = new Administrador();
			a.setEmail("teste@email.com");
			a.setTipoPerfilUsuario(TipoPerfilUsuario.ROLE_ADMINISTRADOR);
			a.setNome("Teste de sistema");
			a.setCpf("11242520023");

			Credencial cA = new Credencial();
			cA.setUsername("teste");
			cA.setSenha("teste");
			cA.setUsuario(a);
			this.credencialRepository.save(cA);

		};
	}

}
