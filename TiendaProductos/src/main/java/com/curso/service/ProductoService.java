package com.curso.service;

import java.util.List;
import java.util.Optional;

import com.curso.model.Producto;

public interface ProductoService {
	List<Producto> findAll();
	Optional<Producto> findById(long id);
	void restaStock(long id, int unidades);
	void sumaStock(long id, int unidades);
	Double getPrecioById(long id);
}
