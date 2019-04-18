package com.pa2.milk.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pa2.milk.api.model.Bolsista;
import com.pa2.milk.api.model.enums.EnumTipoPerfilUsuario;
import com.pa2.milk.api.repository.BolsistaRepository;

@Service
public class BolsistaService {
	
	private static final Logger log = LoggerFactory.getLogger(BolsistaService.class);
	
	@Autowired
	public BolsistaRepository bolsistaRepository;
	
	public void salvar(Bolsista bolsista) {
		log.info("Salvando Bolsista ");
		bolsistaRepository.save(bolsista);
	}
	
	public Bolsista buscarPorId(Integer id) {
		log.info("Buscando Bolsista por ID ");
		Optional<Bolsista> objBolsista = bolsistaRepository.findById(id);
		return objBolsista.orElse(null);
	}
	
	public Optional<Bolsista> buscarPorCpf(String cpf) {
		log.info("Buscando Bolsista pelo Cpf: {}", cpf);
		return Optional.ofNullable(this.bolsistaRepository.findByCpf(cpf));
	}
		
	public Bolsista buscarPorCpfNormal(String cpf) {
		log.info("Buscando Bolsista pelo Cpf: {}", cpf);
		return this.bolsistaRepository.findByCpf(cpf);
	}
	
	public Optional<Bolsista> buscarPorEmail(String email) {
		log.info("Buscando Bolsista pelo Email: {}", email);
		return Optional.ofNullable(this.bolsistaRepository.findByEmail(email));
	}
	
	public void remover(Integer id) {
		log.info("Removendo Bolsista pelo Id: {}", id);
		this.bolsistaRepository.deleteById(id);
	}
	
	public List<Bolsista> listarBolsista() {
		log.info("Listando Bolsista");
		return this.bolsistaRepository.findAll();
	}
		
	public List<Bolsista> buscarPorTipoPerfilUsuario(EnumTipoPerfilUsuario tipoPerfilUsuario) {
		log.info("Buscando usuario pelo tipoPerfilUsuario: {}", tipoPerfilUsuario);
		return this.bolsistaRepository.findByCodigoTipoPerfilUsuario(tipoPerfilUsuario.getCodigo());
	}

	public Optional<Bolsista> buscarPorTipoPerfilUsuarioandID(EnumTipoPerfilUsuario tipoPerfilUsuario, Integer id) {
		log.info("Buscando Bolsista pelo tipoPerfilUsuario: {}", tipoPerfilUsuario);
		return Optional.ofNullable(this.bolsistaRepository.findByCodigoTipoPerfilUsuarioAndId(tipoPerfilUsuario.getCodigo(), id));
	}

}
