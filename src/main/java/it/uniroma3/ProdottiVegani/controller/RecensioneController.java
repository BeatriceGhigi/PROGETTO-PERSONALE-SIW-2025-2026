package it.uniroma3.ProdottiVegani.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.ProdottiVegani.model.Recensione;
import it.uniroma3.ProdottiVegani.service.ProdottoService;
import it.uniroma3.ProdottiVegani.service.RecensioneService;

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
    public String saveRecensione(@PathVariable("id") Long id, Recensione recensione) {
        recensione.setProdotto(this.prodottoService.findById(id).get());
        this.recensioneService.save(recensione);
        return "redirect:/prodotti/" + id;
    }
    

    @GetMapping("/recensioni/delete/{id}")
    public String deleteRecensione(@PathVariable("id") Long id) {
        Long prodottoId = this.recensioneService.findById(id).get().getProdotto().getId();
        this.recensioneService.deleteById(id);
        return "redirect:/prodotti/" + prodottoId;
    }
}
