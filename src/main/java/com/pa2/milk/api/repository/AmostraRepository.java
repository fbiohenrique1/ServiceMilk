package com.pa2.milk.api.repository;

import java.util.List;

import javax.persistence.NamedQuery;

import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pa2.milk.api.model.Amostra;

@Transactional(readOnly = true)
@NamedQuery(name = "AmostraRepository.findByAnaliseId", query = "SELECT a FROM Amostra a WHERE a.analise.id = :analiseId")
public interface AmostraRepository extends GenericRepository<Amostra, Integer> {

	List<Amostra> findByAnaliseId(@Param("analiseId") Integer analiseId);

}
