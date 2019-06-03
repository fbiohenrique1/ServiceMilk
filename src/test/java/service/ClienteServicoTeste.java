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
import org.springframework.boot.test.mock.mockito.MockBean;

import com.pa2.milk.api.model.Cliente;
import com.pa2.milk.api.model.Fazenda;
import com.pa2.milk.api.model.Solicitacao;
import com.pa2.milk.api.repository.ClienteRepository;
import com.pa2.milk.api.service.ClienteService;;

@RunWith(MockitoJUnitRunner.class)
public class ClienteServicoTeste {

	@Mock
	private ClienteService clienteService;

	@MockBean
	private ClienteRepository clienterepository;
	
	@Mock
	private Cliente cliente;
	
	private static final String TEL1 = "84996074563";

	private static final String TEL2 = "84996074575";

	List<Fazenda> FAZENDA = new ArrayList<Fazenda>();

	List<Solicitacao> SOLICITACAO = new ArrayList<Solicitacao>();
	
	@Before
	public void init() {
		cliente = new Cliente(TEL1,TEL2,FAZENDA,SOLICITACAO);
	}
	
	/*@After	
	public void after() {
		//Mockito.doCallRealMethod().when(clienteservice).);
		//Não foi possivel identific como apagar o cliente
		
	}*/
	
	@Test
	public void salvarCliente() {
		clienteService.salvar(cliente);
		
		assertTrue("cadastro" + cliente + "com sucesso", true);
		
	}
	
	@Test
	public void salvarClienteSemTelefone1() {
		cliente = new Cliente(null,TEL2,FAZENDA,SOLICITACAO);
		clienteService.salvar(cliente);
		
		assertFalse("cadastro" + cliente + "com falha", false);
		
	}
	
	@Test
	public void salvarClienteSemTelefone2() {
		cliente = new Cliente(TEL1,null,FAZENDA,SOLICITACAO);
		clienteService.salvar(cliente);
		
		assertFalse("cadastro" + cliente + "com falha", false);
		
	}
	
	@Test
	public void salvarClienteComTelefoneErrado() {
		cliente = new Cliente(TEL1,"lucas",FAZENDA,SOLICITACAO);
		clienteService.salvar(cliente);
		
		assertFalse("cadastro" + cliente + "com falha", false);
		
	}
}
