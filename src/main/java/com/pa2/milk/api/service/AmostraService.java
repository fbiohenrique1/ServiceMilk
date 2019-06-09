package com.pa2.milk.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pa2.milk.api.model.Amostra;
import com.pa2.milk.api.model.Fazenda;
import com.pa2.milk.api.repository.AmostraRepository;

@Service
public class AmostraService {

	private static final Logger log = LoggerFactory.getLogger(AmostraService.class);

	@Autowired
	public AmostraRepository amostraRepository;

	public void salvar(Amostra amostra) {
		log.info("Salvando Amostra");
		amostraRepository.save(amostra);
	}

	public Optional<Amostra> buscarPorId(Integer id) {
		log.info("Buscando Amostra por ID ");
		return this.amostraRepository.findById(id);
	}

	public void remover(Integer id) {
		log.info("Removendo amostra pelo Id: {}", id);
		this.amostraRepository.deleteById(id);
	}
	
	public List<Amostra> listarAmostras() {
		log.info("Listando todas as amostras");
		return this.amostraRepository.findAll();
	}
	
	public List<Amostra> deletarAmostrasPorAnalise(Integer analiseId) {
		log.info("Deletar todas as amostras por Analise");
		return this.amostraRepository.deleteByAnaliseId(analiseId);
	}

	

}
