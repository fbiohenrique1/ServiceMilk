package com.pa2.milk.api.resources.cliente;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResources {

	@RequestMapping(method=RequestMethod.GET)
	public String findById(){
		return "REST está funcionando";
	}
}
