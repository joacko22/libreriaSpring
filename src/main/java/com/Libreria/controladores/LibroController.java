package com.Libreria.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Libreria.entidades.Libro;
import com.Libreria.servicios.AutorServicio;
import com.Libreria.servicios.EditorialServicio;
import com.Libreria.servicios.LibroServicio;

@Controller
@RequestMapping("/libros")
public class LibroController {

	@Autowired
	private LibroServicio libroService;
	@Autowired
	private AutorServicio autorService;
	@Autowired
	private EditorialServicio editorialService;

	@GetMapping()
	public String lista(ModelMap modelo) throws Exception {
		try {
			List<Libro> libros = libroService.listarTodos();
			modelo.addAttribute("libros", libros);
			return "libros";
		} catch (Exception e) {
			return "redirect:/";
		}

	}

	@GetMapping("/registro")
	public String registro(ModelMap model) {
		model.addAttribute("autores", autorService.listarTodos());
		model.addAttribute("editoriales", editorialService.listarTodos());

		return "form-libro";
	}

	@PostMapping("/registro")
	public String registrar(ModelMap model, @RequestParam String titulo, @RequestParam Integer anio,
			@RequestParam Integer ejemplares, @RequestParam String autorID, @RequestParam String editorialID) {
		try {
			libroService.guardar(titulo, anio, ejemplares, autorID, editorialID);
			model.put("exito", "libro guardado con exito");
			return "form-libro";
		} catch (Exception e) {
			model.put("error", e);
			return "form-libro";
		}

	}

	@GetMapping("/actualizar/{id}")
	public String encontrar(ModelMap model, @PathVariable String id) {
		try {
			model.addAttribute("libro", libroService.obtenerID(id));
			return "actualizar-libro";
		} catch (Exception e) {
			return "redirect:/libro";
		}
	}

	@PostMapping("/actualizar/{id}")
	public String actualizar(ModelMap model, @RequestParam String id, @RequestParam String titulo,
			@RequestParam Integer anio, @RequestParam Integer ejemplares, @RequestParam String autorID,
			@RequestParam String editorialID) {
		try {
			libroService.update(id, titulo, anio, ejemplares, autorID, editorialID);
			model.put("exito", "libro actualizado con exito");
			return "actualizar-libro";
		} catch (Exception e) {
			model.put("error", e);
			return "actualizar-libro";
		}
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(ModelMap model, @PathVariable String id) {

		try {
			libroService.eliminar(id);
			model.put("exito", "libro eliminado exitosamente");
			return "libro";
		} catch (Exception e) {
			model.put("error", e);
			return "libro";
		}

	}

}
