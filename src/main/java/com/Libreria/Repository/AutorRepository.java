package com.Libreria.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Libreria.entidades.Autor;



public interface AutorRepository extends JpaRepository<Autor,String> {


@Query("SELECT a FROM Autor a WHERE a.alta = true")
public List<Autor> autoresActivos();
	
}
