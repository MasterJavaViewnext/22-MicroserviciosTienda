package com.curso.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.curso.model.Producto;

import jakarta.transaction.Transactional;

public interface ProductoDao extends JpaRepository<Producto, Long>{

	@Transactional
	@Modifying
	@Query("UPDATE Producto p SET p.stock = p.stock - ?2 WHERE p.id = ?1")
	void updateStock(long id, int unidades);


}
