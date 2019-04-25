package com.pa2.milk.api.repository;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.pa2.milk.api.model.Bolsista;
import com.pa2.milk.api.model.Cliente;
import com.pa2.milk.api.model.Credencial;
import com.pa2.milk.api.model.Usuario;
import com.pa2.milk.api.model.enums.EnumTipoPerfilUsuario;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ClienteRepositoryTest {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private CredencialRepository credencialRepository;

	private static final String CPF = "35463259070";
	private static final String EMAIL = "teste@hotmail.com";
	private List<String> telefone = new ArrayList<>();
	
	private  String tel = "99897867";
	
	@Before	
		public void setUp() throws Exception {
		Credencial credencial = new Credencial();
	
		credencial.setUsername("Eric");
		credencial.setSenha("654321");
		credencial.setId(1);

		
		
		Usuario cliente = new Cliente();
		
		try {
			this.telefone.add(tel);
		} catch(NullPointerException e) {
			e.printStackTrace();
		}
			
		cliente.setId(1);
		cliente.setNome("Eric");
		cliente.setCpf(CPF);
		cliente.setEmail(EMAIL);
		cliente.setCodigoTipoPerfilUsuario(EnumTipoPerfilUsuario.ROLE_CLIENTE);	
		((Cliente)cliente).setTelefones(telefone);
		
	
		credencial.setUsuario(cliente);
		
		this.credencialRepository.save(credencial);
		
		
		Assertions.assertThat(cliente.getId()).isNotNull();
		Assertions.assertThat(cliente.getNome()).isEqualTo("Eric");
		Assertions.assertThat(cliente.getCpf()).isEqualTo(CPF);
		Assertions.assertThat(cliente.getEmail()).isEqualTo(EMAIL);
		assertArrayEquals(((Cliente)cliente).getTelefones().toArray(), telefone.toArray());
		Assertions.assertThat(cliente.getCodigoTipoPerfilUsuario()).isEqualTo(EnumTipoPerfilUsuario.ROLE_CLIENTE);

		
		Assertions.assertThat(credencial.getId()).isNotNull();
		Assertions.assertThat(credencial.getSenha()).isEqualTo("654321");
		Assertions.assertThat(credencial.getUsername()).isEqualTo("Eric");
		Assertions.assertThat(credencial.getUsuario()).isNotNull();

		
	}
	
	
	
	@Test
	public void procurarClienteComCpf() {
		Cliente c = this.clienteRepository.findByCpf(CPF);
		assertEquals(CPF, c.getCpf());
	}
	
	
	@Test
	public void procurarClienteComEmail() {
		Cliente c = this.clienteRepository.findByEmail(EMAIL);
		assertEquals(EMAIL, c.getEmail());
	}
	
	@Test
	public void procurarClienteComEmaileCpfvalido() {
		Cliente c = this.clienteRepository.findByCpfOrEmail(CPF, EMAIL);
		assertNotNull(c);
	}

	@Test
	public void procurarClienteComEmaileCpfInvalido() {
		Cliente c = this.clienteRepository.findByCpfOrEmail("18464565456456465", EMAIL);
		assertNotNull(c);
	}

	@Test
	public void procurarClienteComEmailInvalidoeCpf() {
		Cliente c = this.clienteRepository.findByCpfOrEmail(CPF, "lucas");
		assertNotNull(c);
	}
}
