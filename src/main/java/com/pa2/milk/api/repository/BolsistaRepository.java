package com.pa2.milk.api.repository;

import java.util.List;

import com.pa2.milk.api.model.Bolsista;

public interface BolsistaRepository extends GenericRepository<Bolsista, Integer> {

	Bolsista findByCpf(String cpf);

	Bolsista findByEmail(String email);

	Bolsista findByCpfOrEmail(String cpf, String email);

	List<Bolsista> findByCodigoTipoPerfilUsuarioAndAtivoTrue(Integer perfil);

	Bolsista findByCodigoTipoPerfilUsuarioAndIdAndAtivoTrue(Integer perfil, Integer id);

}
