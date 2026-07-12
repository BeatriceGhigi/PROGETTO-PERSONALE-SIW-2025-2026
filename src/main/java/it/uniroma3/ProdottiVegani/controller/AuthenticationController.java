package it.uniroma3.ProdottiVegani.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.ProdottiVegani.exception.UsernameGiaRegistratoException;
import it.uniroma3.ProdottiVegani.model.Utente;
import it.uniroma3.ProdottiVegani.service.UtenteService;
import jakarta.validation.Valid;

@Controller
public class AuthenticationController {

	private UtenteService utenteService;

	public AuthenticationController(UtenteService utenteService) {
		this.utenteService = utenteService;
	}

	@GetMapping("/login")
	public String showLoginForm() {
		return "login";
	}

	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("utente", new Utente());
		return "register";
	}

	@PostMapping("/register")
	public String registraUtente(@Valid @ModelAttribute("utente") Utente utente, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "register";
		}

		try {
			this.utenteService.registraNuovoUtente(utente);
			return "redirect:/login?registrato";
		} catch (UsernameGiaRegistratoException e) {
			bindingResult.rejectValue("username", "utente.duplicate", "Questo username è già in uso");
			return "register";
		}
	}
}