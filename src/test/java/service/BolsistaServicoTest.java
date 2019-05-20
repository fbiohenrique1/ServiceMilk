package service;








import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.pa2.milk.api.model.Bolsista;
import com.pa2.milk.api.model.enums.EnumTipoPerfilUsuario;
import com.pa2.milk.api.repository.BolsistaRepository;
import com.pa2.milk.api.service.BolsistaService;

@RunWith(MockitoJUnitRunner.class)
public class BolsistaServicoTest {
	
	  @Mock
	  private BolsistaService bolsistaservice;
	  
	  @Mock
	  private BolsistaRepository bolsistarepository;
	  
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
	  
	   
	  @Test
	  public void salvarBolsistaSemNome() {
		  Bolsista bolsista = new Bolsista(EMAIL,null,CPF,PERFIL); 
		  
		  assertFalse("cadastro" + bolsista + "com falha", false);
		    
	  }
	  
	  @Test
	  public void salvarBolsistaSemEmail() {
		  Bolsista bolsista = new Bolsista(null,NOME,CPF,PERFIL);  
		  
		  assertFalse("cadastro" + bolsista + "com falha", false);
		  
		
	  }
	  
	  @Test
	  public void salvarBolsistaSemCpf() {
		  Bolsista bolsista = new Bolsista(EMAIL,NOME,null,PERFIL);  

		  assertFalse("cadastro" + bolsista + "com falha", false);
		   
	  }
	  
	  @Test
	  public void salvarBolsistaSemTipoDePerfil() {
		  Bolsista bolsista = new Bolsista(EMAIL,NOME,CPF,null);  
		  
		  assertFalse("cadastro" + bolsista + "com falha", false);
		  
	  }
	  
	  /*
	  @Test
	  public void buscarBosistaPorEmail() {
		  Bolsista bolsista2 = new Bolsista(EMAIL,NOME,CPF,PERFIL);
		  
		  bolsistarepository.save(bolsista2);
		  
		  Optional<Bolsista> bolsista = this.bolsistaservice.buscarPorCpf(bolsista2.getCpf());
		  
		  assertTrue(bolsista.isPresent());
		  }
		 */
	  
	  
}
