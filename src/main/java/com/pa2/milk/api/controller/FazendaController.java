package com.pa2.milk.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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













}
