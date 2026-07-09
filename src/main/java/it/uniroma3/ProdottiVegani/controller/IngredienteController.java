package it.uniroma3.ProdottiVegani.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.ProdottiVegani.model.Ingrediente;
import it.uniroma3.ProdottiVegani.service.IngredienteService;

@Controller
public class IngredienteController {

    private IngredienteService ingredienteService;

    public IngredienteController(IngredienteService ingredienteService) {
        this.ingredienteService = ingredienteService;
    }

    @GetMapping("/ingredienti")
    public String list(Model model) {
        List<Ingrediente> ingredienti = this.ingredienteService.findAll();
        model.addAttribute("ingredienti", ingredienti);
        return "ingredienti/list";
    }

    @GetMapping("/ingredienti/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Optional<Ingrediente> ingredienteOptional = this.ingredienteService.findById(id);
        if (ingredienteOptional.isPresent()) {
            model.addAttribute("ingrediente", ingredienteOptional.get());
        }
        return "ingredienti/show";
    }
}