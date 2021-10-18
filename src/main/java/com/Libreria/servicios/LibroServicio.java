package com.Libreria.servicios;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Libro guardar(String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados,
			Integer ejemplaresRestantes, String autorID, String editorialID) throws Exception {
		try {
			validar(titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, autorID, editorialID);
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

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Libro update(String id, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados,
			Integer ejemplaresRestantes, String autorID, String editorialID) throws Exception {
		try {
			validar(titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, autorID, editorialID);
			Autor autor = autorService.obtenerID(autorID);
			Editorial editorial = editorialService.obtenerID(editorialID);
			Libro libro = obtenerID(id);
			libro.setTitulo(titulo);
			libro.setAnio(anio);
			libro.setEjemplares(ejemplares);
			libro.setEjemplaresPrestados(ejemplaresPrestados);
			libro.setEjemplaresRestantes(ejemplaresRestantes);
			libro.setAutor(autor);
			libro.setEditorial(editorial);
			return libroRepository.save(libro);
		} catch (Exception e) {
			throw new Exception("Error al actualizar el libro");
		}

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Libro obtenerID(String id) throws Exception {

		Optional<Libro> res = libroRepository.findById(id);

		if (!res.isEmpty()) {
			return res.get();
		} else {
			throw new Exception("No existe libro con dicho id");
		}

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void eliminar(String id) throws Exception {

		try {
			libroRepository.deleteById(id);
		} catch (Exception e) {
			throw new Exception("Error al eliminar el libro, asegurese del que el libro exista");
		}

	}

	@Transactional(readOnly = true)
	public List<Libro> listarTodos() throws Exception {

		try {

			return libroRepository.findAll();

		} catch (Exception e) {

			System.err.println(e);

			throw new Exception("No se que paso, pero no anda");

		}
	}

	public void validar(String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados,
			Integer ejemplaresRestantes, String autorID, String editorialID) throws Exception {
		if (titulo.contains(" ") || titulo.isEmpty()) {
			throw new Exception("el titulo requiere un nombre");
		}
		if (anio.equals(0) || anio.equals(" ")) {
			throw new Exception("falta el año");
		}
		if (ejemplares.equals(0) || ejemplares.equals(" ")) {
			throw new Exception("faltan ejemplares");
		}if (ejemplaresPrestados.equals(0) || ejemplaresPrestados.equals(" ")) {
			throw new Exception("falta llenar el campo o no puede ser 0");
		}
		if (ejemplaresRestantes.equals(0) || ejemplaresRestantes.equals(" ")) {
			throw new Exception("falta llenar el campo o no puede ser 0");
		}
		if (autorID.contains(" ") || autorID.isEmpty()) {
			throw new Exception("no se encontro el autor");
		}
		if (editorialID.contains(" ") || editorialID.isEmpty()) {
			throw new Exception("no se encontro la editorial");
		}
	}
	
	
	
	
	
}