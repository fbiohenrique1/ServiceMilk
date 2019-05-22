package com.pa2.milk.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pa2.milk.api.model.Fazenda;
import com.pa2.milk.api.repository.FazendaRepository;

@Service
public class FazendaService {

	private static final Logger log = LoggerFactory.getLogger(FazendaService.class);

	@Autowired
	private FazendaRepository fazendaRepository;

	public Optional<Fazenda> buscarPorId(Integer id) {
		log.info("Buscando Fazenda por Id");
		return this.fazendaRepository.findById(id);
	}

	public void salvar(Fazenda fazenda) {
		log.info("Salvando fazenda ");
		fazendaRepository.save(fazenda);
	}

	public void remover(Integer id) {
		log.info("Removendo fazenda pelo Id: {}", id);
		this.fazendaRepository.deleteById(id);
	}

	public List<Fazenda> listarFazenda() {
		log.info("Listando todas as fazendas");
		return this.fazendaRepository.findAll();
	}

	public Optional<Fazenda> buscarPorCpfCnpj(String cpfCnpj) {
		log.info("Buscando fazenda pelo cpfcnpj: {}", cpfCnpj);
		return Optional.ofNullable(this.fazendaRepository.findByCpfcnpj(cpfCnpj));
	}

	public List<Fazenda> buscarFazendaClienteId(Integer clienteId) {
		return this.fazendaRepository.findByClienteIdAndAtivoTrue(clienteId);
	}

}
