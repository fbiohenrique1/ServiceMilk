package com.pa2.milk.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pa2.milk.api.helper.Response;
import com.pa2.milk.api.model.Analise;
import com.pa2.milk.api.model.Credencial;
import com.pa2.milk.api.model.Fazenda;
import com.pa2.milk.api.model.Solicitacao;
import com.pa2.milk.api.model.dto.SolicitacaoDto;
import com.pa2.milk.api.model.enums.EnumStatusSolicitacao;
import com.pa2.milk.api.service.FazendaService;
import com.pa2.milk.api.service.SolicitacaoService;

import javassist.NotFoundException;

@RestController
@RequestMapping(value = "/solicitacao")
@CrossOrigin(origins = "*")
public class SolicitacaoController {

	private static final Logger log = LoggerFactory.getLogger(FazendaController.class);

	@Autowired
	private SolicitacaoService solicitacaoService;

	@Autowired
	private FazendaService fazendaSerice;

	@GetMapping
	public List<Solicitacao> listarSolicitacoes() {
		log.info("Listando Solicitações");
		return this.solicitacaoService.listarTodasSolicitacoes();
	}

	@PostMapping
	public ResponseEntity<Response<Solicitacao>> cadastrarSolicitacao(@RequestBody SolicitacaoDto solicitacaoDTO,
			BindingResult result) throws NoSuchAlgorithmException, NotFoundException {
		log.info("Cadastrando solicitacao: {}", solicitacaoDTO.toString());

		Response<Solicitacao> response = new Response<Solicitacao>();

		if (result.hasErrors()) {
			log.info("Erro validando dados de cadastro da Solicitacao: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Optional<Fazenda> fazenda = fazendaSerice.buscarPorCnpj(solicitacaoDTO.getCnpj());
		Solicitacao solicitacao = gerarSolicitacao(solicitacaoDTO, fazenda.get());

		solicitacaoService.salvarSolicitacao(solicitacao);

		return ResponseEntity.ok(response);
	}

	private Solicitacao gerarSolicitacao(SolicitacaoDto solicitacaoDTO, Fazenda fazenda) {
		List<Analise> analises = solicitacaoDTO.transformarParaListaAnalise();
		Solicitacao solicitacao = solicitacaoDTO.transformarParaSolicitacao();
		solicitacao.setFazenda(fazenda);
		solicitacao.setCliente(fazenda.getCliente());
		analises.stream().forEach(objAnalise -> solicitacao.addAnalise(objAnalise));
		solicitacao.setStatus(EnumStatusSolicitacao.PENDENTE);
		return solicitacao;
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Solicitacao>> buscarSolicitacaoPorID(@PathVariable("id") Integer id) {
		log.info("Buscar Solicitacao por Id");

		Response<Solicitacao> response = new Response<Solicitacao>();

		Optional<Solicitacao> solicitacao = solicitacaoService.buscarSolicitacaoPorId(id);

		response.setData(solicitacao.get());

		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<Response<Solicitacao>> deletarCliente(@PathVariable("id") Integer id) {
		log.info("Removendo Solicitação: {}", id);

		Response<Solicitacao> response = new Response<Solicitacao>();

		Optional<Solicitacao> solicitacao = this.solicitacaoService.buscarSolicitacaoPorId(id);

		if (!solicitacao.isPresent()) {
			log.info("Solicitacao não encontrada");
			response.getErros().add("Solicitacao não encontrada");
			ResponseEntity.badRequest().body(response);
		}

		response.setData(solicitacao.get());

		this.solicitacaoService.deletarSolicitacaoPorId(id);

		return ResponseEntity.ok(response);
	}

}
