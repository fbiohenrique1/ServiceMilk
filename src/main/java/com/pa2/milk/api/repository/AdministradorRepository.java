package com.pa2.milk.api.repository;

import java.util.List;

import com.pa2.milk.api.model.Administrador;

public interface AdministradorRepository extends GenericRepository<Administrador, Integer> {
;

Administrador findByCpf(String cpf);

Administrador findByEmail(String email);

Administrador findByCpfOrEmail(String cpf, String email);

List<Administrador> findByCodigoTipoPerfilUsuario(Integer perfil);

Administrador findByCodigoTipoPerfilUsuarioAndId(Integer perfil, Integer id);

}
