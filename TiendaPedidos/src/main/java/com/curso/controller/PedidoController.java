package com.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.model.Pedido;
import com.curso.service.PedidoService;

@RestController
@RequestMapping(value = "pedidos")
public class PedidoController {
	
	@Autowired
	PedidoService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	List<Pedido> findAll(){
		return service.findAll();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	void insert(@RequestBody Pedido pedido) {
		service.insert(pedido);
	}
}
