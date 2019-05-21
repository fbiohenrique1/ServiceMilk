package service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.pa2.milk.api.model.Administrador;
import com.pa2.milk.api.model.enums.EnumTipoPerfilUsuario;
import com.pa2.milk.api.repository.AdministradorRepository;
import com.pa2.milk.api.service.AdministradorService;

@RunWith(MockitoJUnitRunner.class)
public class AdministradorServicoTeste {

	@Mock
	private AdministradorService adminservico;

	@Mock
	private AdministradorRepository adminrepository;

	private static final String CPF = "08653685030";
	private static final String CPF2 = "00000000000";
	private static final String CPF3 = "456";

	private static final String EMAIL = "lucas-antonio22@hotmail.com";

	private static final String EMAIL2 = "lucas";

	private static final String NOME = "lucas antonio";

	private static final Integer PERFIL = EnumTipoPerfilUsuario.ROLE_ADMINISTRADOR.getCodigo();

	@Test
	public void salvarAdministrador() {

		Administrador admin = new Administrador(EMAIL, NOME, CPF, PERFIL);

		adminservico.salvar(admin);

		assertTrue("cadastro" + admin + "com sucesso", true);

		verify(adminservico).salvar(admin);

	}

	@Test
	public void salvarAdministradorSemNome() {

		Administrador admin = new Administrador(EMAIL, null, CPF, PERFIL);
		assertFalse("cadatro" + admin + "com falha ", false);
	}

	@Test
	public void salvarAdministradorSemEmail() {

		Administrador admin = new Administrador(null, NOME, CPF, PERFIL);

		assertFalse("cadatro" + admin + "com falha ", false);

	}

	@Test
	public void salvarAdministradorEmail2() {

		Administrador admin = new Administrador(EMAIL2, NOME, CPF, PERFIL);

		assertFalse("cadatro" + admin + "com falha ", false);

	}

	@Test
	public void salvarAdministradorSemCPF() {

		Administrador admin = new Administrador(EMAIL, NOME, null, PERFIL);

		assertFalse("cadatro" + admin + "com falha ", false);
	}
	
	@Test
	public void salvarAdministradorCPF2() {

		Administrador admin = new Administrador(EMAIL, NOME, CPF2, PERFIL);

		assertFalse("cadatro" + admin + "com falha ", false);
	}
	@Test
	public void salvarAdministradorCPF3() {

		Administrador admin = new Administrador(EMAIL, NOME, CPF3, PERFIL);

		assertFalse("cadatro" + admin + "com falha ", false);
	}

	@Test
	public void salvarAdministradorSemPerfil() {

		Administrador admin = new Administrador(EMAIL, NOME, CPF, null);

		assertFalse("cadatro" + admin + "com falha ", false);
	}

}
