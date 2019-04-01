package com.pa2.milk.api.repository.usuario.cliente;

import org.springframework.stereotype.Repository;

import com.pa2.milk.api.model.usuario.cliente.Cliente;
import com.pa2.milk.api.repository.GenericRepository;

@Repository
public interface ClienteRepository extends GenericRepository<Cliente, Integer> {

	Cliente findByCpf(String cpf);

	Cliente findByEmail(String email);

	Cliente findByCpfOrEmail(String cpf, String email);

}
