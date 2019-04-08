package com.pa2.milk.api.repository;

import org.springframework.stereotype.Repository;

import com.pa2.milk.api.model.Cliente;
import com.pa2.milk.api.model.enums.EnumTipoPerfilUsuario;

@Repository
public interface ClienteRepository extends GenericRepository<Cliente, Integer> {

	Cliente findByCpf(String cpf);

	Cliente findByEmail(String email);

	Cliente findByCpfOrEmail(String cpf, String email);

	Cliente findByTipoPerfilUsuario(EnumTipoPerfilUsuario perfil);

	Cliente findByTipoPerfilUsuarioAndId(EnumTipoPerfilUsuario perfil, Integer id);
}
