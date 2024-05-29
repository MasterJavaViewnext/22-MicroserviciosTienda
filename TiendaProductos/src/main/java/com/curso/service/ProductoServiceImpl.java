package com.curso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.dao.ProductoDao;
import com.curso.model.Producto;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	ProductoDao dao;
	
	@Override
	public List<Producto> findAll() {
		return dao.findAll();
	}

	@Override
	public Optional<Producto> findById(long id) {
		return dao.findById(id);
	}

	@Override
	public void restaStock(long id, int unidades) {
		dao.restaStock(id, unidades);
	}

	@Override
	public void sumaStock(long id, int unidades) {
		dao.sumaStock(id, unidades);
	}

	@Override
	public Double getPrecioById(long id) {
		Producto producto = findById(id).orElse(null);
		return producto.getPrecio();
	}

}
