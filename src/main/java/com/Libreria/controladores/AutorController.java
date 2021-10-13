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

import com.Libreria.entidades.Autor;
import com.Libreria.servicios.AutorServicio;

@Controller
@RequestMapping("/Autor")
public class AutorController {
	@Autowired
	private AutorServicio autorService;

	@GetMapping("/lista")
	public String listAutores(ModelMap modelo) {
		List<Autor> autores = autorService.listarTodos();
		modelo.addAttribute("autores",autores);
		return "list-Autor";
	}
	
	@GetMapping("/registro")
	public String formulario() {
		return "form-Autor";
	}

	@PostMapping("/registro")
	public String guardar(ModelMap modelo, @RequestParam String nombre) {

		try { 
			autorService.guardar(nombre);
			modelo.put("exito", "Registro exitoso");
			return "redirect:/Autor";
		} catch (Exception e) {
			modelo.put("error", "falto algun dato");
			return "form-Autor";
		}
	}
	@GetMapping("/modificar/{id}")
	public String modifica(ModelMap modelo,@PathVariable String id){
		
		autorService.encontrarID(id);
		modelo.addAttribute(id);
		return "modificar-autor";
	}	
	
	@PostMapping("/modificar/{id}")
	public String modificar(@PathVariable String id,@RequestParam String nombre) {
		try {
			autorService.modificar(id,nombre);
			return "modificar-autor";
		} catch (Exception e) {
			return "redirect:/";
		}
		
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(ModelMap modelo,@PathVariable String id){
		try {
			autorService.eliminar(id);
			modelo.put("exito","autor eliminado");
			return "redirect:/Autor/lista";
		} catch (Exception e) {
			return "redirect:/";
		}
		
	}
	@GetMapping("/baja/{id}")
	public String baja(@PathVariable String id) {
				
		try {
			autorService.baja(id);
			return "redirect:/Autor/lista";
		} catch (Exception e) {
			return "redirect:/";
		}
	}
	@GetMapping("/alta/{id}")
	public String alta(@PathVariable String id) {
				
		try {
			autorService.alta(id);
			return "redirect:/Autor/lista";
		} catch (Exception e) {
			return "redirect:/";
		}
	}
}
