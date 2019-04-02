package com.pa2.milk.api.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import com.pa2.milk.api.model.enums.TipoPerfilUsuario;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Usuario extends AbstractModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIO")
	@SequenceGenerator(name = "SEQ_USUARIO", sequenceName = "id_seq_usuario", allocationSize = 1)
	private Integer id;

	@Email(message = "O campo email é inválido.")
	@Column(unique = true)
	private String email;

	@NotBlank(message = "O campo nome não pode ser nulo.")
	private String nome;

	@CPF(message = "O campo CPF é inválido.")
	@Column(unique = true, length = 16)
	private String cpf;

	@Enumerated(EnumType.STRING)
	@Column(name = "perfil", nullable = false)
	private TipoPerfilUsuario tipoPerfilUsuario;

	@OneToOne(cascade = CascadeType.ALL)
	private Credencial credencial;

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

	public TipoPerfilUsuario getTipoPerfilUsuario() {
		return tipoPerfilUsuario;
	}

	public void setTipoPerfilUsuario(TipoPerfilUsuario tipoPerfilUsuario) {
		this.tipoPerfilUsuario = tipoPerfilUsuario;
	}

	public Credencial getCredencial() {
		return credencial;
	}

	public void setCredencial(Credencial credencial) {
		this.credencial = credencial;
	}

}
