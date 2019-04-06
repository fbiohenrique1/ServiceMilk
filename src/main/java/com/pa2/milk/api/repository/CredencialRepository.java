package com.pa2.milk.api.repository;

import com.pa2.milk.api.model.Credencial;

public interface CredencialRepository extends GenericRepository<Credencial, Integer> {

	Credencial findByUsername(String username);
}
