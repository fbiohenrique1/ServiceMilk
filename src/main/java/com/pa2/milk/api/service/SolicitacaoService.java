package com.pa2.milk.api.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pa2.milk.api.model.Analise;
import com.pa2.milk.api.model.Cliente;
import com.pa2.milk.api.model.Fazenda;
import com.pa2.milk.api.model.Solicitacao;
import com.pa2.milk.api.model.enums.EnumStatusSolicitacao;
import com.pa2.milk.api.repository.ClienteRepository;
import com.pa2.milk.api.repository.FazendaRepository;
import com.pa2.milk.api.repository.SolicitacaoRepository;

@Service
public class SolicitacaoService {

	private static final Logger log = LoggerFactory.getLogger(SolicitacaoService.class);

	@Autowired
	private SolicitacaoRepository solicitacaoRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	
	private FazendaRepository fazendaRepository;

	private Cliente cliente;

	private Solicitacao solicitacao;

	public void salvarSolicitacao(Fazenda fazenda) {
		// TODO: stub para salvar solicitacao
	}

	public void adicionarAnalise() {
		// TODO: stub para add analises
	}

	public void adicionarAmostra() {
		// TODO: stub para add analise
	}

	private boolean existeFazenda(Fazenda fazenda) {
		return cliente.getListaFazenda().contains(fazenda);
	}

	private Solicitacao criarSolicitacao(Fazenda fazenda) {
		if (existeFazenda(fazenda)) {
			Solicitacao solicitacao = new Solicitacao();
			solicitacao.setCliente(cliente);
			solicitacao.setFazenda(fazenda);
			EnumStatusSolicitacao enums = EnumStatusSolicitacao.porCodigo(1);
		}
		return solicitacao;
	}

}
