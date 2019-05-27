package com.pa2.milk.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "amostra")
@Table
public class Amostra extends AbstractModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataColeta;

	@NotNull(message = "O campo número de Amostra não pode ser nulo.")
	private int numeroAmostra;

	@NotBlank(message = "O campo qrCode não pode ser nulo.")
	@Column(unique = true)
	private String qrCode;

	@Column(length = 2047)
	private String observacao;

	@ManyToOne
	@JoinColumn(name = "analise_id")
	@JsonIgnore
	private Analise analise;

	public Amostra() {
	}

	public Amostra(Date dataColeta,
			@NotNull(message = "O campo número de Amostra não pode ser nulo.") int numeroAmostra,
			@NotBlank(message = "O campo qrCode não pode ser nulo.") String qrCode, String observacao,
			Analise analise) {
		super();
		this.dataColeta = dataColeta;
		this.numeroAmostra = numeroAmostra;
		this.qrCode = qrCode;
		this.observacao = observacao;
		this.analise = analise;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataColeta() {
		return dataColeta;
	}

	public void setDataColeta(Date dataColeta) {
		this.dataColeta = dataColeta;
	}

	public int getNumeroAmostra() {
		return numeroAmostra;
	}

	public void setNumeroAmostra(int numeroAmostra) {
		this.numeroAmostra = numeroAmostra;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Analise getAnalise() {
		return analise;
	}

	public void setAnalise(Analise analise) {
		this.analise = analise;
	}

}
