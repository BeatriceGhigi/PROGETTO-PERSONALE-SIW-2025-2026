package it.uniroma3.ProdottiVegani.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.ProdottiVegani.model.Categoria;
import it.uniroma3.ProdottiVegani.service.CategoriaService;
import jakarta.validation.Valid;

@Controller
public class CategoriaController {

	private CategoriaService categoriaService;

	public CategoriaController(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	// ===================== FUNZIONALITA' PUBBLICHE (Sezione 4.1) =====================

	@GetMapping("/categorie")
	public String list(Model model) {
		List<Categoria> categorie = this.categoriaService.findAll();
		model.addAttribute("categorie", categorie);
		return "categorie/list";
	}

	@GetMapping("/categorie/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		Optional<Categoria> categoriaOptional = this.categoriaService.findById(id);
		if (categoriaOptional.isPresent()) {
			model.addAttribute("categoria", categoriaOptional.get());
		}
		return "categorie/show";
	}

	// ===================== FUNZIONALITA' ADMIN (Sezione 4.3) =====================

	@GetMapping("/admin/categorie/nuova")
	public String formNuovaCategoria(Model model) {
		model.addAttribute("categoria", new Categoria());
		return "admin/categorie/form";
	}

	@PostMapping("/admin/categorie")
	public String creaCategoria(@Valid @ModelAttribute("categoria") Categoria categoria, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "admin/categorie/form";
		}
		Categoria salvata = this.categoriaService.salva(categoria);
		return "redirect:/categorie/" + salvata.getId();
	}

	@GetMapping("/admin/categorie/{id}/modifica")
	public String formModificaCategoria(@PathVariable("id") Long id, Model model) {
		Optional<Categoria> categoriaOptional = this.categoriaService.findById(id);
		if (categoriaOptional.isEmpty()) {
			return "redirect:/categorie";
		}
		model.addAttribute("categoria", categoriaOptional.get());
		return "admin/categorie/form";
	}

	@PostMapping("/admin/categorie/{id}")
	public String aggiornaCategoria(@PathVariable("id") Long id,
	                                 @Valid @ModelAttribute("categoria") Categoria categoriaForm,
	                                 BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			categoriaForm.setId(id);
			return "admin/categorie/form";
		}
		Categoria aggiornata = this.categoriaService.aggiorna(id, categoriaForm);
		return "redirect:/categorie/" + aggiornata.getId();
	}

	@PostMapping("/admin/categorie/{id}/elimina")
	public String eliminaCategoria(@PathVariable("id") Long id) {
		this.categoriaService.elimina(id);
		return "redirect:/categorie";
	}
}