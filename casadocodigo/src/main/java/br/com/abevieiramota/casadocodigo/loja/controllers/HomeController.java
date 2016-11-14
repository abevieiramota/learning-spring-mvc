package br.com.abevieiramota.casadocodigo.loja.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// define um controlador
@Controller
public class HomeController {
	
	// mapeia uma request num handler
	@RequestMapping("/")
	public String index() {
		System.out.println("Entrou na home.");
		
		// nome de view
		return "home";
	}
}
