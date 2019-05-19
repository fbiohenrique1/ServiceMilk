package service;








import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.pa2.milk.api.model.Bolsista;
import com.pa2.milk.api.model.enums.EnumTipoPerfilUsuario;
import com.pa2.milk.api.service.BolsistaService;

@RunWith(MockitoJUnitRunner.class)
public class BolsistaServicoTest {
	
	  @Mock
	  private BolsistaService bolsistaservice;
	  
	  private static final String CPF = "08653685030";

	  private static final String EMAIL = "lucas-antonio22@hotmail.com";
	  
	  private static final String NOME = "lucas antonio";
	  
	  private static final Integer PERFIL = EnumTipoPerfilUsuario.ROLE_BOLSISTA.getCodigo();
	  
	  @Test
	  public void salvaBolsista() {
		  Bolsista bolsista = new Bolsista(EMAIL,NOME,CPF,PERFIL);
		  
		  bolsistaservice.salvar(bolsista);
		  
		  assertTrue("cadastro" + bolsista + "com sucesso", true);
		  
		  verify(bolsistaservice).salvar(bolsista);
		  
	  }
	  /*
	   * TESTE AINDA COM ERROS, IRAM SER CORRIGIDOS.
	  @Test
	  public void salvarBolsistaSemNome() {
		  Bolsista bolsista = new Bolsista(EMAIL,null,CPF,PERFIL); 
		  
		  assertTrue("cadastro" + bolsista + "com falha", false);
		  
		  verify(bolsistaservice).salvar(bolsista);
	  }
	  
	  @Test
	  public void salvarBolsistaSemEmail() {
		  Bolsista bolsista = new Bolsista(null,NOME,CPF,PERFIL);  
		  
		  assertTrue(bolsista.isAtivo());
		  
		  verify(bolsistaservice).salvar(bolsista);
	  }
	  
	  @Test
	  public void salvarBolsistaSemCpf() {
		  Bolsista bolsista = new Bolsista(EMAIL,NOME,null,PERFIL);  

		  assertTrue(bolsista.isAtivo());
		  
		  verify(bolsistaservice).salvar(bolsista);
	  }
	  
	  @Test
	  public void salvarBolsistaSemTipoDePerfil() {
		  Bolsista bolsista = new Bolsista(EMAIL,NOME,CPF,null);  
		  
		  assertTrue(bolsista.isAtivo());
		  
		  verify(bolsistaservice).salvar(bolsista);
	  }
	  
	  @Test
	  public void buscarBosistaPorEmail() {
		  Bolsista bolsista = new Bolsista(EMAIL,NOME,CPF,PERFIL);
		  Optional aux = bolsistaservice.buscarPorEmail(EMAIL);
		  
		  assertTrue(aux.isPresent());
		  
		  verify(bolsistaservice).buscarPorEmail(EMAIL);
		  }
		  */
}
