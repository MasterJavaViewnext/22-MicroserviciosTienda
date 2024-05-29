package com.curso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.curso.dao.PedidoDao;
import com.curso.model.Pedido;

@Service
public class PedidoServiceImpl implements PedidoService {

	private static final String URL_PRODUCTOS = "http://localhost:8000/productos";
	
	@Autowired
	PedidoDao dao;
	@Autowired
	RestTemplate template;
	
	@Override
	public List<Pedido> findAll() {
		return dao.findAll();
	}

	@Override
	public Optional<Pedido> findById(long id) {
		return dao.findById(id);
	}

	@Override
	public void insert(Pedido pedido) {
		pedido.setPrecio(calcularPrecio(pedido.getIdProducto(), pedido.getCantidad()));
		dao.save(pedido);
		actualizarStockProducto(pedido.getIdProducto(), pedido.getCantidad());
	}
	
	/**
	 * Método que realiza una peticion get para obtener el precio de un producto
	 * mediante su id y lo multiplica por las unidades vendidas
	 * @param idProducto
	 * @param unidadesVendidas
	 * @return precioCalculado
	 */
	private Double calcularPrecio(int idProducto, int unidadesVendidas) {
		Double precio;
		precio = template.getForObject(URL_PRODUCTOS + "/precio/" + idProducto, Double.class);
		return precio * unidadesVendidas;
	}
	
	/**
	 * Método que actualiza las unidades del producto vendido realizando una peticion al microservicio
	 * productos y restando las unidades vendidas al stock de ese producto
	 * @param idProducto
	 * @param unidadesVendidas
	 */
	private void actualizarStockProducto(int idProducto, int unidadesVendidas) {
		//Creacion de la URI con los request param
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_PRODUCTOS + "/resta")
		    .queryParam("id", idProducto)
		    .queryParam("unidades", unidadesVendidas);

		String url = builder.toUriString();
		template.put(url, null);
	}
}
