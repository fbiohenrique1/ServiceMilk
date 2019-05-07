package com.pa2.milk.api.repository;

import java.util.List;

import javax.persistence.NamedQuery;

import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pa2.milk.api.model.Solicitacao;

@Transactional(readOnly = true)
@NamedQuery(name = "SolicitacaoRepository.findByClienteId", query = "SELECT s FROM Solicitacao s WHERE s.cliente.id = :clienteId")
public interface SolicitacaoRepository extends GenericRepository<Solicitacao, Integer> {

	List<Solicitacao> findByClienteId(@Param("clienteId") Integer clienteId);

}