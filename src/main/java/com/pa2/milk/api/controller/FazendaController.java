package com.pa2.milk.api.controller;

import java.security.NoSuchAlgorithmException;
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
import com.pa2.milk.api.model.Cliente;
import com.pa2.milk.api.model.Fazenda;
import com.pa2.milk.api.model.dto.CadastroFazendaDto;
import com.pa2.milk.api.service.ClienteService;
import com.pa2.milk.api.service.FazendaService;

@RestController
@RequestMapping (value = "/fazenda")
@CrossOrigin(origins = "*") 
public class FazendaController {
	
	private static final Logger log = LoggerFactory.getLogger(FazendaController.class);
	
	@Autowired
	private FazendaService fazendaService;
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public List<Fazenda> listarFazendas(){
		List<Fazenda> fazendas =  this.fazendaService.listarFazenda();
		return fazendas;
	}
	
	@PostMapping
	public ResponseEntity<Response<CadastroFazendaDto>> cadastrarFazenda (@Valid @RequestBody CadastroFazendaDto fazendaDto,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando Fazenda: {}", fazendaDto.toString());
		
		Response<CadastroFazendaDto> response = new Response<CadastroFazendaDto>();
		
		validarDadosExistentes(fazendaDto, result);
		
//		Cliente c = this.converterDtoParaCliente(fazendaDto);
		Cliente c = this.clienteService.buscarPorCpfNormal(fazendaDto.getCpf());
		
		Fazenda f = this.converterDtoParaFazenda(fazendaDto, result);
		
		if(result.hasErrors()) {
			log.info("Erro validando dados de cadastro da Fazenda: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
//		this.clienteService.salvar(c);
		f.setCliente(c);
		this.fazendaService.salvar(f);
		
		response.setData2(this.converterCadastroFazendaDto(f));
		
		return ResponseEntity.ok(response);
	}


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
	
	private void validarDadosExistentes(CadastroFazendaDto FazendaDto, BindingResult result) {
		this.fazendaService.buscarPorCnpj(FazendaDto.getCnpj())
		                   .ifPresent(emp -> result.addError(new ObjectError("fazenda", "Fazenda já existente.")));
	
	}


	
	private Fazenda converterDtoParaFazenda(CadastroFazendaDto fazendaDto, BindingResult result)
			throws NoSuchAlgorithmException {
	
		Fazenda f = new Fazenda();
		
		f.setNome(fazendaDto.getNomeFazenda());
		f.setImagem(fazendaDto.getImagem());
		f.setEstado(fazendaDto.getEstado());
		f.setBairro(fazendaDto.getBairro());
		f.setCep(fazendaDto.getCep());
		f.setCidade(fazendaDto.getCidade());
		f.setEndereco(fazendaDto.getEndereco());
	    f.setCnpj(fazendaDto.getCnpj());
	    f.setNumero(fazendaDto.getNumero());
		
		return f;
	}
	
	
	private CadastroFazendaDto converterCadastroFazendaDto(Fazenda fazenda) {
		CadastroFazendaDto fazendaDto = new CadastroFazendaDto();
		
		fazendaDto.setId(fazenda.getId());
		fazendaDto.setNomeFazenda(fazenda.getNome());
		fazendaDto.setBairro(fazenda.getBairro());
		fazendaDto.setCep(fazenda.getCep());
		fazendaDto.setCidade(fazenda.getCidade());
		fazendaDto.setCnpj(fazenda.getCnpj());
		fazendaDto.setEndereco(fazenda.getEndereco());
		fazendaDto.setEstado(fazenda.getEstado());
		fazendaDto.setImagem(fazenda.getImagem());
		fazendaDto.setNumero(fazenda.getNumero());
		                                                            
		fazendaDto.setCpf(fazenda.getCliente().getCpf());
		fazendaDto.setEmail(fazenda.getCliente().getEmail());
		
		return fazendaDto;
	}


}
