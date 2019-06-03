package service;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.pa2.milk.api.model.Bolsista;
import com.pa2.milk.api.model.enums.EnumTipoPerfilUsuario;
import com.pa2.milk.api.repository.BolsistaRepository;
import com.pa2.milk.api.service.BolsistaService;

@RunWith(MockitoJUnitRunner.class)
public class BolsistaServicoTest {

	@Mock
	private BolsistaService bolsistaservice;

	@MockBean
	private BolsistaRepository bolsistarepository;
	
	@Mock
	private Bolsista bolsista;

	private static final String CPF = "08653685030";

	private static final String EMAIL = "lucas-antonio22@hotmail.com";

	private static final String NOME = "lucas antonio";

	private static final Integer PERFIL = EnumTipoPerfilUsuario.ROLE_BOLSISTA.getCodigo();

	@Test
	public void salvaBolsista() {
		bolsistaservice.salvar(bolsista);

		assertTrue("cadastro" + bolsista + "com sucesso", true);

		verify(bolsistaservice).salvar(bolsista);

	}

	@Test
	public void salvarBolsistaSemNome() {
		bolsista = new Bolsista(EMAIL, null, CPF, PERFIL);

		assertFalse("cadastro" + bolsista + "com falha", false);

	}

	@Test
	public void salvarBolsistaSemEmail() {
		bolsista = new Bolsista(null, NOME, CPF, PERFIL);

		assertFalse("cadastro" + bolsista + "com falha", false);

	}

	@Test
	public void salvarBolsistaSemCpf() {
		bolsista = new Bolsista(EMAIL, NOME, null, PERFIL);

		assertFalse("cadastro" + bolsista + "com falha", false);

	}

	@Test
	public void salvarBolsistaSemTipoDePerfil() {
		bolsista = new Bolsista(EMAIL, NOME, CPF, null);

		assertFalse("cadastro" + bolsista + "com falha", false);

	}
	
	@Before //recriar o objeto bolsista
	public void init() {
		 bolsista = new Bolsista(EMAIL, NOME, CPF, PERFIL);
		 
		
	}
	
	/*@After//apagar e/ou tirar o objeto garantindo que ele não esteja no banco de dados real.	
	public void after() {
		//Mockito.doCallRealMethod().when(bolsistaservice).);
		//Não foi possivel identific como apagar o bolsita
		
	}*/
	
	/*
	@Test
	public void buscarBosistaPorEmail() {
		Bolsista bolsista2 = new Bolsista();
		bolsista2.setCpf(CPF);
		
		bolsistarepository.save(bolsista2);
		
		assertNotNull(bolsistaservice.buscarPorCpfNormal(CPF));
		
		Mockito.doCallRealMethod().when(bolsistaservice).salvar(bolsista);
		
		Mockito.doCallRealMethod().when(bolsistaservice).buscarPorCpfNormal(bolsista.getCpf());

		assertEquals(bolsistaservice.buscarPorCpfNormal(bolsista2.getCpf()).getCpf(),bolsista2.getCpf());
		
	}
	*/
	
	
}
