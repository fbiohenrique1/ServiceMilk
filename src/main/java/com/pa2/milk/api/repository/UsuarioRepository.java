package com.pa2.milk.api.repository;

import org.springframework.stereotype.Repository;

import com.pa2.milk.api.model.Usuario;

@Repository
public interface UsuarioRepository extends GenericRepository<Usuario, Integer> {

	Usuario findByEmail(String email);
}
