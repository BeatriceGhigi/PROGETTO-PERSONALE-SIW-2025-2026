package it.uniroma3.ProdottiVegani.controller;

import java.time.LocalDateTime;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.ProdottiVegani.model.Prodotto;
import it.uniroma3.ProdottiVegani.model.Utente;
import it.uniroma3.ProdottiVegani.model.Wishlist;
import it.uniroma3.ProdottiVegani.service.ProdottoService;
import it.uniroma3.ProdottiVegani.service.UtenteService;
import it.uniroma3.ProdottiVegani.service.WishlistService;

@Controller
public class WishlistController {

	private WishlistService wishlistService;
	private UtenteService utenteService;
	private ProdottoService prodottoService;

	public WishlistController(WishlistService wishlistService,
	                           UtenteService utenteService,
	                           ProdottoService prodottoService) {
		this.wishlistService = wishlistService;
		this.utenteService = utenteService;
		this.prodottoService = prodottoService;
	}

	// Mostra SOLO la wishlist dell'utente attualmente autenticato
	@GetMapping("/wishlist")
	public String showWishlist(Model model, Authentication authentication) {
		Utente utente = this.utenteService.findByUsername(authentication.getName())
				.orElseThrow(() -> new IllegalArgumentException("Utente non trovato: " + authentication.getName()));

		model.addAttribute("wishlist", this.wishlistService.findByUtenteId(utente.getId()));
		model.addAttribute("utente", utente);
		return "wishlist/list";
	}

	@PostMapping("/wishlist/add/{prodottoId}")
	public String addToWishlist(@PathVariable("prodottoId") Long prodottoId, Authentication authentication) {
		Utente utente = this.utenteService.findByUsername(authentication.getName())
				.orElseThrow(() -> new IllegalArgumentException("Utente non trovato: " + authentication.getName()));
		Prodotto prodotto = this.prodottoService.findById(prodottoId)
				.orElseThrow(() -> new IllegalArgumentException("Prodotto non trovato con ID: " + prodottoId));

		Wishlist wishlist = new Wishlist();
		wishlist.setUtente(utente);
		wishlist.setProdotto(prodotto);
		wishlist.setDataAggiunta(LocalDateTime.now());
		this.wishlistService.save(wishlist);

		return "redirect:/wishlist";
	}

	// Elimina un elemento SOLO se appartiene all'utente autenticato
	@PostMapping("/wishlist/delete/{id}")
	public String deleteWishlistItem(@PathVariable("id") Long id, Authentication authentication) {
		Wishlist item = this.wishlistService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Elemento wishlist non trovato con ID: " + id));

		if (!item.getUtente().getUsername().equals(authentication.getName())) {
			throw new AccessDeniedException("Non puoi eliminare un elemento della wishlist di un altro utente.");
		}

		this.wishlistService.deleteById(id);
		return "redirect:/wishlist";
	}
}