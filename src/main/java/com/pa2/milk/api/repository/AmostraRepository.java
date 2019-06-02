package com.pa2.milk.api.repository;

import java.util.List;

import javax.persistence.NamedQuery;

import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pa2.milk.api.model.Amostra;
import com.pa2.milk.api.model.Analise;

@Transactional(readOnly = true)
@NamedQuery(name = "AmostraRepository.findByAmostraId", query = "SELECT a FROM Amostra a WHERE a.analise.id = :analiseId")
public interface AmostraRepository extends GenericRepository<Amostra, Integer> {

	List<Amostra> findByAnalise(@Param("analiseId") Integer analiseId);

}
