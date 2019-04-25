package com.pa2.milk.api.repository;

import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pa2.milk.api.model.Fazenda;
import com.pa2.milk.api.model.Solicitacao;

@Transactional(readOnly = true)
@NamedQuery(name = "FazendaRepository.findByClienteId", query = "SELECT f FROM Fazenda f WHERE f.cliente.id = :clienteId")
public interface FazendaRepository extends GenericRepository<Fazenda, Integer> {

	Fazenda findByCnpj(String cnpj);

	List<Fazenda> findByClienteId(@Param("clienteId") Integer funcionarioId);

	
}
