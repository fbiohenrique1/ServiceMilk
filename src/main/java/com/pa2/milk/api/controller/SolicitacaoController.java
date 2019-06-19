package com.pa2.milk.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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
import com.pa2.milk.api.model.Fazenda;
import com.pa2.milk.api.model.Solicitacao;
import com.pa2.milk.api.model.dto.SolicitacaoDetalhesDto;
import com.pa2.milk.api.model.dto.SolicitacaoDto;
import com.pa2.milk.api.model.dto.StatusSolicitacaoDTO;
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

	@PreAuthorize("hasAnyRole('ADMINISTRADOR','BOLSISTA','CLIENTE')")
	@PostMapping
	public ResponseEntity<Response<Solicitacao>> cadastrarSolicitacao(@RequestBody SolicitacaoDto solicitacaoDTO,
			BindingResult result) throws NoSuchAlgorithmException, NotFoundException {
		log.info("Cadastrando solicitacao: {}", solicitacaoDTO.toString(),"cpfcnj");
		Response<Solicitacao> response = new Response<Solicitacao>();

		Optional<Fazenda> fazenda = fazendaSerice.buscarPorCpfCnpj(solicitacaoDTO.getCpfcnpj());

		if (!fazenda.isPresent()) {
			log.info("Não existe fazenda cadastrada com tais dados: {}", solicitacaoDTO.getCpfcnpj());
			response.getErros().add("Fazenda não encontrada");
			return ResponseEntity.badRequest().body(response);
			// result.addError(new ObjectError("solicitacao", "Fazenda não encontrada."));
		}
		Solicitacao solicitacao = gerarSolicitacao(solicitacaoDTO, fazenda.get(), result);
		if (result.hasErrors()) {
			log.info("Erro no cadastro da Solicitacao: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		solicitacaoService.salvarSolicitacao(solicitacao);
		response.setData(solicitacao);

		return ResponseEntity.ok(response);
	}

	private Solicitacao gerarSolicitacao(SolicitacaoDto solicitacaoDTO, Fazenda fazenda, BindingResult result)
			throws NoSuchAlgorithmException {
		List<Analise> analises = solicitacaoDTO.transformarParaListaAnalise();
		if(analises.isEmpty()) {
			result.addError(new ObjectError("analises", "Lista de análises vazia"));
		}
		Solicitacao solicitacao = solicitacaoDTO.transformarParaSolicitacao();
		solicitacao.setFazenda(fazenda);
		solicitacao.setCliente(fazenda.getCliente());
		// solicitacao.setDataCriada(Calendar.getInstance(TimeZone.getTimeZone("GMT-03:00")).getTime());
		solicitacao.setDataCriada(new Date());
		analises.stream().forEach(objAnalise -> solicitacao.addAnalise(objAnalise));
		solicitacao.setStatus(EnumStatusSolicitacao.PENDENTE);
		return solicitacao;
	}

	@PreAuthorize("hasAnyRole('ADMINISTRADOR','BOLSISTA','CLIENTE')")
	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Solicitacao>> buscarSolicitacaoPorID(@PathVariable("id") Integer id) {
		log.info("Buscar Solicitacao por Id");

		Response<Solicitacao> response = new Response<Solicitacao>();

		Optional<Solicitacao> solicitacao = solicitacaoService.buscarSolicitacaoPorId(id);

		if (!solicitacao.isPresent()) {
			log.info("Cliente não encontrado: {}", solicitacao.get());
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(solicitacao.get());

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/status/{id}")
	public ResponseEntity<Response<SolicitacaoDetalhesDto>> statusSolicitacao(@PathVariable("id") Integer id) {
		log.info("Status e Observação da Solicitação");

		Response<SolicitacaoDetalhesDto> response = new Response<SolicitacaoDetalhesDto>();

		Optional<Solicitacao> solicitacao = solicitacaoService.buscarSolicitacaoPorId(id);

		if (!solicitacao.isPresent()) {
			log.info("Solicitação não encontrada: {}", solicitacao.get());
			return ResponseEntity.badRequest().body(response);
		}

		SolicitacaoDetalhesDto s = new SolicitacaoDetalhesDto();
		s.setStatus(solicitacao.get().getStatus());
		s.setObservacao(solicitacao.get().getObservacao());

		response.setData(s);

		return ResponseEntity.ok(response);
	}

	@PreAuthorize("hasAnyRole('ADMINISTRADOR','BOLSISTA','CLIENTE')")
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Response<Solicitacao>> deletarSolicitacao(@PathVariable("id") Integer id) {
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

	// TODO: Atualizar solicitação

	@PreAuthorize("hasAnyRole('ADMINISTRADOR','BOLSISTA','CLIENTE')")
	@PostMapping(value = "/status")
	public ResponseEntity<Response<Solicitacao>> atualizarStatus(@RequestBody StatusSolicitacaoDTO statusSolicitacaoDTO)
			throws NotFoundException {
		log.info("Atualizando Status de Solicitação: {}", statusSolicitacaoDTO.getSolicitacaoId());

		Response<Solicitacao> response = new Response<Solicitacao>();

		Optional<Solicitacao> solicitacao = solicitacaoService
				.buscarSolicitacaoPorId(statusSolicitacaoDTO.getSolicitacaoId());

		if (!solicitacao.isPresent()) {
			log.info("Solicitacao não encontrada");
			response.getErros().add("Solicitacao não encontrada");
			ResponseEntity.badRequest().body(response);
		}

		solicitacao.get().setStatus(statusSolicitacaoDTO.getStatus());
		solicitacao.get().setObservacao(statusSolicitacaoDTO.getObservacao());
		solicitacaoService.salvarSolicitacao(solicitacao.get());

		response.setData(solicitacao.get());

		return ResponseEntity.ok(response);
	}

//	@PutMapping(value = "{id}")
//	public ResponseEntity<Response<SolicitacaoDto>> atualizarSolicitacao(@PathVariable("id") Integer id,
//			@Valid @RequestBody SolicitacaoDto solicitacaoDto, BindingResult result) throws NoSuchAlgorithmException {
//
//		log.info("Atualizando o Solicitacao:{}", solicitacaoDto.toString());
//
//		Response<SolicitacaoDto> response = new Response<SolicitacaoDto>();
//
//		Optional<Solicitacao> solicitacao = this.solicitacaoService.buscarSolicitacaoPorId(id);
//
//		if (!solicitacao.isPresent()) {
//			log.info("Solicitacao não encontrada");
//			response.getErros().add("Solicitacao não encontrada");
//			ResponseEntity.badRequest().body(response);
//		}
//
//		this.atualizarDadosSolicitacao(solicitacao.get(), solicitacaoDto, result);
//
//		return ResponseEntity.ok(response);
//
//	}
//
//	private void atualizarDadosSolicitacao(Solicitacao solicitacao, SolicitacaoDto solicitacaoDto, BindingResult result)
//			throws NoSuchAlgorithmException {
//
//	}

}
