package com.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curso.model.Producto;
import com.curso.service.ProductoService;

@RestController
@RequestMapping(value = "productos")
public class ProductoController {
	
	@Autowired
	ProductoService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	List<Producto> findAll(){
		return service.findAll();
	}

	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	Producto findById(@PathVariable long id) {
		return service.findById(id).orElse(null);
	}

	//Método que resta las unidades indicadas al stock del producto con ese id
	@PutMapping(value = "resta")
	void restaStock(@RequestParam int id, @RequestParam int unidades) {
		service.restaStock(id, unidades);
	}
	
	//Método que suma las unidades indicadas al stock del producto con ese id
	@PutMapping(value = "suma")
	void sumaStock(@RequestParam int id, @RequestParam int unidades) {
		service.sumaStock(id, unidades);
	}
	
	@GetMapping(value = "precio/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	Double getPrecioById(@PathVariable long id) {
		return service.getPrecioById(id);
	}
	
}
