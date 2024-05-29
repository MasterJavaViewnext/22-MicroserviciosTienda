package com.curso.service;

import java.time.LocalDateTime;
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
	public boolean insert(Pedido pedido) {
		boolean insertRealizado; 
		if (cantidadPosible(pedido.getIdProducto(), pedido.getCantidad())) {
			//Introducimos la fecha actual si no se especificó
			if (pedido.getFecha() == null) pedido.setFecha(LocalDateTime.now());
			//Introducmos el precio calculado 
			pedido.setPrecio(calcularPrecio(pedido.getIdProducto(), pedido.getCantidad()));
			//Insertamos el pedido
			dao.save(pedido);
			insertRealizado = true;
			//Actualizamos el stock de producto
			actualizarStockProducto(pedido.getIdProducto(), pedido.getCantidad());
		} else {
			insertRealizado = false;
		}
		return insertRealizado;
	}

	@Override
	public void delete(long id) {
		dao.delete(findById(id).orElse(null));
	}
	
	/**
	 * Método que realiza una peticion get para obtener el stock de un producto mediante
	 * su id y comprueba si hay las unidades suficientes para realizar el pedido
	 * @param idProducto
	 * @param unidadesVendidas
	 * @return precioCalculado
	 */
	private boolean cantidadPosible(int idProducto, int unidadesVendidas) {
		boolean posible = false;
		posible = template.getForObject(URL_PRODUCTOS + "/stock/" + idProducto, Long.class) >= unidadesVendidas;
		return posible;
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
