package com.pa2.milk.api.repository;

import java.util.List;

import javax.persistence.NamedQuery;

import org.springframework.data.repository.query.Param;

import com.pa2.milk.api.model.Fazenda;

@NamedQuery(name = "FazendaRepository.findByClienteId", 
query = "SELECT faze FROM Fazenda faze WHERE faze.cliente.id = :clienteId")
public interface FazendaRepository extends GenericRepository<Fazenda, Integer> {

	Fazenda findByCnpj(String cnpj);
	List<Fazenda> findByClienteId(@Param("clieenteId") Integer clienteId);

}
