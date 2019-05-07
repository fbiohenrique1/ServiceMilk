package com.pa2.milk.api.model.dto;

import java.util.Date;

import com.pa2.milk.api.model.Analise;

public class AmostraDto {
	private Date dataColeta;

	private int numeroAmostra;

	private String qrCode;

	private String observacao;
		
	private Analise analise;

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
