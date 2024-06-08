package com.api.arpires.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.arpires.apirest.Entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
	
}
