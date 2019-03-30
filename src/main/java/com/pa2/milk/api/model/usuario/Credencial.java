package com.pa2.milk.api.model.usuario;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Parameter;


@Entity
@Table(name="credencial")
public class Credencial {

	@Id
	@GeneratedValue(generator = "fk_credencial_id_cliente")
	@org.hibernate.annotations.GenericGenerator(name = "fk_credencial_id_cliente",
	strategy = "foreign", parameters = @Parameter(name = "property", value = "cliente"))
	@Column(name = "id_credencial")
	private Integer id;

	@NotBlank
	@Column(unique = true)
	private String username;

	@NotBlank
	@Column(unique = true, length=8)
	private String senha;
	
	@OneToOne	
	private Usuario usuario;

	public Credencial() {
	}

	public Integer getId() {
		return id;
	}

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
