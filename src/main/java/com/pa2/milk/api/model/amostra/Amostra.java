package com.pa2.milk.api.model.amostra;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.pa2.milk.api.model.AbstractModel;

@Entity
@Table
public class Amostra extends AbstractModel<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AMOSTRA")
	@SequenceGenerator(name = "SEQ_AMOSTRA", sequenceName = "id_seq_amostra", allocationSize = 1)
	private Integer id;

	@Column(nullable = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dataColeta;

	@NotNull
	private int numeroAmostra;

	@NotBlank
	@Column(unique = true)
	private String qrCode;

	@Column(length = 2047)
	private String Observacao;

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
		return Observacao;
	}

	public void setObservacao(String observacao) {
		Observacao = observacao;
	}

}
