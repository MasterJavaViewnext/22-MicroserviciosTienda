package com.curso.service;

import java.util.List;
import java.util.Optional;

import com.curso.model.Pedido;

public interface PedidoService {
	List<Pedido> findAll();
	Optional<Pedido> findById(long id);
	void insert(Pedido pedido);
	
}
