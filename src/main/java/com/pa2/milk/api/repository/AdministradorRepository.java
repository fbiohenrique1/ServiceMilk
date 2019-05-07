package com.pa2.milk.api.repository;

import java.util.List;

import com.pa2.milk.api.model.Administrador;

public interface AdministradorRepository extends GenericRepository<Administrador, Integer> {

	Administrador findByCpf(String cpf);

	Administrador findByEmail(String email);

	Administrador findByCpfOrEmail(String cpf, String email);

	List<Administrador> findByCodigoTipoPerfilUsuarioAndAtivoTrue(Integer perfil);

	Administrador findByCodigoTipoPerfilUsuarioAndIdAndAtivoTrue(Integer perfil, Integer id);

}
