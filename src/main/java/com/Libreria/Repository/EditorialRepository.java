package com.Libreria.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Libreria.entidades.Editorial;

import antlr.collections.List;

public interface EditorialRepository extends JpaRepository<Editorial,String> {


	
	@Query("SELECT e FROM Editorial e WHERE e.alta = true")
	public List listarActivos();
	
}
