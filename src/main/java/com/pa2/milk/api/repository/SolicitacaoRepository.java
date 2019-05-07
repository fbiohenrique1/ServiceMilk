package com.pa2.milk.api.repository;

import javax.persistence.NamedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.pa2.milk.api.model.Solicitacao;

@Transactional(readOnly = true)
@NamedQuery(name = "SolicitacaoRepository.findByFazenda", query = "SELECT s FROM Solicitacao s WHERE s.fazenda.id = :fazendaId")
public interface SolicitacaoRepository extends GenericRepository<Solicitacao, Integer> {

//List<Solicitacao> findByFazenda(@Param("fazendaId") Integer fazendaId);

}