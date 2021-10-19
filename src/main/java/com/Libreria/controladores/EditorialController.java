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

import com.Libreria.entidades.Editorial;
import com.Libreria.servicios.EditorialServicio;
@Controller
@RequestMapping("/Editorial")
public class EditorialController {
	@Autowired
	private EditorialServicio editService;
	@GetMapping()
	public String listEditorial(ModelMap modelo) {
		List<Editorial> editoriales = editService.listarTodos();
		modelo.addAttribute("editoriales",editoriales );
		return "list-Editorial";
	}
	@GetMapping("/registro")
	public String formulario() {
		return "form-Editorial";
	}

	@PostMapping("/registro")
	public String guardar(ModelMap modelo, @RequestParam String nombre) {

		try {
			editService.guardar(nombre);
			modelo.put("exito", "Registro exitoso");
			return "form-Editorial";
		} catch (Exception e) {
			modelo.put("error", "falto algun dato");
			return "form-Editorial";
		}
		
	}
	@GetMapping("/modificar/{id}")
	public String obtenerID(ModelMap model,@PathVariable String id) {
		try {
			model.addAttribute("edit",editService.obtenerID(id));
			return "modificar-edit";
		} catch (Exception e) {
			return "/";
		}
	}
	@PostMapping("/modificar/{id}")
	public String modificar(@PathVariable String id,@RequestParam String nombre) {
		try {
			editService.modificar(id, nombre);
			return "redirect:/Editorial/list-Editorial";
		} catch (Exception e) {
		return "/Editorial/list-Editorial";
		}
	}
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable String id) {
		try {
			editService.eliminar(id);
			return"list-Editorial";
		} catch (Exception e) {
			return"list-Editorial";
		}
	}
	@GetMapping("/baja/{id}")
	public String baja(@PathVariable String id) {
				
		try {
			editService.baja(id);
			return "redirect:/Editorial";
		} catch (Exception e) {
			return "redirect:/";
		}
	}
	@GetMapping("/alta/{id}")
	public String alta(@PathVariable String id) {
				
		try {
			editService.alta(id);
			return "redirect:/Editorial";
		} catch (Exception e) {
			return "redirect:/";
		}
	}
}
