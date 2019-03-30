package com.pa2.milk.api.model.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

import com.pa2.milk.api.model.AbstractModel;
import com.pa2.milk.api.model.usuario.enums.TipoPerfilUsuario;

@Entity
@Table
public class Usuario extends AbstractModel<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIO")
	@SequenceGenerator(name = "SEQ_USUARIO", sequenceName = "id_seq_usuario", allocationSize = 1)
	private Integer id;

	@Email
	@NotBlank
	@Column(unique = true)
	private String email;
	
    @NotBlank
	private String nome;
    
    @CPF
    @Column(unique = true, length = 16)
	private String cpf;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "perfil", nullable = false)
    private TipoPerfilUsuario tipoPerfilUsuario;
    
	public Usuario() {	
	}	
	
	public Usuario(String email, String nome, String cpf, TipoPerfilUsuario tipoPerfilUsuario) {
		this.email = email;
		this.nome = nome;
		this.cpf = cpf;
		this.tipoPerfilUsuario = tipoPerfilUsuario;
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
	
	public TipoPerfilUsuario getTipoPerfilUsuario() {
		return tipoPerfilUsuario;
	}
	
	public void setTipoPerfilUsuario(TipoPerfilUsuario tipoPerfilUsuario) {
		this.tipoPerfilUsuario = tipoPerfilUsuario;
	}
}
