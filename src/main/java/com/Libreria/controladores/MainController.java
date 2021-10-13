package com.Libreria.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

	@GetMapping()
	public String index() {

		return "index.html";
	}
	@GetMapping("/Autor")
	public String Autor() {
		return "Autor.html";
	}

}
