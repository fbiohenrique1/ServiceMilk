package com.pa2.milk.api.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.pa2.milk.api.model.Administrador;
import com.pa2.milk.api.model.Credencial;
import com.pa2.milk.api.model.Usuario;
import com.pa2.milk.api.model.enums.EnumTipoPerfilUsuario;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AdministradorRepositoryTest {
	
	@Autowired
	private AdministradorRepository administradorRepository;
	
	@Autowired 
	private CredencialRepository credencialRepository; 
	
	private static final String CPF = "61047348047";
	
	private static final String EMAIL = "mariairis@ufrn.com";
	
	@Before
	public void setUp() throws Exception{
		Credencial credencial = new Credencial();

		credencial.setUsername("maria");
		credencial.setSenha("789101");
		credencial.setId(1);

		Usuario administrador = new Administrador();

		administrador.setId(1);
		administrador.setNome("maria");
		administrador.setCpf(CPF);
		administrador.setEmail(EMAIL);
		administrador.setCodigoTipoPerfilUsuario(EnumTipoPerfilUsuario.ROLE_ADMINISTRADOR);

		credencial.setUsuario(administrador);

		//this.administradorRepository.save((Administrador) administrador);
		this.credencialRepository.save(credencial);

		Assertions.assertThat(administrador.getId()).isNotNull();
		Assertions.assertThat(administrador.getNome()).isEqualTo("maria");
		Assertions.assertThat(administrador.getEmail()).isEqualTo(EMAIL);
		Assertions.assertThat(administrador.getCpf()).isEqualTo(CPF);
		Assertions.assertThat(administrador.getCodigoTipoPerfilUsuario()).isEqualTo(EnumTipoPerfilUsuario.ROLE_ADMINISTRADOR);

		Assertions.assertThat(credencial.getId()).isNotNull();
		Assertions.assertThat(credencial.getSenha()).isEqualTo("789101");
		Assertions.assertThat(credencial.getUsername()).isEqualTo("maria");
		Assertions.assertThat(credencial.getUsuario()).isNotNull();
	}
	
	@Test 
	public void procurarAdministradorComCpf(){
		Administrador a = this.administradorRepository.findByEmail(EMAIL);
		assertEquals(EMAIL, a.getEmail());
	}
	
	@Test
	public void procurarAdministradorComEmaileCpfvalido() {
		Administrador a = this.administradorRepository.findByCpfOrEmail(CPF, EMAIL);
		assertNotNull(a);
	}

	@Test
	public void procurarAdministradorComEmaileCpfInvalido() {
		Administrador a = this.administradorRepository.findByCpfOrEmail("18464565456456465", EMAIL);
		assertNotNull(a);
	}
	
	@Test
	public void procurarAdministradorComEmailInvalidoeCpf() {
		Administrador a = this.administradorRepository.findByCpfOrEmail(CPF, "maria");
		assertNotNull(a);
	}
	
	/*@Test
	public void procurarAdministradorCodigoDoPerfil() {
		List<Administrador> a = this.administradorRepository
				.findByCodigoTipoPerfilUsuario(EnumTipoPerfilUsuario.ROLE_ADMINISTRADOR.getCodigo());
		for (int i = 0; i < a.size(); i++) {
			assertEquals(EnumTipoPerfilUsuario.ROLE_ADMINISTRADOR.getCodigo(), a.get(i).getCodigoTipoPerfilUsuario());
		}	

	}*/
}
