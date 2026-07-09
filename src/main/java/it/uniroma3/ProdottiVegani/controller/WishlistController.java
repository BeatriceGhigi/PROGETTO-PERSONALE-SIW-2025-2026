package it.uniroma3.ProdottiVegani.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.ProdottiVegani.model.Utente;
import it.uniroma3.ProdottiVegani.model.Wishlist;
import it.uniroma3.ProdottiVegani.service.UtenteService;
import it.uniroma3.ProdottiVegani.service.WishlistService;
import java.time.LocalDateTime;
import it.uniroma3.ProdottiVegani.model.Prodotto;
import it.uniroma3.ProdottiVegani.service.ProdottoService;

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

    @GetMapping("/utenti/{id}/wishlist")
    public String showWishlist(@PathVariable("id") Long id, Model model) {
        Utente utente = this.utenteService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato con ID: " + id));
        
        model.addAttribute("wishlist", this.wishlistService.findByUtenteId(id));
        model.addAttribute("utente", utente);
        return "wishlist/list";
    }
    
    @GetMapping("/utenti/{utenteId}/wishlist/add/{prodottoId}")
    public String addToWishlist(@PathVariable("utenteId") Long utenteId,
                                @PathVariable("prodottoId") Long prodottoId) {

        Utente utente = this.utenteService.findById(utenteId)
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato con ID: " + utenteId));

        Prodotto prodotto = this.prodottoService.findById(prodottoId)
                .orElseThrow(() -> new IllegalArgumentException("Prodotto non trovato con ID: " + prodottoId));

        Wishlist wishlist = new Wishlist();
        wishlist.setUtente(utente);
        wishlist.setProdotto(prodotto);
        wishlist.setDataAggiunta(LocalDateTime.now());

        this.wishlistService.save(wishlist);

        return "redirect:/utenti/" + utenteId + "/wishlist";
    }
    
    @GetMapping("/wishlist/delete/{id}")
    public String deleteWishlistItem(@PathVariable("id") Long id) {
        // Recupera l'elemento della wishlist in modo sicuro
        Wishlist item = this.wishlistService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Elemento wishlist non trovato con ID: " + id));
        Long utenteId = item.getUtente().getId();
        
        this.wishlistService.deleteById(id);
        return "redirect:/utenti/" + utenteId + "/wishlist";
    }
}
