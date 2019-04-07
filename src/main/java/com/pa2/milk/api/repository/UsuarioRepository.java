package com.pa2.milk.api.repository;

import org.springframework.stereotype.Repository;

import com.pa2.milk.api.model.Usuario;
import com.pa2.milk.api.model.enums.TipoPerfilUsuario;

@Repository
public interface UsuarioRepository extends GenericRepository<Usuario, Integer> {

	Usuario findByEmail(String email);
	Usuario findByTipoPerfilUsuario(TipoPerfilUsuario perfil);
	Usuario findByTipoPerfilUsuarioAndId(TipoPerfilUsuario perfil, Integer id);
}
