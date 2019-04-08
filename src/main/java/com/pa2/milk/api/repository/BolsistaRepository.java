package com.pa2.milk.api.repository;

import java.util.List;

import com.pa2.milk.api.model.Bolsista;
import com.pa2.milk.api.model.enums.EnumTipoPerfilUsuario;

public interface BolsistaRepository extends GenericRepository<Bolsista, Integer> {

	Bolsista findByCpf(String cpf);

	Bolsista findByEmail(String email);

	Bolsista findByCpfOrEmail(String cpf, String email);
	
	List<Bolsista> findByCodigoTipoPerfilUsuario(Integer perfil);

	Bolsista findByCodigoTipoPerfilUsuarioAndId(EnumTipoPerfilUsuario perfil, Integer id);

}
