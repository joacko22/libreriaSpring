package com.Libreria.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Libreria.servicios.EditorialServicio;
@Controller
@RequestMapping("/Editorial")
public class EditorialController {
	@Autowired
	private EditorialServicio editService;

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
