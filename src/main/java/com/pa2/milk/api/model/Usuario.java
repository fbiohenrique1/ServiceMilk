package com.pa2.milk.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.pa2.milk.api.model.enums.EnumTipoPerfilUsuario;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Usuario extends AbstractModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Email(message = "O campo email é inválido.")
	@Column(unique = true)
	private String email;

	@NotBlank(message = "O campo nome não pode ser nulo.")
	private String nome;

	// @CPF(message = "O campo CPF é inválido.")
	@NotBlank(message = "O campo CPF não pode ser nulo.")
	@Column(unique = true, length = 16)
	private String cpf;

	@Column(name = "perfil", nullable = false)
	private Integer codigoTipoPerfilUsuario;

	public Usuario() {
		super();
	}

	public Usuario(String email, String nome, String cpf, Integer codigoTipoPerfilUsuario) {
		super();
		this.email = email;
		this.nome = nome;
		this.cpf = cpf;
		this.codigoTipoPerfilUsuario = codigoTipoPerfilUsuario;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public EnumTipoPerfilUsuario getCodigoTipoPerfilUsuario() {
		return EnumTipoPerfilUsuario.porCodigo(codigoTipoPerfilUsuario);
	}

	public void setCodigoTipoPerfilUsuario(EnumTipoPerfilUsuario tipoPerfilUsuario) {
		this.codigoTipoPerfilUsuario = tipoPerfilUsuario.getCodigo();
	}
}
