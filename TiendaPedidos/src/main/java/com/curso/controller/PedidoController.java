package com.curso.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	Optional<Pedido> findById(@PathVariable long id){
		return service.findById(id);
	}
	
	//Método que intenta insertar y devuelve un codigo HTTP dependiendo si es posible realizar la insercion
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<String> insert(@RequestBody Pedido pedido) {
		ResponseEntity<String>  ret;
		if (service.insert(pedido)) {
			ret = ResponseEntity.status(HttpStatus.CREATED).body ("HTTP STATUS 201: Pedido correctamente creado y stock actualizado");
		} else {
			ret = ResponseEntity.status(HttpStatus.CONFLICT).body("HTTP STATUS 409: Cantidad superior al stock del producto");
		}
		return ret;
	}
	
	@DeleteMapping(value = "{id}")
	void delete(@PathVariable long id) {
		service.delete(id);
	}
	
}
