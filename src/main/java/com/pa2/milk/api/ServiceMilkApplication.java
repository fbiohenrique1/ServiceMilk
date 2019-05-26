package com.pa2.milk.api;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.pa2.milk.api.controller.EnviarEmail;
import com.pa2.milk.api.helper.PasswordUtils;
import com.pa2.milk.api.model.Administrador;
import com.pa2.milk.api.model.Amostra;
import com.pa2.milk.api.model.Analise;
import com.pa2.milk.api.model.Bolsista;
import com.pa2.milk.api.model.Cliente;
import com.pa2.milk.api.model.Credencial;
import com.pa2.milk.api.model.Fazenda;
import com.pa2.milk.api.model.OrdemServico;
import com.pa2.milk.api.model.Solicitacao;
import com.pa2.milk.api.model.Usuario;
import com.pa2.milk.api.model.enums.EnumAnalisesSolicitadas;
import com.pa2.milk.api.model.enums.EnumEspecie;
import com.pa2.milk.api.model.enums.EnumLeite;
import com.pa2.milk.api.model.enums.EnumOrigemLeite;
import com.pa2.milk.api.model.enums.EnumProdutos;
import com.pa2.milk.api.model.enums.EnumStatusSolicitacao;
import com.pa2.milk.api.model.enums.EnumTipoPerfilUsuario;
import com.pa2.milk.api.repository.AdministradorRepository;
import com.pa2.milk.api.repository.AmostraRepository;
import com.pa2.milk.api.repository.AnaliseRepository;
import com.pa2.milk.api.repository.BolsistaRepository;
import com.pa2.milk.api.repository.ClienteRepository;
import com.pa2.milk.api.repository.CredencialRepository;
import com.pa2.milk.api.repository.FazendaRepository;
import com.pa2.milk.api.repository.OrdemServicoRepository;
import com.pa2.milk.api.repository.SolicitacaoRepository;
import com.pa2.milk.api.repository.UsuarioRepository;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
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

	@Autowired
	private AmostraRepository amostraRepository;

	@Autowired
	private EnviarEmail enviar;
	
	public static void main(String[] args) {
		SpringApplication.run(ServiceMilkApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {

			// Cliente
			Usuario usuario = new Cliente();
			usuario.setEmail("pedro@email.com");
			usuario.setCodigoTipoPerfilUsuario(EnumTipoPerfilUsuario.ROLE_CLIENTE);
			usuario.setNome("Pedro Fernandes");
			usuario.setCpf("26302742099");
			((Cliente) usuario).setTelefone1("888888888");
			((Cliente) usuario).setTelefone2("999999999");
			Credencial c = new Credencial();
			c.setId(usuario.getId());
			c.setUsername("pedrohnf688");
			c.setSenha(PasswordUtils.gerarBCrypt("pedrohnf"));
			c.setUsuario(usuario);
			this.credencialRepository.save(c);

			// Bolsista
			Usuario b = new Bolsista();
			b.setEmail("fabio@email.com");
			b.setCodigoTipoPerfilUsuario(EnumTipoPerfilUsuario.ROLE_BOLSISTA);
			b.setNome("Fábio Henrique");
			b.setCpf("19056419072");
			Credencial cB = new Credencial();
			cB.setUsername("fabio");
			cB.setSenha(PasswordUtils.gerarBCrypt("fabio"));
			cB.setUsuario(b);
			this.credencialRepository.save(cB);

			// Administrador
			Usuario a = new Administrador();
			a.setEmail("teste@email.com");
			a.setCodigoTipoPerfilUsuario(EnumTipoPerfilUsuario.ROLE_ADMINISTRADOR);
			a.setNome("Cliente teste de sistema");
			a.setCpf("11242520023");
			Credencial cA = new Credencial();
			cA.setUsername("teste");
			cA.setSenha(PasswordUtils.gerarBCrypt("teste"));
			cA.setUsuario(a);
			this.credencialRepository.save(cA);

			// Fazenda
			Fazenda fazenda = new Fazenda();
			fazenda.setNome("Fazenda Monte Alegre");
			fazenda.setBairro("Iguaçu");
			fazenda.setCep("64965970");
			fazenda.setCidade("Avelino Lopes");
			fazenda.setCpfcnpj("07204356000196");
			fazenda.setEndereco("Avenida Sérgio Gama 105");
			fazenda.setNumero(211);
			fazenda.setImagem("imagem");
			fazenda.setEstado("Piauí");
			this.fazendaRepository.save(fazenda);

			Fazenda fazenda1 = new Fazenda();
			fazenda1.setNome("Chácara Pedacinho do Céu");
			fazenda1.setBairro("Aracuí");
			fazenda1.setCep("29365984");
			fazenda1.setCidade("Aracui");
			fazenda1.setCpfcnpj("66965755000139");
			fazenda1.setEndereco("Rodovia Fued Nemer, s/n");
			fazenda1.setNumero(2112);
			fazenda1.setImagem("imagem2");
			fazenda1.setEstado("Minas Gerais");
			this.fazendaRepository.save(fazenda1);

			// Adicionar id de cliente em uma fazenda
			Optional<Usuario> cliente = this.usuarioRepository.findById(1);
			((Cliente) cliente.get()).addFazenda(fazenda);
			((Cliente) cliente.get()).addFazenda(fazenda1);
			this.clienteRepository.save((Cliente) cliente.get());

			// Solicitação
			Solicitacao s = new Solicitacao();
			s.setCliente((Cliente) usuario);
			s.setFazenda(fazenda);
			s.setStatus(EnumStatusSolicitacao.PENDENTE);
			s.setDataCriada(Calendar.getInstance(TimeZone.getTimeZone("GMT-03:00")).getTime());
			this.solicitacaoRepository.save(s);

			// OrdemServiço
			OrdemServico os = new OrdemServico();
			os.setDataHora(new Date());
			os.setSolicitacao(s);
			os.setBolsista((Bolsista) b);
			os.setValor(112.0);
			this.ordemServicoRepository.save(os);

			// Analise 1
			Collection<EnumLeite> teste = new ArrayList<>();
			teste.add(EnumLeite.CRU);
			teste.add(EnumLeite.PASTEURIZADO);

			Collection<EnumProdutos> teste2 = new ArrayList<>();
			teste2.add(EnumProdutos.SORO);
			teste2.add(EnumProdutos.CREME_30_GORDURA);

			Collection<EnumOrigemLeite> teste3 = new ArrayList<>();
			teste3.add(EnumOrigemLeite.TANQUE_COLETIVO);
			teste3.add(EnumOrigemLeite.BALDE);

			Collection<EnumAnalisesSolicitadas> teste4 = new ArrayList<>();
			teste4.add(EnumAnalisesSolicitadas.ANALISES_FRAUDE);
			teste4.add(EnumAnalisesSolicitadas.CELULAS_SOMATICAS);

			Analise analise = new Analise();
			analise.setEspecie(EnumEspecie.BOVINO);
			analise.setLeite(EnumLeite.CRU);
			analise.setProdutos(teste2);
			analise.setOrigemLeite(EnumOrigemLeite.TETEIRA);
			analise.setAnalisesSolicitadas(teste4);
			analise.setQuantidadeAmostras(70);

			Analise analise2 = new Analise();
			analise2.setEspecie(EnumEspecie.OUVINO);
			analise2.setLeite(EnumLeite.CRU);
			analise2.setProdutos(teste2);
			analise2.setOrigemLeite(EnumOrigemLeite.TETEIRA);
			analise2.setAnalisesSolicitadas(teste4);
			analise2.setQuantidadeAmostras(60);
			analise2.setDescricao("analise 2");

			Analise analise3 = new Analise();
			analise3.setEspecie(EnumEspecie.CAPRINO);
			analise3.setLeite(EnumLeite.CRU);
			analise3.setProdutos(teste2);
			analise3.setOrigemLeite(EnumOrigemLeite.TETEIRA);
			analise3.setAnalisesSolicitadas(teste4);
			analise3.setQuantidadeAmostras(5);
			analise3.setDescricao("analise 3");
			// this.analiseRepository.save(analise);

			// Amostra 1
			Amostra amostra = new Amostra();
			amostra.setDataColeta(new Date());
			amostra.setNumeroAmostra(30);
			amostra.setObservacao("obs");
			amostra.setQrCode("qrCode");
//			this.amostraRepository.save(amostra);

			// Amostra 2
			Amostra amostra2 = new Amostra();
			amostra2.setDataColeta(new Date());
			amostra2.setNumeroAmostra(323);
			amostra2.setObservacao("obs2");
			amostra2.setQrCode("qrCode2");
//			this.amostraRepository.save(amostra2);

			// Adicionar id de solicitação em uma analise
			Optional<Solicitacao> solicitacao = this.solicitacaoRepository.findById(1);
			List<Analise> listaAnalises = new ArrayList<>();
			listaAnalises.add(analise);
			listaAnalises.add(analise2);
			listaAnalises.add(analise3);
			listaAnalises.stream().forEach(objAnalise -> solicitacao.get().addAnalise(objAnalise));
			// solicitacao.get().addAnalise(analise);
			// solicitacao.get().addAnalise(analise2);
			this.solicitacaoRepository.save(solicitacao.get());

//-------------------------------------------------------------(NAO FUNCIONA)---------------------------------------------
			// Cadastro de uma amostra em um analise de uma solicitação
//			Optional<Solicitacao> testeSoli = this.solicitacaoRepository.findById(1);
//			List<Analise> testeAnalise = testeSoli.get().getListaAnalise();
//			for (int i = 0; i < testeAnalise.size(); i++) {
//				System.out.println("analise" + testeAnalise.get(i).getId());
//				System.out.println("analise" + testeAnalise.get(i).getLeite());
//				System.out.println("analise" + testeAnalise.get(i).getOrigemLeite());
//				System.out.println("analise" + testeAnalise.get(i).getProdutos());
//				System.out.println("analise" + testeAnalise.get(i).getSolicitacao());
//				testeAnalise.get(i).addAmostra(amostra);
//				testeAnalise.get(i).addAmostra(amostra2);
//				this.analiseRepository.save(testeAnalise.get(i));
//			}			
//-------------------------------------------------------------(NAO FUNCIONA)---------------------------------------------

//			enviar.sendEmail();
			 
		};
	}

}
