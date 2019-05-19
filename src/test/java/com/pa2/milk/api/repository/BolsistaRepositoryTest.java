package com.pa2.milk.api.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.pa2.milk.api.model.Bolsista;
import com.pa2.milk.api.model.Credencial;
import com.pa2.milk.api.model.Usuario;
import com.pa2.milk.api.model.enums.EnumTipoPerfilUsuario;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class BolsistaRepositoryTest {

	@Autowired
	private BolsistaRepository bolsistaRepository;

	@Autowired
	private CredencialRepository credencialReposistory;

	private static final String CPF = "78759173017";

	private static final String EMAIL = "lucasantonio@hotmail.com";

	@Before
	public void setUp() throws Exception {
		Credencial credencial = new Credencial();

		credencial.setUsername("lucas");
		credencial.setSenha("123456");
		credencial.setId(1);

		Bolsista usuario = new Bolsista();

		usuario.setId(1);
		usuario.setNome("lucas");
		usuario.setCpf(CPF);
		usuario.setEmail(EMAIL);
		usuario.setCodigoTipoPerfilUsuario(EnumTipoPerfilUsuario.ROLE_BOLSISTA);

		credencial.setUsuario(usuario);

		// this.bolsistaRepository.save((Bolsista) usuario);
		this.credencialReposistory.save(credencial);

		Assertions.assertThat(usuario.getId()).isNotNull();
		Assertions.assertThat(usuario.getNome()).isEqualTo("lucas");
		Assertions.assertThat(usuario.getEmail()).isEqualTo(EMAIL);
		Assertions.assertThat(usuario.getCpf()).isEqualTo(CPF);
		Assertions.assertThat(usuario.getCodigoTipoPerfilUsuario()).isEqualTo(EnumTipoPerfilUsuario.ROLE_BOLSISTA);

		Assertions.assertThat(credencial.getId()).isNotNull();
		Assertions.assertThat(credencial.getSenha()).isEqualTo("123456");
		Assertions.assertThat(credencial.getUsername()).isEqualTo("lucas");
		Assertions.assertThat(credencial.getUsuario()).isNotNull();

	}

	@Test
	public void procurarBolsistaComCpf() {
		Bolsista b = this.bolsistaRepository.findByCpf(CPF);
		assertEquals(CPF, b.getCpf());
	}

	@Test
	public void procurarBolsistaComEmail() {
		Bolsista b = this.bolsistaRepository.findByEmail(EMAIL);
		assertEquals(EMAIL, b.getEmail());
	}

	@Test
	public void procurarBolsistaComEmaileCpfvalido() {
		Bolsista b = this.bolsistaRepository.findByCpfOrEmail(CPF, EMAIL);
		assertNotNull(b);
	}

	@Test
	public void procurarBolsistaComEmaileCpfInvalido() {
		Bolsista b = this.bolsistaRepository.findByCpfOrEmail("18464565456456465", EMAIL);
		assertNotNull(b);
	}

	@Test
	public void procurarBolsistaComEmailInvalidoeCpf() {
		Bolsista b = this.bolsistaRepository.findByCpfOrEmail(CPF, "lucas");
		assertNotNull(b);
	}
	
	/*
	@Test
	public void procurarBolsistaCodigoDoPerfil() {
		List<Bolsista> b = this.bolsistaRepository
				.findByCodigoTipoPerfilUsuario(EnumTipoPerfilUsuario.ROLE_BOLSISTA.getCodigo());
		for (int i = 0; i < b.size(); i++) {
			assertEquals(EnumTipoPerfilUsuario.ROLE_BOLSISTA.getCodigo(), b.get(i).getCodigoTipoPerfilUsuario());
		}	

	}
	 */
}
