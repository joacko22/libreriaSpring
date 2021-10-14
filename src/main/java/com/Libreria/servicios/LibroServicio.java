package com.Libreria.servicios;

import org.springframework.stereotype.Service;

import com.Libreria.Repository.LibroRepository;
import com.Libreria.entidades.Autor;
import com.Libreria.entidades.Editorial;
import com.Libreria.entidades.Libro;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class LibroServicio {

	@Autowired
	private AutorServicio autorService;
	@Autowired
	private EditorialServicio editorialService;

	@Autowired
	private LibroRepository libroRepository;

	public Libro guardar(String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados,
			Integer ejemplaresRestantes, String autorID, String editorialID) throws Exception {
		try {

			Autor autor = autorService.obtenerID(autorID);
			Editorial editorial = editorialService.obtenerID(editorialID);
			Libro libro = new Libro();
			libro.setTitulo(titulo);
			libro.setAnio(anio);
			libro.setEjemplares(ejemplares);
			libro.setEjemplaresPrestados(ejemplaresPrestados);
			libro.setEjemplaresRestantes(ejemplaresRestantes);
			libro.setAutor(autor);
			libro.setEditorial(editorial);
			return libroRepository.save(libro);

		} catch (Exception e) {
			throw new Exception("Error en la creación del libro");
		}

	}

	public List<Libro> findAll() throws Exception {

		try {

			return libroRepository.findAll();

		} catch (Exception e) {

			System.err.println(e);

			throw new Exception("No se que paso, pero no anda");

		}

	}

	public Libro findById(String id) throws Exception {

		Optional<Libro> res = libroRepository.findById(id);

		if (!res.isEmpty()) {
			return res.get();
		} else {
			throw new Exception("No existe libro con dicho id");
		}

	}

	public void removeById(String id) throws Exception {

		try {
			libroRepository.deleteById(id);
		} catch (Exception e) {
			throw new Exception("Error al eliminar el libro, asegurese del que el libro exista");
		}

	}
}