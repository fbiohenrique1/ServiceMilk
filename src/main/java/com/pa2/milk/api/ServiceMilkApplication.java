package com.pa2.milk.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.pa2.milk.api.helper.PasswordUtils;
import com.pa2.milk.api.model.Administrador;
import com.pa2.milk.api.model.Analise;
import com.pa2.milk.api.model.Bolsista;
import com.pa2.milk.api.model.Cliente;
import com.pa2.milk.api.model.Credencial;
import com.pa2.milk.api.model.Fazenda;
import com.pa2.milk.api.model.OrdemServico;
import com.pa2.milk.api.model.Solicitacao;
import com.pa2.milk.api.model.Usuario;
import com.pa2.milk.api.model.enums.EnumAnalisesSolicitadas;
import com.pa2.milk.api.model.enums.EnumLeite;
import com.pa2.milk.api.model.enums.EnumOrigemLeite;
import com.pa2.milk.api.model.enums.EnumProdutos;
import com.pa2.milk.api.model.enums.EnumStatusSolicitacao;
import com.pa2.milk.api.model.enums.EnumTipoPerfilUsuario;
import com.pa2.milk.api.repository.AdministradorRepository;
import com.pa2.milk.api.repository.AnaliseRepository;
import com.pa2.milk.api.repository.BolsistaRepository;
import com.pa2.milk.api.repository.ClienteRepository;
import com.pa2.milk.api.repository.CredencialRepository;
import com.pa2.milk.api.repository.FazendaRepository;
import com.pa2.milk.api.repository.OrdemServicoRepository;
import com.pa2.milk.api.repository.SolicitacaoRepository;
import com.pa2.milk.api.repository.UsuarioRepository;

@SpringBootApplication
public class ServiceMilkApplication {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private BolsistaRepository bolsistaRepository;

	@Autowired
	private AdministradorRepository administradorRepository;

	@Autowired
	private CredencialRepository credencialRepository;

	@Autowired
	private FazendaRepository fazendaRepository;

	@Autowired
	private SolicitacaoRepository solicitacaoRepository;

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	@Autowired
	private AnaliseRepository analiseRepository;

	public static void main(String[] args) {
		SpringApplication.run(ServiceMilkApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner() {
//		return args -> {
//
//			// Cliente
//			Usuario usuario = new Cliente();
//			usuario.setEmail("pedro@email.com");
//			usuario.setCodigoTipoPerfilUsuario(EnumTipoPerfilUsuario.ROLE_CLIENTE);
//			usuario.setNome("Pedro Fernandes");
//			usuario.setCpf("65750205002");
//			List<String> t = new ArrayList<String>();
//			t.add("43242344");
//			((Cliente) usuario).setTelefones(t);
//			Credencial c = new Credencial();
//			c.setId(usuario.getId());
//			c.setUsername("pedrohnf688");
//			c.setSenha(PasswordUtils.gerarBCrypt("pedrohnf"));
//			c.setUsuario(usuario);
//			this.credencialRepository.save(c);
//
//			// Bolsista
//			Usuario b = new Bolsista();
//			b.setEmail("fabio@email.com");
//			b.setCodigoTipoPerfilUsuario(EnumTipoPerfilUsuario.ROLE_BOLSISTA);
//			b.setNome("Fábio Henrique");
//			b.setCpf("13286865745");
//			Credencial cB = new Credencial();
//			cB.setUsername("fabio");
//			cB.setSenha(PasswordUtils.gerarBCrypt("fabio"));
//			cB.setUsuario(b);
//			this.credencialRepository.save(cB);
//
//			// Administrador
//			Usuario a = new Administrador();
//			a.setEmail("teste@email.com");
//			a.setCodigoTipoPerfilUsuario(EnumTipoPerfilUsuario.ROLE_ADMINISTRADOR);
//			a.setNome("Teste de sistema");
//			a.setCpf("11242520023");
//			Credencial cA = new Credencial();
//			cA.setUsername("teste");
//			cA.setSenha(PasswordUtils.gerarBCrypt("teste"));
//			cA.setUsuario(a);
//			this.credencialRepository.save(cA);
//
//			// Fazenda
//			Fazenda fazenda = new Fazenda();
//			fazenda.setNome("bananinha");
//			fazenda.setBairro("bairro");
//			fazenda.setCep("cep");
//			fazenda.setCidade("cidade");
//			fazenda.setCnpj("84339157000136");
//			fazenda.setEndereco("endereco");
//			fazenda.setNumero(211);
//			fazenda.setImagem("iamgem");
//			fazenda.setEstado("estado");
//			this.fazendaRepository.save(fazenda);
//
//			Fazenda fazenda1 = new Fazenda();
//			fazenda1.setNome("bananinha2");
//			fazenda1.setBairro("bairro2");
//			fazenda1.setCep("cep2");
//			fazenda1.setCidade("cidade2");
//			fazenda1.setCnpj("87996280000118");
//			fazenda1.setEndereco("endereco2");
//			fazenda1.setNumero(2112);
//			fazenda1.setImagem("iamgem2");
//			fazenda1.setEstado("estado2");
//			this.fazendaRepository.save(fazenda1);
//
//			// Adicionar id de cliente em uma fazenda
//			Optional<Usuario> cliente = this.usuarioRepository.findById(1);
//			((Cliente) cliente.get()).addFazenda(fazenda);
//			((Cliente) cliente.get()).addFazenda(fazenda1);
//			this.clienteRepository.save((Cliente) cliente.get());
//
//			// Solicitação
//			Solicitacao s = new Solicitacao();
//			s.setCliente((Cliente) usuario);
//			s.setFazenda(fazenda);
//			s.setStatus(EnumStatusSolicitacao.PENDENTE);
//			this.solicitacaoRepository.save(s);
//
//			// OrdemServiço
//			OrdemServico os = new OrdemServico();
//			os.setDataHora(new Date());
//			os.setSolicitacao(s);
//			os.setBolsista((Bolsista) b);
//			os.setValor(112.0);
//			this.ordemServicoRepository.save(os);
//
//			// Analise
//			Analise analise = new Analise();
//			analise.setEspecie("especie");
//			analise.setLeite(EnumLeite.PASTEURIZADO);
//			analise.setProdutos(EnumProdutos.SORO);
//			analise.setOrigemLeite(EnumOrigemLeite.TANQUE);
//			analise.setAnalisesSolicitadas(EnumAnalisesSolicitadas.CASEINA);
//			this.analiseRepository.save(analise);
//
//			// Adicionar id de solicitação em uma analise
//			Optional<Solicitacao> solicitacao = this.solicitacaoRepository.findById(1);
//			solicitacao.get().addAnalise(analise);
//			this.solicitacaoRepository.save(solicitacao.get());
//
//		};
//	}

}
