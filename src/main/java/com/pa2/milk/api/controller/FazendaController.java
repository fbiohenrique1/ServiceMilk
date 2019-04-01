package com.pa2.milk.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pa2.milk.api.helper.Response;
import com.pa2.milk.api.model.Fazenda;
import com.pa2.milk.api.service.FazendaService;

@RestController
@RequestMapping (value = "/fazenda")
@CrossOrigin(origins = "*") 
public class FazendaController {
	
	private static final Logger log = LoggerFactory.getLogger(FazendaController.class);
	
	@Autowired
	private FazendaService fazendaService;
	
	@GetMapping
	public List<Fazenda> listarFazendas(){
		List<Fazenda> fazendas =  this.fazendaService.listarFazenda();
		return fazendas;
	}
	
	@PostMapping
	public ResponseEntity<Response<Fazenda>> cadastrarFazenda (@Valid @RequestBody Fazenda fazenda, BindingResult result){
		log.info("Cadastrando Fazenda: {}", fazenda.toString());
		
		Response<Fazenda> response = new Response<Fazenda>();
		
		response.setData(Optional.ofNullable(fazenda));

		Fazenda farm = fazenda;
		
		if (result.hasErrors()) {

			log.error("Erro validando dados do cadastro Fazenda: {}", result.getAllErrors());

			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		this.fazendaService.salvar(farm);

		return ResponseEntity.ok(response);	}


	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Fazenda>> buscarFazendaPorId(@PathVariable("id") Integer id) {

		log.info("Buscar Fazenda por Id");

		Response<Fazenda> response = new Response<Fazenda>();

		Fazenda farm = this.fazendaService.buscarPorId(id);

		response.setData(Optional.ofNullable(farm));

		verificarResposta(response);

		return ResponseEntity.ok(response);
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity<Response<Fazenda>> atualizarFazenda(@PathVariable("id") Integer id,
			@Valid @RequestBody Fazenda fazenda, BindingResult result){

		log.info("Atualizando o Fazenda:{}", fazenda.toString());

		Response<Fazenda> response = new Response<Fazenda>();

		Fazenda farm = this.fazendaService.buscarPorId(id);

		response.setData(Optional.ofNullable(farm));

		verificarResposta(response);

		this.atualizarDadosFazenda(farm, fazenda, result);

		if (result.hasErrors()) {
			log.error("Erro validando lancamento:{}", result.getAllErrors());

			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));

			return ResponseEntity.badRequest().body(response);
		}

		this.fazendaService.salvar(fazenda);

		return ResponseEntity.ok(response);

	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<Response<Fazenda>> deletarFazenda(@PathVariable("id") Integer id) {

		log.info("Removendo Fazenda: {}", id);

		Response<Fazenda> response = new Response<Fazenda>();

		Fazenda fazenda = this.fazendaService.buscarPorId(id);

		response.setData(Optional.ofNullable(fazenda));

		verificarResposta(response);

		this.fazendaService.remover(id);

		return ResponseEntity.ok(response);
	}
	
	private void atualizarDadosFazenda(Fazenda farm, Fazenda fazenda, BindingResult result){
		
		if (!farm.getCnpj().equals(fazenda.getCnpj())) {

			this.fazendaService.buscarPorCnpj(fazenda.getCnpj())
					.ifPresent(faze -> result.addError(new ObjectError("cnpj", "CNPJ já exitente.")));
			farm.setCnpj(fazenda.getCnpj());
		}

	}
	
	private void verificarResposta(Response<Fazenda> response) {
		if (!response.getData().isPresent()) {
			log.info("Fazenda não encontrada");

			response.getErros().add("Fazenda não encontrada");

			ResponseEntity.badRequest().body(response);
		}
	}


}
