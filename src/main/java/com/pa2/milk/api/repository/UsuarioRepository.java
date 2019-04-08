package com.pa2.milk.api.repository;

import org.springframework.stereotype.Repository;

import com.pa2.milk.api.model.Usuario;
import com.pa2.milk.api.model.enums.EnumTipoPerfilUsuario;

@Repository
public interface UsuarioRepository extends GenericRepository<Usuario, Integer> {

	Usuario findByEmail(String email);
	Usuario findByTipoPerfilUsuario(EnumTipoPerfilUsuario perfil);
	Usuario findByTipoPerfilUsuarioAndId(EnumTipoPerfilUsuario perfil, Integer id);
}
