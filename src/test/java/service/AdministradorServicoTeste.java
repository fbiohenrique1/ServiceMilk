package service;

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

	private static final String EMAIL = "lucas-antonio22@hotmail.com";

	private static final String NOME = "lucas antonio";

	private static final Integer PERFIL = EnumTipoPerfilUsuario.ROLE_ADMINISTRADOR.getCodigo();

	
	
	@Test
	public void salvarAdministrador () {
		
		Administrador admin = new Administrador(EMAIL,NOME,CPF,PERFIL);
		
		adminservico.salvar(admin);
		
		assertTrue("cadastro" + admin + "com sucesso", true);
		
		verify(adminservico).salvar(admin);
		
		
		
	}
}
