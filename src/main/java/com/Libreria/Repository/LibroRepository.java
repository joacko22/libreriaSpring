package com.Libreria.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Libreria.entidades.Libro;

public interface LibroRepository extends JpaRepository<Libro, String> {
	
		
	

}
