package service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.pa2.milk.api.model.Cliente;
import com.pa2.milk.api.model.Credencial;
import com.pa2.milk.api.model.Fazenda;
import com.pa2.milk.api.model.Solicitacao;
import com.pa2.milk.api.service.CredencialService;;


@RunWith(MockitoJUnitRunner.class)
public class CredencialServicoTest {
	
	@Mock
	private Credencial credencial;
	
	@Mock
	private CredencialService credencialService;
	
	private static final String TEL1 = "84996074563";

	private static final String TEL2 = "84996074575";
	
	private static final String USERNAME= "lucas12";
	
	private static final String SENHA = "12345678l" ;

	List<Fazenda> FAZENDA = new ArrayList<Fazenda>();

	List<Solicitacao> SOLICITACAO = new ArrayList<Solicitacao>();
	
	Cliente cliente = new Cliente(TEL1,TEL2,FAZENDA,SOLICITACAO);
	
	@Before
	public void init() {
		credencial = new Credencial(USERNAME,SENHA,cliente);
	}
	
	/*@After	
	public void after() {
		//Mockito.doCallRealMethod().when(clienteservice).);
		//Não foi possivel identific como apagar o credencial
		
	}*/
	
	@Test
	public void SalvarCredencial() {
		
		credencialService.salvar(credencial);
		
		assertTrue("cadastro" + credencial + "com sucesso", true);
	}
	
	@Test
	public void SalvarCredencialSemUsarname() {
		credencial = new Credencial(null,SENHA,cliente);
		credencialService.salvar(credencial);
		
		assertFalse("cadastro" + credencial + "com Falha", false);
	}
	
	@Test
	public void SalvarCredencialSemSenha() {
		credencial = new Credencial(USERNAME,null,cliente);
		credencialService.salvar(credencial);
		
		assertFalse("cadastro" + credencial + "com Falha", false);
	}
	
	@Test
	public void SalvarCredencialComSenhaMenorQue8Digitos() {
		credencial = new Credencial(USERNAME,"123",cliente);
		credencialService.salvar(credencial);
		
		assertFalse("cadastro" + credencial + "com Falha", false);
	}
	
	@Test
	public void SalvarCredencialSemCliente() {
		credencial = new Credencial(USERNAME,SENHA,null);
		credencialService.salvar(credencial);
		
		assertFalse("cadastro" + credencial + "com Falha", false);
	}


}
