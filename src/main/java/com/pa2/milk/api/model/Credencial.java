package com.pa2.milk.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity(name = "credencial")
@Table
public class Credencial extends AbstractModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// @NotBlank(message = "O campo username não pode ser nulo.")
	@Column(unique = true)
	private String username;

	// @NotBlank(message = "O campo senha não pode ser nulo.")
	@Column(unique = true/* , length = 8 */)
	private String senha;

	@OneToOne
	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
	private Usuario usuario;

	public Credencial() {
	}

	public Credencial(String username, String senha, Usuario usuario) {
		super();
		this.username = username;
		this.senha = senha;
		this.usuario = usuario;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
