package it.uniroma3.ProdottiVegani.controller;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.ProdottiVegani.model.Recensione;
import it.uniroma3.ProdottiVegani.service.ProdottoService;
import it.uniroma3.ProdottiVegani.service.RecensioneService;
import it.uniroma3.ProdottiVegani.service.UtenteService;

@Controller
public class RecensioneController {

	private RecensioneService recensioneService;
	private ProdottoService prodottoService;
	private UtenteService utenteService;

	public RecensioneController(RecensioneService recensioneService,
	                             ProdottoService prodottoService,
	                             UtenteService utenteService) {
		this.recensioneService = recensioneService;
		this.prodottoService = prodottoService;
		this.utenteService = utenteService;
	}

	@GetMapping("/prodotti/{id}/formNewRecensione")
	public String formNewRecensione(@PathVariable("id") Long id, Model model) {
		model.addAttribute("recensione", new Recensione());
		model.addAttribute("prodotto", this.prodottoService.findById(id).get());
		return "recensioni/formNewRecensione";
	}

	@PostMapping("/prodotti/{id}/recensioni")
	public String saveRecensione(@PathVariable("id") Long id, Recensione recensione, Authentication authentication) {
		this.recensioneService.salva(recensione, id, authentication.getName());
		return "redirect:/prodotti/" + id;
	}

	// Mostra il form di modifica SOLO se chi lo richiede e' l'autore della recensione
	@GetMapping("/recensioni/{id}/modifica")
	public String formModificaRecensione(@PathVariable("id") Long id, Model model, Authentication authentication) {
		Recensione recensione = this.recensioneService.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Recensione non trovata con id " + id));

		if (!recensione.getAutore().getUsername().equals(authentication.getName())) {
			throw new AccessDeniedException("Non puoi modificare una recensione scritta da un altro utente.");
		}

		model.addAttribute("recensione", recensione);
		return "recensioni/modifica";
	}

	@PostMapping("/recensioni/{id}/modifica")
	public String aggiornaRecensione(@PathVariable("id") Long id, Recensione recensioneForm, Authentication authentication) {
		Recensione aggiornata = this.recensioneService.aggiorna(id, recensioneForm, authentication.getName());
		return "redirect:/prodotti/" + aggiornata.getProdotto().getId();
	}

	@GetMapping("/recensioni/delete/{id}")
	public String deleteRecensione(@PathVariable("id") Long id) {
		Long prodottoId = this.recensioneService.findById(id).get().getProdotto().getId();
		this.recensioneService.deleteById(id);
		return "redirect:/prodotti/" + prodottoId;
	}
}