package com.pa2.milk.api.response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.pa2.milk.api.model.usuario.cliente.Cliente;

public class Response<T> {

	private Optional<T> data;
	private List<String> erros;
	
	
	public Response() {
	}


	public Optional<T> getData() {
		return data;
	}


	public void setData(Optional<T> data) {
		this.data = data;
	}


	public List<String> getErros() {
		if(this.erros == null) {
			this.erros = new ArrayList<String>();
		}
		
		return erros;
	}


	public void setErros(List<String> erros) {
		this.erros = erros;
	}
	
	
		
	
	
}
