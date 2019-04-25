package com.pa2.milk.api.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pa2.milk.api.model.Fazenda;
import com.pa2.milk.api.model.Solicitacao;
import com.pa2.milk.api.model.enums.EnumStatusSolicitacao;
import com.pa2.milk.api.repository.FazendaRepository;
import com.pa2.milk.api.repository.SolicitacaoRepository;

import javassist.NotFoundException;

@Service
public class SolicitacaoService {

	private static final Logger log = LoggerFactory.getLogger(SolicitacaoService.class);

	@Autowired
	private SolicitacaoRepository solicitacaoRepository;


	public void salvarSolicitacao(Solicitacao solicitacao) throws NotFoundException {
		solicitacaoRepository.save(solicitacao);
	}

	public void adicionarAnalise() {
		// TODO: stub para add analises
	}

	public void adicionarAmostra() {
		// TODO: stub para add analise
	}

	private Solicitacao criarSolicitacao(Fazenda fazenda) {
		Solicitacao solicitacao = new Solicitacao();
		solicitacao.setCliente(fazenda.getCliente());
		solicitacao.setFazenda(fazenda);
		solicitacao.setStatus(EnumStatusSolicitacao.PENDENTE);
		return solicitacao;
	}

	public Optional<Solicitacao> buscarSolicitacaoPorId(Integer id) {
		return solicitacaoRepository.findById(id);
	}

}
