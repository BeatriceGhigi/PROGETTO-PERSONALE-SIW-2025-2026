package it.uniroma3.ProdottiVegani.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.ProdottiVegani.model.Prodotto;
import it.uniroma3.ProdottiVegani.service.ProdottoService;

@Controller
public class ProdottoController {

    private ProdottoService prodottoService;

    public ProdottoController(ProdottoService prodottoService) {
        this.prodottoService = prodottoService;
    }

    @GetMapping("/prodotti")
    public String list(Model model) {
        List<Prodotto> prodotti = this.prodottoService.findAll();
        model.addAttribute("prodotti", prodotti);
        return "prodotti/list";
    }

    @GetMapping("/prodotti/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Optional<Prodotto> prodottoOptional = this.prodottoService.findById(id);
        if (prodottoOptional.isPresent()) {
            model.addAttribute("prodotto", prodottoOptional.get());
        }
        return "prodotti/show";
    }
}
